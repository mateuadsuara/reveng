package com.github.demonh3x.reveng;

import java.util.Map;

public class InCommonFinder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final Map<RandomReadable, T> dumpValueMappings;

    public InCommonFinder(Alchemist<T, byte[]> alchemist, Map<RandomReadable, T> dumpValueMappings) {
        this.alchemist = alchemist;
        this.dumpValueMappings = dumpValueMappings;
    }

    public Iterable<Integer> getOffsets() {
        RandomReadable firstReadable = dumpValueMappings.keySet().iterator().next();
        Iterable<Integer> foundOffsets = new NaturalIncrementingSequence(0, firstReadable.size());

        for (Map.Entry<RandomReadable, T> entry : dumpValueMappings.entrySet()) {
            final RandomReadable readable = entry.getKey();
            final T value = entry.getValue();

            final TypeFinder<T> finder = new TypeFinder<>(alchemist, readable, foundOffsets);
            foundOffsets = finder.find(value);
        }

        return foundOffsets;
    }
}