package ru.reeson2003.machinery.application.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.reeson2003.machinery.application.model.SampleModel;
import ru.reeson2003.machinery.application.presenter.SamplePresenter;
import ru.reeson2003.machinery.application.presenter.SamplePresenterImpl;

import java.io.IOException;

public class SampleView
        extends BaseView<SampleModel, SamplePresenter> {

    public SampleView(WebSocketSession session) {
        super(session);
    }

    @Override
    public SamplePresenter supplyPresenter() {
        return new SamplePresenterImpl();
    }

    @Override
    public void onUpdate(SampleModel model) {
        try {
            String json = new ObjectMapper().writeValueAsString(model);
            getSession().sendMessage(new TextMessage(json));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOpen() {
        getPresenter().connect();
    }

    @Override
    public void onMessage(String text) {
        getPresenter().setData(text);
    }

    @Override
    public void onError(Throwable exception) {

    }

    @Override
    public void onClose(CloseStatus status) {
        getPresenter().disconnect();
    }
}
