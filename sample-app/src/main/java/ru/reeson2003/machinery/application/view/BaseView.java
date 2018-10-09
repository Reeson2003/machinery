package ru.reeson2003.machinery.application.view;

import org.springframework.web.socket.WebSocketSession;
import ru.reeson2003.machinery.api.Presenter;
import ru.reeson2003.machinery.api.View;
import ru.reeson2003.machinery.application.service.ws.Connection;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class BaseView<M, P extends Presenter>
        implements View<M, P>, Connection {

    private P presenter;

    private WebSocketSession session;

    public BaseView(WebSocketSession session) {
        this.session = session;
    }

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

    protected WebSocketSession getSession() {
        return session;
    }
}
