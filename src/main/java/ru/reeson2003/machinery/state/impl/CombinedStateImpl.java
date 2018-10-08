package ru.reeson2003.machinery.state.impl;

import ru.reeson2003.machinery.state.api.*;

import java.util.HashMap;
import java.util.Map;

public class CombinedStateImpl
        implements CombinedState {

    private Map<Class<?>, State<?, ?>> combinedState = new HashMap<>();


    @Override
    @SuppressWarnings("unchecked")
    public <S> Subscription<S> listen(StateListener<S> listener, Class<S> model) {
        State<S, ?> state = (State<S, ?>) combinedState.get(model);
        return state.listen(listener);
    }

    @Override
    public <S, A extends Action<?, ?>> void combine(State<S, A> state, Class<S> model) {
        combinedState.put(model, state);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <S, A extends Action<?, ?>> void perform(A action, Class<S> model) {
        State<S, A> state = (State<S, A>) combinedState.get(model);
        state.perform(action);
    }
}
