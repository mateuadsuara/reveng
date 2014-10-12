package com.github.demonh3x.reveng;

public class TypeFinder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final RandomReadable readable;
    private final Iterable<Integer> offsets;

    public TypeFinder(Alchemist<T, byte[]> alchemist, RandomReadable readable, Iterable<Integer> offsets) {
        this.alchemist = alchemist;
        this.readable = readable;
        this.offsets = offsets;
    }

    public Iterable<Integer> find(T value) {
        return new LazyResults(alchemist.transmuteForwards(value), readable, offsets);
    }
}
