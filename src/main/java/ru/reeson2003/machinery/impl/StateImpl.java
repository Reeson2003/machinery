package ru.reeson2003.machinery.impl;

import ru.reeson2003.machinery.api.*;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StateImpl<S, A extends Action<?, ?>>
        implements State<S, A> {

    private S state;

    private Dispatcher<S, A> dispatcher;

    private List<StateListener<S>> listeners = new ArrayList<>();

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public StateImpl(S state, Dispatcher<S, A> dispatcher) {
        checkFinality(state);
        this.state = state;
        this.dispatcher = dispatcher;
    }

    private void checkFinality(S state) {
        Field[] fields = state.getClass().getFields();
        for (int i = 0; i < fields.length; i++) {
            int modifiers = fields[i].getModifiers();
            boolean isFinal = Modifier.isFinal(modifiers);
            if (!isFinal) {
                throw new IllegalArgumentException("All fields of model class should be final");
            }
        }
    }

    @Override
    public void perform(A action) {
        execute(() -> {
            this.state = dispatcher.dispatch(state, action);
            for (StateListener<S> l : listeners)
                l.update(this.state);
        });
    }

    private void execute(Runnable runnable) {
        executor.submit(runnable);
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
