package com.github.demonh3x.reveng;

import java.util.Iterator;

public class NaturalIncrementingSequence implements Iterable<Integer> {
    private static class It implements Iterator<Integer> {
        private int i;
        private final int finish;

        private It(int start, int finish){
            this.i = start;
            this.finish = finish;
        }

        @Override
        public boolean hasNext() {
            return i < finish;
        }

        @Override
        public Integer next() {
            return i++;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private final int start;
    private final int finish;

    public NaturalIncrementingSequence (int start, int finish) {
        this.start = start;
        this.finish = finish;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new It(start, finish);
    }
}
