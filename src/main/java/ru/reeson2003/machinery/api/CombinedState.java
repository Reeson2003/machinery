package ru.reeson2003.machinery.api;

import ru.reeson2003.machinery.impl.CombinedStateImpl;

public interface CombinedState {

    static <S, A extends Action<?, ?>> void combine(State<S, A> state, Class<S> model) {
        CombinedStateImpl.combine(state, model);
    }

    static <S, A extends Action<?, ?>> State<S, A> getState(Class<S> model) {
        return CombinedStateImpl.getState(model);
    }
}
