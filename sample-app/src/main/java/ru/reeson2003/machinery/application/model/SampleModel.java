package ru.reeson2003.machinery.application.model;

import lombok.Data;

import java.util.Date;

@Data
public class SampleModel {

    private final String data;

    private final long id;

    private final Date date;
}
