package ru.reeson2003.machinery.state.api;

public interface Subscription<S> {

    StateListener<S> unsubscribe();
}
