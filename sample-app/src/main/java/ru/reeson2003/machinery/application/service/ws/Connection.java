package ru.reeson2003.machinery.application.service.ws;

import org.springframework.web.socket.CloseStatus;

public interface Connection {

    void onMessage(String text);

    void onError(Throwable exception);

    void onClose(CloseStatus status);

}