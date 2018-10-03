package ru.reeson2003.machinery.state.api;

public interface Dispatcher<S, A extends Action> {

    S dispatch(S previousState, A action);
}
