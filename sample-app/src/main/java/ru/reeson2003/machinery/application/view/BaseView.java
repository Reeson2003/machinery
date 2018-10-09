package ru.reeson2003.machinery.application.view;

import ru.reeson2003.machinery.api.Presenter;
import ru.reeson2003.machinery.api.View;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class BaseView<M, P extends Presenter>
        implements View<M, P> {

    private P presenter;

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setup() {
        this.presenter = supplyPresenter();
        this.presenter.attach(this);
    }

    @PreDestroy
    public void cleanup() {
        this.presenter.detach();
        this.presenter = null;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }
}
