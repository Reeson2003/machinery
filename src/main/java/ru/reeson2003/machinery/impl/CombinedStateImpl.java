package ru.reeson2003.machinery.impl;

import ru.reeson2003.machinery.api.Action;
import ru.reeson2003.machinery.api.CombinedState;
import ru.reeson2003.machinery.api.State;

import java.util.HashMap;
import java.util.Map;

public class CombinedStateImpl
        implements CombinedState {

    private static Map<Class<?>, State<?, ?>> combinedState = new HashMap<>();


    public static <M, A extends Action<?, ?>> void combine(State<M, A> state, Class<M> model) {
        combinedState.put(model, state);
    }

    @SuppressWarnings("unchecked")
    public static <M, A extends Action<?, ?>> State<M, A> getState(Class<M> model) {
        State<?, ?> state = combinedState.get(model);
        if (state == null) {
            throw new IllegalStateException("No state instance found for model: [" + model + "]");
        }
        return (State<M, A>) state;
    }

}
