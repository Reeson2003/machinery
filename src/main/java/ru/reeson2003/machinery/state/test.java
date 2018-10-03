package ru.reeson2003.machinery.state;

import ru.reeson2003.machinery.state.api.Action;
import ru.reeson2003.machinery.state.api.Dispatcher;
import ru.reeson2003.machinery.state.api.State;

public class test {

    public static void main(String[] args) {
        Dispatcher<MyState, MyAction> dispatcher = (previousState, action) -> new MyState(previousState.times + 1, action.payload);

        State<MyState, MyAction> state = State.create(new MyState(5, "text"), dispatcher);

        state.listen(System.out::println);

        state.perform(new MyAction("", "new value"));
    }


    private static class MyAction
            implements Action<String> {

        private final String name;

        private final String payload;

        public MyAction(String name, String payload) {
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

    private static class MyState {

        private final int times;

        private final String text;

        public MyState(int times, String text) {
            this.times = times;
            this.text = text;
        }

        @Override
        public String toString() {
            return "MyState{" +
                    "times=" + times +
                    ", text='" + text + '\'' +
                    '}';
        }
    }
}
