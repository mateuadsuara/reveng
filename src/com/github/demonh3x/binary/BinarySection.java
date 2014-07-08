package com.github.demonh3x.binary;

public final class BinarySection {
    private final Long bitOffset;
    private final Long lengthInBits;

    public BinarySection(Long bitOffset, Long lengthInBits) {
        if (bitOffset < 0L)
            throw new IllegalArgumentException("A negative offset have no meaning!");
        if (lengthInBits < 1L)
            throw new IllegalArgumentException("A section must be at least 1 bit long!");
        if (bitOffset == null || lengthInBits == null)
            throw new NullPointerException("No nulls allowed!");

        this.bitOffset = bitOffset;
        this.lengthInBits = lengthInBits;
    }

    public Long bitOffset() {
        return bitOffset;
    }

    public Long lengthInBits(){
        return lengthInBits;
    }

    @Override
    public int hashCode() {
        return bitOffset.hashCode() + lengthInBits.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinarySection)) return false;

        BinarySection section = (BinarySection) obj;
        return this.bitOffset.equals(section.bitOffset) &&
                this.lengthInBits.equals(section.lengthInBits);
    }
}
