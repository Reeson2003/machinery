package ru.reeson2003.machinery.state.api;

public interface Action<P> {

    String getName();

    P getPayload();
}
