package ru.reeson2003.machinery.state.api;

public interface Action<I, P> {

    I getIdentity();

    P getPayload();
}
