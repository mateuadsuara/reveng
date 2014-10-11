package com.github.demonh3x.storage;

import com.github.demonh3x.reveng.RandomReadable;

public class ReadableByteArray implements RandomReadable {
    private byte[] data;
    public ReadableByteArray(byte[] data){
        this.data = data;
    }

    @Override
    public byte read(int offset) {
        return this.data[offset];
    }

    @Override
    public int size() {
        return data.length;
    }
}