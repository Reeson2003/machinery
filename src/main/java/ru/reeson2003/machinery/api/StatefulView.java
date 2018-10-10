package ru.reeson2003.machinery.api;

public interface StatefulView<M, P extends StatefulPresenter<M, ?, ?>>
        extends View<P> {

    void onUpdate(M model);
}
