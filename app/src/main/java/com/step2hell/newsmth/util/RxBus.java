package com.step2hell.newsmth.util;

import java.util.HashMap;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public enum RxBus {
    INSTANCE {
        private final Subject<Object> subject = PublishSubject.create().toSerialized();

        private HashMap<String, CompositeDisposable> subscriptionMap = new HashMap<>();

        @Override
        public <T> void publish(T t) {
            subject.onNext(t);
        }

        @Override
        public <T> Flowable<T> listen(Class<T> eventType) {
            return subject.toFlowable(BackpressureStrategy.BUFFER).ofType(eventType);
        }

        @Override
        public <T> void registerBus(T t, Disposable d) {
            String key = t.getClass().getName();
            if (subscriptionMap.get(key) != null){
                subscriptionMap.get(key).add(d);
            }else{
                CompositeDisposable disposables = new CompositeDisposable();
                disposables.add(d);
                subscriptionMap.put(key,disposables);
            }
        }

        @Override
        public <T> void unregisterBus(T t) {
            String key = t.getClass().getName();
            if (subscriptionMap.containsKey(key)){
                CompositeDisposable disposables = subscriptionMap.get(key);
                if (disposables !=null) disposables.dispose();
                subscriptionMap.remove(key);
            }
        }
    };

    public <T> void publish(T t) {
        throw new AbstractMethodError();
    }

    public <T> Flowable<T> listen(Class<T> eventType) {
        throw new AbstractMethodError();
    }

    public <T> void registerBus(T t, Disposable d){
        throw new AbstractMethodError();
    }

    public <T> void unregisterBus(T t){
        throw new AbstractMethodError();
    }
}
