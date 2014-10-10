package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.Alchemist;

public class Finder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final RandomReadable readable;
    public Finder(Alchemist<T, byte[]> alchemist, RandomReadable readable) {
        this.alchemist = alchemist;
        this.readable = readable;
    }

    public int find(T value) {
        byte[] expected = alchemist.transmuteForwards(value);

        for (int offset = 0; offset < readable.size(); offset++){
            final byte[] actual = new byte[expected.length];
            for (int i = 0; i < actual.length; i++) {
                actual[i] = readable.read(offset +i);
            }

            if (areEqual(expected, actual))
                return offset;
        }

        return -1;
    }

    private boolean areEqual(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) return false;
        }

        return true;
    }
}
