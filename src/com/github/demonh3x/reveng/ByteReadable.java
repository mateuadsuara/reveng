package com.github.demonh3x.reveng;

import java.io.IOException;

public interface ByteReadable {
    /**
     * Read a byte.
     * @param offset The offset where the wanted byte is allocated.
     * @return The read byte.
     * @throws java.io.IOException When the operation couldn't be finished.
     */
    public byte read(int offset) throws IOException;
}
