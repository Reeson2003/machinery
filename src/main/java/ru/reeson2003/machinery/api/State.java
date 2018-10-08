package ru.reeson2003.machinery.api;

import ru.reeson2003.machinery.impl.StateImpl;

public interface State<S, A extends Action<?, ?>> {

    @SuppressWarnings("unchecked")
    static <M, P extends Action<?, ?>> State<M, P> create(M initialState, Dispatcher<M, P> dispatcher) {
        return (StateImpl<M, P>) new StateImpl(initialState, dispatcher);
    }

    void perform(A action);

    Subscription<S> listen(StateListener<S> listener);
}
