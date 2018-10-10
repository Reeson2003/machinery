package ru.reeson2003.machinery.api;

public interface StatefulPresenter<M, V extends View<?>, A extends Action<?, ?>>
        extends Presenter<V> {

    State<M, A> getState();

    State<M, A> detachState();

    void attachState(Class<M> model);

}
