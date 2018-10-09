package ru.reeson2003.machinery.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import ru.reeson2003.machinery.api.CombinedState;
import ru.reeson2003.machinery.api.State;
import ru.reeson2003.machinery.application.model.SampleAction;
import ru.reeson2003.machinery.application.model.SampleDispatcher;
import ru.reeson2003.machinery.application.model.SampleModel;
import ru.reeson2003.machinery.application.service.ws.Connection;
import ru.reeson2003.machinery.application.service.ws.WebSocket;
import ru.reeson2003.machinery.application.view.SampleView;

import java.util.Date;

@SpringBootApplication
@EnableWebSocket
public class Application {

    public static void main(String[] args) {
        State<SampleModel, SampleAction> state = State.create(new SampleModel("initial data", 0, new Date()), new SampleDispatcher());
        CombinedState.combine(state, SampleModel.class);
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebSocket webSocket() {
        return new WebSocket() {
            @Override
            protected String context() {
                return "ws";
            }
        };
    }

    @Bean
    @Lazy
    @Scope("prototype")
    public Connection connection(WebSocketSession session) {
        return new SampleView(session);
    }
}
