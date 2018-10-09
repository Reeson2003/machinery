package ru.reeson2003.machinery.application.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.reeson2003.machinery.application.model.SampleModel;
import ru.reeson2003.machinery.application.presenter.SamplePresenter;
import ru.reeson2003.machinery.application.presenter.SamplePresenterImpl;

@RestController
public class SampleController
        extends BaseView<SampleModel, SamplePresenter> {

    private SampleModel sampleModel;

    @RequestMapping("/info")
    public SampleModel info() {
        return sampleModel;
    }

    @RequestMapping("/data")
    public void setData(@RequestParam String data) {
        getPresenter().setData(data);
    }

    @RequestMapping("/id")
    public void setId(@RequestParam long id) {
        getPresenter().setId(id);
    }

    @Override
    public SamplePresenter supplyPresenter() {
        return new SamplePresenterImpl();
    }

    @Override
    public void onUpdate(SampleModel model) {
        this.sampleModel = model;
    }
}
