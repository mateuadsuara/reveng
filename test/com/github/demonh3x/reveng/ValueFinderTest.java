package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.Alchemist;
import com.github.demonh3x.alchemy.BigEndian4BytesIntegerAlchemist;
import com.github.demonh3x.alchemy.ByteAlchemist;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ValueFinderTest {
    @Test
    public void findAByteInFirstPlace() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x05},
                (byte) 5,
                set(0)
        );
    }

    @Test
    public void findAByteInSecondPlace() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x00, 0x05},
                (byte) 5,
                set(1)
        );
    }

    @Test
    public void findAnIntegerInFirstPlace() {
        assertFound(
                new BigEndian4BytesIntegerAlchemist(),
                new byte[]{0x00, 0x00, 0x00, 0x05},
                5,
                set(0)
        );
    }

    @Test
    public void findAnIntegerInSecondPlace() {
        assertFound(
                new BigEndian4BytesIntegerAlchemist(),
                new byte[]{0x00, 0x00, 0x00, 0x00, 0x05},
                5,
                set(1)
        );
    }

    @Test
    public void findAByteInTwoPlaces() {
        assertFound(
                new ByteAlchemist(),
                new byte[]{0x00, 0x05, 0x05},
                (byte) 5,
                set(1, 2)
        );
    }

    private static <T> void assertFound(Alchemist<T, byte[]> alchemist, byte[] data, T value, Set<Integer> expectedFindings) {
        Finder<T> finder = new Finder<>(alchemist, new ReadableByteArray(data));
        assertThat(finder.find(value), is(expectedFindings));
    }

    private static <T> Set<T> set(T ... args){
        final HashSet<T> ret = new HashSet<>();
        Collections.addAll(ret, args);
        return ret;
    }
}
