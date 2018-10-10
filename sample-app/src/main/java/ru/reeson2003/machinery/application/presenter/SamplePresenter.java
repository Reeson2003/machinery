package ru.reeson2003.machinery.application.presenter;

import ru.reeson2003.machinery.api.StatefulPresenter;
import ru.reeson2003.machinery.api.StatefulView;
import ru.reeson2003.machinery.application.model.SampleAction;
import ru.reeson2003.machinery.application.model.SampleModel;

public interface SamplePresenter
        extends StatefulPresenter<SampleModel, StatefulView<SampleModel, SamplePresenter>, SampleAction> {

    void setData(String data);

    void connect();

    void disconnect();
}
