package ru.reeson2003.machinery.impl;

import ru.reeson2003.machinery.api.*;

public abstract class BaseStatefulPresenter<M, V extends StatefulView<M, ?>, A extends Action<?, ?>>
        extends BasePresenter<V>
        implements StatefulPresenter<M, V, A> {

    private Subscription<M> mSubscription;

    private State<M, A> mState;

    @Override
    public State<M, A> detachState() {
        mSubscription.unsubscribe();
        State<M, A> result = mState;
        mState = null;
        return result;
    }

    @Override
    public void attachState(Class<M> model) {
        mState = CombinedState.getState(model);
        mSubscription = mState.listen(getView()::onUpdate);
    }

    @Override
    public State<M, A> getState() {
        return mState;
    }
}
