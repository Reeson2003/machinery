package ru.reeson2003.machinery.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.reeson2003.machinery.api.CombinedState;
import ru.reeson2003.machinery.api.State;
import ru.reeson2003.machinery.application.model.SampleAction;
import ru.reeson2003.machinery.application.model.SampleDispatcher;
import ru.reeson2003.machinery.application.model.SampleModel;

import java.util.Date;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        State<SampleModel, SampleAction> state = State.create(new SampleModel("initial data", 0, new Date()), new SampleDispatcher());
        CombinedState.combine(state, SampleModel.class);
        SpringApplication.run(Application.class, args);
    }
}
