package com.github.demonh3x.alchemy;

import com.github.demonh3x.reveng.Alchemist;

import java.nio.ByteBuffer;

public class BigEndian4BytesIntegerAlchemist implements Alchemist<Integer, byte[]> {
    @Override
    public byte[] transmuteForwards(Integer integer) {
        return ByteBuffer.allocate(4).putInt(integer).array();
    }

    @Override
    public Integer transmuteBackwards(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }
}
