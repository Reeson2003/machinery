package ru.reeson2003.machinery.api;

public interface Presenter<V extends View> {

    void attachView(View view);

    V detachView();

    V getView();

}
