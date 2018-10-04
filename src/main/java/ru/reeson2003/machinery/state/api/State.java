package ru.reeson2003.machinery.state.api;

public interface State<S, A extends Action<?, ?>> {

    static <M, P extends Action<?, ?>> State<M, P> create(M initialState, Dispatcher<M, P> dispatcher) {
        StateImpl<M, P> stateImpl = new StateImpl(initialState, dispatcher);
        return stateImpl;
    }

    void perform(A action);

    Subscription<S> listen(StateListener<S> listener);

}
