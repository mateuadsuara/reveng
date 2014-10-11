package com.github.demonh3x.storage;

import com.github.demonh3x.reveng.RandomReadable;

import java.io.*;

public class ReadableFile implements RandomReadable {
    private final RandomAccessFile f;

    public ReadableFile(File file) throws FileNotFoundException {
        f = new RandomAccessFile(file, "r");
    }

    @Override
    public byte read(int offset) {
        try {
            f.seek(offset);
            return f.readByte();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        try {
            return (int) f.length();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
