package com.step2hell.newsmth.util;

import java.util.HashMap;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

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
            if (map.get(key) != null) {
                map.get(key).add(d);
            } else {
                CompositeDisposable disposables = new CompositeDisposable();
                disposables.add(d);
                map.put(key, disposables);
            }
        }

        @Override
        public <T> void unregisterBus(T t) {
            String key = t.getClass().getName();
            if (map.containsKey(key)) {
                CompositeDisposable disposables = map.get(key);
                if (disposables != null) disposables.dispose();
                map.remove(key);
            }
        }
    };

    public <T> void publish(T t) {
        throw new AbstractMethodError();
    }

    public <T> Flowable<T> listen(Class<T> eventType) {
        throw new AbstractMethodError();
    }

    public <T> void registerBus(T t, Disposable d) {
        throw new AbstractMethodError();
    }

    public <T> void unregisterBus(T t) {
        throw new AbstractMethodError();
    }
}
