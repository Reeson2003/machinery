package ru.reeson2003.machinery.api;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MyState {

    private long id;

    private String name;
}
