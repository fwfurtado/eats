package me.fwfurtado;

import java.util.StringJoiner;

class Counter {
    private Long counter;

    /**
     * @deprecated frameworks only
     */
    @Deprecated
    private Counter() {
    }

    public Counter(Long counter) {
        this.counter = counter;
    }

    public Long getCounter() {
        return counter;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Counter.class.getSimpleName() + "[", "]")
            .add("counter=" + counter)
            .toString();
    }
}
