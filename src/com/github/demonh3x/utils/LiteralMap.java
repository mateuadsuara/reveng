package com.github.demonh3x.utils;

import java.util.HashMap;
import java.util.Map;

public class LiteralMap {
    public static <K, V> Map<K, V> from(Object... keyValuePairs) {
        final HashMap<K, V> map = new HashMap<>();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            map.put((K) keyValuePairs[i], (V) keyValuePairs[i+1]);
        }
        return map;
    }
}
