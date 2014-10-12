package com.github.demonh3x.reveng;

import java.util.Iterator;

public class LazyResults implements Iterable<Integer> {
    private final byte[] expected;
    private final RandomReadable readable;
    private final Iterable<Integer> offsets;

    public LazyResults(byte[] expected, RandomReadable readable, Iterable<Integer> offsets) {
        this.expected = expected;
        this.readable = readable;
        this.offsets = offsets;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new It(expected, readable, offsets.iterator());
    }

    private static class It implements Iterator<Integer> {
        private final byte[] expectedData;
        private final RandomReadable readable;
        private final Iterator<Integer> offsetsIterator;
        private Integer nextOffset = null;

        private It(byte[] expectedData, RandomReadable readable, Iterator<Integer> offsetsIterator) {
            this.expectedData = expectedData;
            this.readable = readable;
            this.offsetsIterator = offsetsIterator;
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
            Integer offset;
            byte[] actualData;
            boolean found;

            do {
                if (isFinished()) return null;
                offset = offsetsIterator.next();
                try {
                    actualData = readData(offset);
                    found = areEqual(expectedData, actualData);
                } catch (Exception e) {
                    found = false;
                }
            } while (!found);

            return offset;
        }

        private boolean isFinished() {
            return !offsetsIterator.hasNext();
        }

        private byte[] readData(Integer currentOffset) {
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