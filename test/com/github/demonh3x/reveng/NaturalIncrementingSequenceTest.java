package com.github.demonh3x.reveng;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class NaturalIncrementingSequenceTest {
    @Test
    public void from0To2() {
        final Iterator<Integer> it = new NaturalIncrementingSequence(0, 2).iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(0));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(false));
    }
}
