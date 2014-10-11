package com.github.demonh3x.reveng;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LiteralSet {
    @SafeVarargs
    public static <T> Set<T> from(T... elements) {
        final HashSet<T> ret = new HashSet<>();
        Collections.addAll(ret, elements);
        return ret;
    }
}
