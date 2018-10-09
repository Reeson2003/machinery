package ru.reeson2003.machinery.application.presenter;

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
        getState().perform(new SampleAction(SampleAction.Result.DATA, new SamplePayload(0, data)));
    }

    @Override
    public void setId(long id) {
        getState().perform(new SampleAction(SampleAction.Result.ID, new SamplePayload(id, "")));
    }
}
