package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.BigEndian4BytesIntegerAlchemist;
import com.github.demonh3x.alchemy.ByteAlchemist;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TypeFinderTest {
    @Test
    public void findAByteInFirstPlace() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x05},
                (byte) 5,
                LiteralSet.from(0)
        );
    }

    @Test
    public void findAByteInsideButNotLast() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x00, 0x05, 0x00},
                (byte) 5,
                LiteralSet.from(1)
        );
    }

    @Test
    public void findAByteInSecondPlace() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x00, 0x05},
                (byte) 5,
                LiteralSet.from(1)
        );
    }

    @Test
    public void findAnIntegerInFirstPlace() {
        assertFound(
                new BigEndian4BytesIntegerAlchemist(),
                new byte[]{0x00, 0x00, 0x00, 0x05},
                5,
                LiteralSet.from(0)
        );
    }

    @Test
    public void findAnIntegerInSecondPlace() {
        assertFound(
                new BigEndian4BytesIntegerAlchemist(),
                new byte[]{0x00, 0x00, 0x00, 0x00, 0x05},
                5,
                LiteralSet.from(1)
        );
    }

    @Test
    public void findAByteInTwoPlaces() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x00, 0x05, 0x05},
                (byte) 5,
                LiteralSet.from(1, 2)
        );
    }

    private static <T> void assertFound(Alchemist<T, byte[]> alchemist, byte[] data, T value, Set<Integer> expectedFindings) {
        TypeFinder<T> typeFinder = new TypeFinder<>(alchemist, new ReadableByteArray(data));

        final HashSet<Integer> offsets = new HashSet<>();
        for (Integer offset : typeFinder.find(value))
            offsets.add(offset);

        assertThat(offsets, is(expectedFindings));
    }
}
