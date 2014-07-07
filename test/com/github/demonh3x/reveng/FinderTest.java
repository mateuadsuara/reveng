package com.github.demonh3x.reveng;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FinderTest {
    @Test
    public void givenANonExistentValue_shouldReturnNoOffsets(){
        assertFound(
                new byte[]{0x00, 0x01, 0x01, 0x03},
                new byte[]{(byte) 0xFF},
                new ArrayList<Integer>()
        );
    }

    @Test
    public void givenAOneByteValueExistingOnceAtTheBeggining_shouldReturnItsOffset(){
        assertFound(
                new byte[]{0x00, 0x01, 0x01, 0x03},
                new byte[]{0x00},
                Arrays.asList(0)
        );
    }

    @Test
    public void givenAOneByteValueExistingOnceAtTheBegginingB_shouldReturnItsOffset(){
        assertFound(
                new byte[]{0x02, 0x01, 0x01, 0x03},
                new byte[]{0x02},
                Arrays.asList(0)
        );
    }

    @Test
    public void givenAOneByteValueExistingOnceInTheMiddle_shouldReturnItsOffset(){
        assertFound(
                new byte[]{0x02, 0x00, 0x01, 0x03},
                new byte[]{0x00},
                Arrays.asList(1)
        );
    }

    @Test
    public void givenANonExistentTwoByteValue_shouldReturnNoOffsets(){
        assertFound(
                new byte[]{0x02, 0x00, 0x01, 0x03},
                new byte[]{0x03, 0x01},
                new ArrayList<Integer>()
        );
    }

    @Test
    public void givenAOneByteValueExistingTwice_shouldReturnItsOffsets(){
        assertFound(
                new byte[]{0x01, 0x00, 0x01, 0x03},
                new byte[]{0x01},
                Arrays.asList(0, 2)
        );
    }

    @Test
    public void givenATwoByteValueExistingOnceAtTheBeggining_shouldReturnItsOffset(){
        assertFound(
                new byte[]{0x01, 0x00, 0x01, 0x03},
                new byte[]{0x01, 0x00},
                Arrays.asList(0)
        );
    }

    @Test
    public void givenATwoByteValueExistingTwiceInterleaving_shouldReturnItsOffsets(){
        assertFound(
                new byte[]{0x00, 0x00, 0x00, 0x03},
                new byte[]{0x00, 0x00},
                Arrays.asList(0, 1)
        );
    }

    private void assertFound(byte[] data, byte[] value, List<Integer> expectedOffsets) {
        ByteReadable readable = new ReadableByteArray(data);
        Iterable<Integer> actual = new Finder(readable).find(value);
        HashSet<Integer> expected = new HashSet<Integer>();
        expected.addAll(expectedOffsets);
        assertThat(actual, is((Iterable<Integer>) expected));
    }
}