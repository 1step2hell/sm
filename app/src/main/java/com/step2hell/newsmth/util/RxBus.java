package com.step2hell.newsmth.util;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public enum RxBus {
    INSTANCE {
        private final Subject<Object> subject = PublishSubject.create().toSerialized();

        @Override
        public void publish(Object o) {
            subject.onNext(o);
        }

        @Override
        public <T> Observable<T> listen(Class<T> eventType) {
            return subject.ofType(eventType);
        }
    };

    public void publish(Object o) {
        throw new AbstractMethodError();
    }

    public <T> Observable<T> listen(Class<T> eventType) {
        throw new AbstractMethodError();
    }
}
