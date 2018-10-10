package ru.reeson2003.machinery.application.view;

import org.springframework.web.socket.WebSocketSession;
import ru.reeson2003.machinery.api.StatefulPresenter;
import ru.reeson2003.machinery.api.StatefulView;
import ru.reeson2003.machinery.application.service.ws.Connection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class BaseStatefulView<M, P extends StatefulPresenter<M, ?, ?>>
        implements StatefulView<M, P>, Connection {

    private P presenter;

    private WebSocketSession session;

    public BaseStatefulView(WebSocketSession session) {
        this.session = session;
    }

    @PostConstruct
    @SuppressWarnings("unchecked")
    public void setup() {
        this.presenter = supplyPresenter();
        this.presenter.attachView(this);
        this.presenter.attachState(getModelClass());
    }

    @PreDestroy
    public void cleanup() {
        this.presenter.detachView();
        this.presenter.detachState();
        this.presenter = null;
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    protected abstract Class<M> getModelClass();

    protected WebSocketSession getSession() {
        return session;
    }
}
