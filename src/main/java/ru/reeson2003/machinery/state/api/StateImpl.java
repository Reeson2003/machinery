package ru.reeson2003.machinery.state.api;

import java.util.ArrayList;
import java.util.List;

class StateImpl<S, A extends Action<?>>
        implements State<S, A> {

    private S state;

    private Dispatcher<S, A> dispatcher;

    private List<StateListener<S>> listeners = new ArrayList<>();

    StateImpl(S state, Dispatcher<S, A> dispatcher) {
        this.state = state;
        this.dispatcher = dispatcher;
    }


    @Override
    public void perform(A action) {
        this.state = dispatcher.dispatch(state, action);
        for (StateListener<S> l : listeners)
            l.update(this.state);
    }

    @Override
    public Subscription<S> listen(StateListener<S> listener) {
        listeners.add(listener);
        listener.update(state);
        return () -> {
            listeners.remove(listener);
            return listener;
        };
    }

}
