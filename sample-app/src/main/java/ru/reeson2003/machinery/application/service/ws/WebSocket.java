package ru.reeson2003.machinery.application.service.ws;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.websocket.server.ServerContainer;
import java.util.Optional;

@Slf4j
public abstract class WebSocket
        implements WebSocketConfigurer {

    @Autowired
    private ServletContext context;

    @Autowired
    private MessageHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/" + context()).setAllowedOrigins("*");
    }

    protected abstract String context();

    @PostConstruct
    public void updateConfiguration() {
        Optional.ofNullable("10000")
                .map(Long::parseLong)
                .ifPresent(timeout -> Optional
                        .ofNullable(context.getAttribute("javax.websocket.server.ServerContainer"))
                        .map(obj -> (ServerContainer) obj)
                        .ifPresent(container -> container.setDefaultMaxSessionIdleTimeout(timeout)));
    }

}