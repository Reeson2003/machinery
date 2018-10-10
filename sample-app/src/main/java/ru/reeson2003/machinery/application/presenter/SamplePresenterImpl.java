package ru.reeson2003.machinery.application.presenter;

import ru.reeson2003.machinery.api.State;
import ru.reeson2003.machinery.api.StatefulView;
import ru.reeson2003.machinery.application.model.SampleAction;
import ru.reeson2003.machinery.application.model.SampleModel;
import ru.reeson2003.machinery.application.model.SamplePayload;
import ru.reeson2003.machinery.impl.BaseStatefulPresenter;

public class SamplePresenterImpl
        extends BaseStatefulPresenter<SampleModel, StatefulView<SampleModel, SamplePresenter>, SampleAction>
        implements SamplePresenter {

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
        State<SampleModel, SampleAction> state = detachState();
        state.perform(new SampleAction(SampleAction.Result.DISCONNECT, new SamplePayload("")));
    }

}
