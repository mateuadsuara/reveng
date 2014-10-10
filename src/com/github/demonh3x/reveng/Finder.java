package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.Alchemist;

import java.util.HashSet;
import java.util.Set;

public class Finder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final RandomReadable readable;
    public Finder(Alchemist<T, byte[]> alchemist, RandomReadable readable) {
        this.alchemist = alchemist;
        this.readable = readable;
    }

    public Set<Integer> find(T value) {
        byte[] expected = alchemist.transmuteForwards(value);

        for (int offset = 0; offset < readable.size(); offset++){
            final byte[] actual = new byte[expected.length];
            for (int i = 0; i < actual.length; i++) {
                actual[i] = readable.read(offset +i);
            }

            if (areEqual(expected, actual)){
                final HashSet<Integer> ret = new HashSet<>();
                ret.add(offset);
                return ret;
            }
        }

        throw new RuntimeException();
    }

    private boolean areEqual(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) return false;

        for (int i = 0; i < expected.length; i++) {
            if (expected[i] != actual[i]) return false;
        }

        return true;
    }
}
