package ru.reeson2003.machinery.state.api;

import lombok.Data;
import org.junit.Test;

import java.util.Date;

public class CombinedStateImplTest {

    @Test
    public void combineTest() {
        CombinedState combinedState = prepare();

        combinedState.listen(System.out::println, One.class);
        combinedState.listen(System.out::println, Two.class);

        combinedState.perform(new OneAction(Key.SUCCESS, "text - 42"), One.class);
        combinedState.perform(new TwoAction(Key.SUCCESS, 43), Two.class);
    }

    private CombinedState prepare() {
        CombinedStateImpl combinedState = new CombinedStateImpl();
        One one = new One(42, "lorem ipsum");
        State<One, OneAction> oneState = State.create(one, (s, a) -> {
            switch (a.identity) {
                case SUCCESS:
                    return new One(s.id + 1, a.payload);
                case ERROR:
                    return new One(s.id - 1, s.data);
                default:
                    return s;
            }
        });
        Two two = new Two("hello", new Date());
        State<Two, TwoAction> twoState = State.create(two, (s, a) -> {
            switch (a.identity) {
                case SUCCESS:
                    return new Two(s.message + a.payload, new Date());
                case ERROR:
                    return new Two(s.message, new Date());
                default:
                    return s;
            }
        });
        combinedState.combine(oneState, One.class);
        combinedState.combine(twoState, Two.class);
        return combinedState;
    }

    private enum Key {
        SUCCESS, ERROR
    }

    @Data
    private static class One {

        private final int id;

        private final String data;
    }

    @Data
    private static class Two {

        private final String message;

        private final Date date;
    }

    @Data
    private static class OneAction
            implements Action<Key, String> {

        private final Key identity;

        private final String payload;
    }

    @Data
    private static class TwoAction
            implements Action<Key, Integer> {

        private final Key identity;

        private final Integer payload;
    }
}
