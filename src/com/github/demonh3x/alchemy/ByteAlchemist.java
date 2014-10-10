package com.github.demonh3x.alchemy;

import com.github.demonh3x.alchemy.Alchemist;

public class ByteAlchemist implements Alchemist<Byte, byte[]>{
    @Override
    public byte[] transmuteForwards(Byte aByte) {
        return new byte[]{aByte};
    }

    @Override
    public Byte transmuteBackwards(byte[] bytes) {
        return bytes[0];
    }
}
