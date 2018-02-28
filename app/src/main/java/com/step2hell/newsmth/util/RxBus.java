package com.step2hell.newsmth.util;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public enum RxBus {
    INSTANCE {
        private final Subject<Object> subject = PublishSubject.create().toSerialized();

        @Override
        public <T> void publish(T t) {
            subject.onNext(t);
        }

        @Override
        public <T> Flowable<T> listen(Class<T> eventType) {
            return subject.toFlowable(BackpressureStrategy.BUFFER).ofType(eventType);
        }
    };

    public <T> void publish(T t) {
        throw new AbstractMethodError();
    }

    public <T> Flowable<T> listen(Class<T> eventType) {
        throw new AbstractMethodError();
    }
}
