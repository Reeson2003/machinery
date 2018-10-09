package ru.reeson2003.machinery.application.model;

import lombok.Data;
import ru.reeson2003.machinery.api.Action;

@Data
public class SampleAction
        implements Action<SampleAction.Result, SamplePayload> {

    private final Result identity;

    private final SamplePayload payload;

    public enum Result {
        MESSAGE, CONNECT, DISCONNECT
    }
}
