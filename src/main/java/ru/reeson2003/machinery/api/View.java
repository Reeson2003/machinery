package ru.reeson2003.machinery.api;

public interface View<M, P extends Presenter> {

    P supplyPresenter();

    P getPresenter();

    void onUpdate(M model);
}
