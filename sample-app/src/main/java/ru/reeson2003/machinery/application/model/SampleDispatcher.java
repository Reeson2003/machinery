package ru.reeson2003.machinery.application.model;

import ru.reeson2003.machinery.api.Dispatcher;

import java.util.Date;

public class SampleDispatcher
        implements Dispatcher<SampleModel, SampleAction> {

    @Override
    public SampleModel dispatch(SampleModel previousState, SampleAction action) {
        switch (action.getIdentity()) {
            case DATA:
                return new SampleModel(action.getPayload().getData(), previousState.getId(), new Date());
            case ID:
                return new SampleModel(previousState.getData(), action.getPayload().getId(), new Date());
            default:
                return previousState;
        }
    }
}
