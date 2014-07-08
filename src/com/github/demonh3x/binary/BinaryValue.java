package com.github.demonh3x.binary;

import java.util.Arrays;

public final class BinaryValue {
    private final byte[] data;
    private final Long lengthInBits;

    public BinaryValue(byte[] data) {
        this(data, (long) data.length *8);
    }

    public BinaryValue(byte[] data, Long lengthInBits){
        if (data.length == 0)
            throw new IllegalArgumentException("A value can't exist without data!");
        if (lengthInBits < 1L)
            throw new IllegalArgumentException("A value must be at least 1 bit long!");
        if (lengthInBits/8 > data.length)
            throw new IllegalArgumentException("The length shouldn't be bigger than the actual data length!");
        if (data == null || lengthInBits == null)
            throw new NullPointerException("No nulls allowed!");

        this.data = data;
        this.lengthInBits = lengthInBits;
    }

    public Long lengthInBits() {
        return lengthInBits;
    }

    public byte[] toByteArray() {
        Long lengthInBytes = (lengthInBits / 8) +1;
        byte[] ret = Arrays.copyOfRange(data, 0, lengthInBytes.intValue());
        long bitsToMask = (lengthInBytes * 8) - lengthInBits;
        int mask = (0xFF >>> bitsToMask) & 0xFF;
        ret[ret.length -1] = (byte) (ret[ret.length -1] & mask);
        return ret;
    }

    public BinaryValue trim(Long lengthInBits){
        if (lengthInBits > this.lengthInBits())
            throw new IllegalArgumentException("Can't trim to a bigger size than existing data!");

        return new BinaryValue(this.toByteArray(), lengthInBits);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryValue)) return false;

        BinaryValue value = (BinaryValue) obj;
        return this.lengthInBits.equals(value.lengthInBits) &&
                Arrays.equals(this.toByteArray(), value.toByteArray());
    }
}
