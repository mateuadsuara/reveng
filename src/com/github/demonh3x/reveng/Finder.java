package com.github.demonh3x.reveng;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Finder {
    private final ByteReadable readable;

    public Finder(ByteReadable readable ) {
        this.readable = readable;
    }

    public Set<Integer> find(byte[] value) {
        HashSet<Integer> offsets = new HashSet<Integer>();

        try {
            for (int offset = 0; true; offset++)
                if (isValueAt(value, offset))
                    offsets.add(offset);
        } catch (IOException ignored) {}

        return Collections.unmodifiableSet(offsets);
    }

    private boolean isValueAt(byte[] value, int offset) throws IOException {
        boolean matches = true;

        for (int i = 0; i < value.length && matches; i++)
            matches = this.readable.read(offset + i) == value[i];

        return matches;
    }
}
