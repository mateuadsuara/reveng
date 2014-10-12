package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.ByteAlchemist;
import com.github.demonh3x.storage.ReadableByteArray;
import com.github.demonh3x.utils.LiteralMap;
import com.github.demonh3x.utils.LiteralSet;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InCommonFinderTest {
    @Test
    public void OneCommonOffset() {
        final InCommonFinder<Byte> finder = new InCommonFinder<>(
                new ByteAlchemist(),
                LiteralMap.<RandomReadable, Byte>from(
                        new ReadableByteArray(new byte[]{0x00, 0x01, 0x00, 0x00}), (byte) 0x00,
                        new ReadableByteArray(new byte[]{0x03, 0x05, 0x01, 0x01}), (byte) 0x01,
                        new ReadableByteArray(new byte[]{0x03, 0x05, 0x03, 0x01}), (byte) 0x03
                )
        );
        assertThat(new ConsumedSet<>(finder.getOffsets()), is(LiteralSet.from(2)));
    }
}
