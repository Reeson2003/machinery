package ru.reeson2003.machinery.application.presenter;

import ru.reeson2003.machinery.api.Presenter;
import ru.reeson2003.machinery.api.View;
import ru.reeson2003.machinery.application.model.SampleAction;
import ru.reeson2003.machinery.application.model.SampleModel;
import ru.reeson2003.machinery.application.model.SamplePayload;

public interface SamplePresenter
        extends Presenter<SampleModel, View<SampleModel, SamplePresenter>, SampleAction.Result, SamplePayload, SampleAction> {

    void setData(String data);

    void connect();

    void disconnect();
}
