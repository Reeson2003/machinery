package ru.reeson2003.machinery.impl;

import ru.reeson2003.machinery.api.*;

public abstract class BasePresenter<M, V extends View<M, ?>, I, P, A extends Action<I, P>>
        implements Presenter<M, V, I, P, A> {

    private Subscription<M> mSubscription;

    private V mView;

    private State<M, A> mState;

    @Override
    public void attach(V view) {
        mView = view;
        mState = CombinedState.getState(getModelClass());
        mSubscription = mState.listen(view::onUpdate);
    }

    @Override
    public void detach() {
        mSubscription.unsubscribe();
        mView = null;
        mState = null;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public State<M, A> getState() {
        return mState;
    }
}
