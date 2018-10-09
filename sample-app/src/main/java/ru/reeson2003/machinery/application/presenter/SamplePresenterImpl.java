package ru.reeson2003.machinery.application.presenter;

import ru.reeson2003.machinery.api.State;
import ru.reeson2003.machinery.api.View;
import ru.reeson2003.machinery.application.model.SampleAction;
import ru.reeson2003.machinery.application.model.SampleModel;
import ru.reeson2003.machinery.application.model.SamplePayload;
import ru.reeson2003.machinery.impl.BasePresenter;

public class SamplePresenterImpl
        extends BasePresenter<SampleModel, View<SampleModel, SamplePresenter>, SampleAction.Result, SamplePayload, SampleAction>
        implements SamplePresenter {


    @Override
    public Class<SampleModel> getModelClass() {
        return SampleModel.class;
    }

    @Override
    public void setData(String data) {
        getState().perform(new SampleAction(SampleAction.Result.MESSAGE, new SamplePayload(data)));
    }

    @Override
    public void connect() {
        getState().perform(new SampleAction(SampleAction.Result.CONNECT, new SamplePayload("")));
    }

    @Override
    public void disconnect() {
        State<SampleModel, SampleAction> state = getState();
        detach();
        state.perform(new SampleAction(SampleAction.Result.DISCONNECT, new SamplePayload("")));
    }

}
