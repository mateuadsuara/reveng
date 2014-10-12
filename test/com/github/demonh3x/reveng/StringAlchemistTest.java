package com.github.demonh3x.reveng;

import com.github.demonh3x.alchemy.StringAlchemist;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StringAlchemistTest {
    @Test
    public void transmuteBackAndForth() {
        final String value = "Foo";
        final StringAlchemist stringAlchemist = new StringAlchemist();
        assertThat(stringAlchemist.transmuteBackwards(stringAlchemist.transmuteForwards(value)), is(value));
    }
}
