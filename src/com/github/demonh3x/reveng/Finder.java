package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.Alchemist;

import java.util.Iterator;

public class Finder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final RandomReadable readable;
    public Finder(Alchemist<T, byte[]> alchemist, RandomReadable readable) {
        this.alchemist = alchemist;
        this.readable = readable;
    }

    public Iterable<Integer> find(T value) {
        return new Results(alchemist.transmuteForwards(value), readable);
    }

    private static class Results implements Iterable<Integer> {
        private final byte[] expected;
        private final RandomReadable readable;

        private Results(byte[] expected, RandomReadable readable) {
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

            private It(byte[] expectedData, RandomReadable readable) {
                this.expectedData = expectedData;
                this.readable = readable;
                this.lastPossibleOffset = readable.size() - expectedData.length;
            }

            @Override
            public boolean hasNext() {
                return currentOffset < lastPossibleOffset;
            }

            @Override
            public Integer next() {
                advance();
                return currentOffset;
            }

            private void advance() {
                do {
                    currentOffset++;
                } while (!areEqual(expectedData, readActualData()));
            }

            private byte[] readActualData() {
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
}
