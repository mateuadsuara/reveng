package com.github.demonh3x.reveng;

import java.util.HashSet;

public class ConsumedSet<T> extends HashSet<T> {
    public ConsumedSet(Iterable<T> iterable){
        for (T t : iterable)
            this.add(t);
    }
}
