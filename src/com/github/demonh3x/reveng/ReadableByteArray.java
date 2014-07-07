package com.github.demonh3x.reveng;

import java.io.IOException;

public class ReadableByteArray implements ByteReadable {
    private byte[] data;

    public ReadableByteArray(byte[] data){
        this.data = data;
    }

    @Override
    public byte read(int offset) throws IOException {
        try {
            return this.data[offset];
        } catch (IndexOutOfBoundsException e){
            throw new IOException(e);
        }
    }
}