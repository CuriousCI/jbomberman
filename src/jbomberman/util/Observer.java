package jbomberman.util;

public interface Observer<T> {
    void actionPerformed(T data);
}
