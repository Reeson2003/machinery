package ru.reeson2003.machinery.api;

public interface Action<I, P> {

    I getIdentity();

    P getPayload();
}
