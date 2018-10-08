package ru.reeson2003.machinery.impl;

import org.junit.Test;
import ru.reeson2003.machinery.api.Action;
import ru.reeson2003.machinery.api.StateListener;
import ru.reeson2003.machinery.api.Subscription;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StateImplTest {

    private String check;

    @Test
    public void performTest() {
        check = "";
        StateImpl<String, MockAction> state = new StateImpl<>("TEST", (s, a) -> {
            switch (a.getIdentity()) {
                case SUCCESS: {
                    check = a.payload;
                    return a.payload;
                }
                case ERROR: {
                    return s;
                }
                default:
                    return s;
            }
        });
        state.perform(new MockAction(Result.ERROR, "ERROR"));
        assertThat(check, is(""));
        state.perform(new MockAction(Result.SUCCESS, "SUCCESS"));
        assertThat(check, is("SUCCESS"));
    }

    @Test
    public void listenTest() {
        check = "";
        StateImpl<String, MockAction> state = new StateImpl<>("TEST", (s, a) -> a.payload);
        Subscription<String> subscription = state.listen(s -> check = s);
        state.perform(new MockAction(Result.SUCCESS, "ONE"));
        assertThat(check, is("ONE"));
        StateListener<String> unsubscribe = subscription.unsubscribe();
        state.perform(new MockAction(Result.SUCCESS, "TWO"));
        assertThat(check, is("ONE"));
    }

    private enum Result {
        SUCCESS,
        ERROR
    }

    private static class MockAction
            implements Action<Result, String> {

        private Result result;

        private String payload;

        public MockAction(Result result, String payload) {
            this.result = result;
            this.payload = payload;
        }

        @Override
        public Result getIdentity() {
            return result;
        }

        @Override
        public String getPayload() {
            return payload;
        }
    }
}