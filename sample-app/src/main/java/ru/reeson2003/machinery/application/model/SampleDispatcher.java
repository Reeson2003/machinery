package ru.reeson2003.machinery.application.model;

import ru.reeson2003.machinery.api.Dispatcher;

import java.util.List;

public class SampleDispatcher
        implements Dispatcher<SampleModel, SampleAction> {

    @Override
    public SampleModel dispatch(SampleModel previousState, SampleAction action) {
        switch (action.getIdentity()) {
            case CONNECT:
                return new SampleModel(previousState.getMessages(), previousState.getUsersOnline() + 1);
            case DISCONNECT:
                return new SampleModel(previousState.getMessages(), previousState.getUsersOnline() - 1);
            case MESSAGE: {
                List<String> messages = previousState.getMessages();
                messages.add(action.getPayload().getMessage());
                new SampleModel(messages, previousState.getUsersOnline());
            }
            default:
                return previousState;
        }
    }
}
