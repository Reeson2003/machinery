package ru.reeson2003.machinery.state.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class StateTest {

    @Test
    public void createTest() {
        State<String, Action<?, ?>> test = State.create("TEST", (s, a) -> s);
        assertThat(test, notNullValue());
    }
}