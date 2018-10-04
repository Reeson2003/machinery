package ru.reeson2003.machinery.state.api;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StateImplTest {

    private String check;

    @Test
    public void performTest() {
        check = "";
        StateImpl<String, MockAction> state = new StateImpl<>("TEST", (s, a) -> {
            if (a.getName().equals("success")) {
                check = a.payload;
                return a.payload;
            } else {
                return s;
            }
        });
        state.perform(new MockAction("error", "ERROR"));
        assertThat(check, is(""));
        state.perform(new MockAction("success", "SUCCESS"));
        assertThat(check, is("SUCCESS"));
    }

    @Test
    public void listenTest() {
        check = "";
        StateImpl<String, MockAction> state = new StateImpl<>("TEST", (s, a) -> a.payload);
        Subscription<String> subscription = state.listen(s -> check = s);
        state.perform(new MockAction("", "ONE"));
        assertThat(check, is("ONE"));
        StateListener<String> unsubscribe = subscription.unsubscribe();
        state.perform(new MockAction("", "TWO"));
        assertThat(check, is("ONE"));
    }

    private static class MockAction
            implements Action<String> {

        private String name;

        private String payload;

        public MockAction(String name, String payload) {
            this.name = name;
            this.payload = payload;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getPayload() {
            return payload;
        }
    }
}