package com.github.demonh3x.binary;

public interface BinaryStorage {
    public BinaryValue read(BinarySection section);
    public void write(BinarySection section, BinaryValue value);
}
