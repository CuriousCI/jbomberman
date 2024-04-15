package jbomberman.util;

public interface Observable<T> {
    void notifySubscribers(T data);

    void subscribe(Observer<T> subscriber);
}
