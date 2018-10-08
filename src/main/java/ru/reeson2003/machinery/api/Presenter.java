package ru.reeson2003.machinery.api;

public interface Presenter<M, V extends View, I, P, A extends Action<I, P>> {

    void attach(V view);

    void detach();

    V getView();

    State<M, A> getState();

    Class<M> getModelClass();
}
