package com.github.demonh3x.reveng;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class EasyFinder<T> {
    private final Alchemist<T, byte[]> alchemist;
    private final Map<RandomReadable, T> dumpValueMappings;

    public EasyFinder(Alchemist<T, byte[]> alchemist, Map<RandomReadable, T> dumpValueMappings) {
        this.alchemist = alchemist;
        this.dumpValueMappings = dumpValueMappings;
    }

    public Set<Integer> getOffsets() {
        final ArrayList<Set<Integer>> results = new ArrayList<>();

        for (Map.Entry<RandomReadable, T> entry : dumpValueMappings.entrySet()) {
            final RandomReadable readable = entry.getKey();
            final T value = entry.getValue();
            results.add(
                    new ConsumedSet<>(
                            new TypeFinder<>(
                                    alchemist,
                                    readable
                            ).find(value)
                    )
            );
        }

        return new Intersect().analise(results);
    }
}