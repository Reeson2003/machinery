package ru.reeson2003.machinery.impl;

import ru.reeson2003.machinery.api.Presenter;
import ru.reeson2003.machinery.api.View;

public abstract class BasePresenter<V extends View<?>>
        implements Presenter<V> {

    private V mView;

    @Override
    @SuppressWarnings("unchecked")
    public void attachView(View view) {
        mView = (V) view;
    }

    @Override
    public V detachView() {
        V result = mView;
        mView = null;
        return result;
    }

    @Override
    public V getView() {
        return mView;
    }

}
