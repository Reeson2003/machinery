package ru.reeson2003.machinery.application.model;

import lombok.Data;

import java.util.List;

@Data
public class SampleModel {

    private final List<String> messages;

    private final long usersOnline;
}
