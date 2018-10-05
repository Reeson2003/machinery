package ru.reeson2003.machinery.state.api;

public interface CombinedState {

    <S> Subscription<S> listen(StateListener<S> listener, Class<S> model);

    <S, A extends Action<?, ?>> void combine(State<S, A> state, Class<S> model);

    <S, A extends Action<?, ?>> void perform(A action, Class<S> model);
}
