package ru.reeson2003.machinery.state.api;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class CombinedStateTest {

    @Test
    public void create() {
        CombinedState combinedState = CombinedState.create();
        assertThat(combinedState, notNullValue());
    }
}