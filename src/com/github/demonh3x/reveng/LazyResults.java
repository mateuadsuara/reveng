package com.github.demonh3x.reveng;

import java.util.Iterator;

public class LazyResults implements Iterable<Integer> {
    private final byte[] expected;
    private final RandomReadable readable;

    public LazyResults(byte[] expected, RandomReadable readable) {
        this.expected = expected;
        this.readable = readable;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new It(expected, readable);
    }

    private static class It implements Iterator<Integer> {
        private final byte[] expectedData;
        private final RandomReadable readable;
        private final int lastPossibleOffset;
        private int currentOffset = -1;
        private Integer nextOffset = null;

        private It(byte[] expectedData, RandomReadable readable) {
            this.expectedData = expectedData;
            this.readable = readable;
            this.lastPossibleOffset = readable.size() - expectedData.length;
        }

        @Override
        public boolean hasNext() {
            nextOffset = getNext();
            return nextOffset != null;
        }

        @Override
        public Integer next() {
            return nextOffset;
        }

        private Integer getNext() {
            do {
                if (isFinished()) return null;
                currentOffset++;
            } while (!areEqual(expectedData, readCurrentData()));

            return currentOffset;
        }

        private boolean isFinished() {
            return currentOffset >= lastPossibleOffset;
        }

        private byte[] readCurrentData() {
            final byte[] actual = new byte[expectedData.length];
            for (int i = 0; i < actual.length; i++) {
                actual[i] = readable.read(currentOffset +i);
            }
            return actual;
        }

        private boolean areEqual(byte[] expected, byte[] actual) {
            if (expected.length != actual.length) return false;

            for (int i = 0; i < expected.length; i++) {
                if (expected[i] != actual[i]) return false;
            }

            return true;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}