package com.step2hell.newsmth.util;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

@SuppressWarnings("ImmutableEnumChecker")
public enum RxBus {
    INSTANCE {
        private final FlowableProcessor<Object> processor = PublishProcessor.create().toSerialized();

        private HashMap<String, CompositeDisposable> map = new HashMap<>();

        @Override
        public <T> void publish(T t) {
            processor.onNext(t);
        }

        @Override
        public <T> Flowable<T> listen(Class<T> eventType) {
            return processor.ofType(eventType);
        }

        @Override
        public <T> void registerBus(T t, Disposable d) {
            String key = t.getClass().getName();
            CompositeDisposable disposables = map.get(key);
            if (disposables == null) {
                disposables = new CompositeDisposable();
                map.put(key,disposables);
            }
            disposables.add(d);
        }

        @Override
        public <T> void unregisterBus(T t) {
            String key = t.getClass().getName();
            CompositeDisposable disposables = map.get(key);
            if (disposables != null) {
                disposables.dispose();
                map.remove(key);
            }
        }
    };

    public abstract <T> void publish(T t);

    public abstract <T> Flowable<T> listen(Class<T> eventType);

    public abstract <T> void registerBus(T t, Disposable d);

    public abstract <T> void unregisterBus(T t);

}
