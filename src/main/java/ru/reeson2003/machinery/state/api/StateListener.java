package ru.reeson2003.machinery.state.api;

public interface StateListener<S> {

    void update(S state);

}
