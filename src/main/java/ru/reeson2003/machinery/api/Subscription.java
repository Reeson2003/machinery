package ru.reeson2003.machinery.api;

public interface Subscription<S> {

    StateListener<S> unsubscribe();
}
