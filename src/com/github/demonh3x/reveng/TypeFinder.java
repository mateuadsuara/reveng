package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.Alchemist;

public class TypeFinder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final RandomReadable readable;

    public TypeFinder(Alchemist<T, byte[]> alchemist, RandomReadable readable) {
        this.alchemist = alchemist;
        this.readable = readable;
    }

    public Iterable<Integer> find(T value) {
        return new LazyResults(alchemist.transmuteForwards(value), readable);
    }
}
