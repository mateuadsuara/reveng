package com.github.demonh3x.reveng;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Intersect {
    public <T> Set<T> analise(List<Set<T>> sets) {
        final HashSet<T> ret = new HashSet<>(sets.get(0));

        for (int i = 1; i < sets.size(); i++)
            ret.retainAll(sets.get(i));

        return ret;
    }
}
