package ru.reeson2003.machinery.api;

public interface View<P extends Presenter> {

    P supplyPresenter();

    P getPresenter();

}
