package com.github.demonh3x.reveng;

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
        Finder<Byte> finder = new Finder<>(new ByteAlchemist(), new ReadableByteArray(new byte[]{0x05}));
        assertThat(finder.find((byte) 5), is(set(0)));
    }

    @Test
    public void findAByteInSecondPlace() {
        Finder<Byte> finder = new Finder<>(new ByteAlchemist(), new ReadableByteArray(new byte[]{0x00, 0x05}));
        assertThat(finder.find((byte) 5), is(set(1)));
    }

    @Test
    public void findAnIntegerInFirstPlace() {
        Finder<Integer> finder = new Finder<>(new BigEndian4BytesIntegerAlchemist(), new ReadableByteArray(new byte[]{0x00, 0x00, 0x00, 0x05}));
        assertThat(finder.find(5), is(set(0)));
    }

    @Test
    public void findAnIntegerInSecondPlace() {
        Finder<Integer> finder = new Finder<>(new BigEndian4BytesIntegerAlchemist(), new ReadableByteArray(new byte[]{0x00, 0x00, 0x00, 0x00, 0x05}));
        assertThat(finder.find(5), is(set(1)));
    }

    private static <T> Set<T> set(T ... args){
        final HashSet<T> ret = new HashSet<>();
        Collections.addAll(ret, args);
        return ret;
    }
}
