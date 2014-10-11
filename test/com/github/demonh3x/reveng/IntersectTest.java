package com.github.demonh3x.reveng;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class IntersectTest {
    private void assertInCommon(List<Set<Integer>> sets, Set<Integer> expected) {
        Set<Integer> commonValues = new Intersect().analise(sets);
        assertThat(commonValues, is(expected));
    }

    @Test
    public void GivenTwoSetsOfSameElement_ShouldReturnThatElement() {
        assertInCommon(
                Arrays.asList(
                    LiteralSet.from(1),
                    LiteralSet.from(1)
                ),
                LiteralSet.from(1)
        );
    }

    @Test
    public void GivenTwoSetsWithTheSameElementAtSecondPlace_ShouldReturnThatElement() {
        assertInCommon(
                Arrays.asList(
                    LiteralSet.from(0, 2),
                    LiteralSet.from(1, 2)
                ),
                LiteralSet.from(2)
        );
    }

    @Test
    public void GivenTwoSetsWithACommonElement_ShouldReturnThatElement() {
        assertInCommon(
                Arrays.asList(
                    LiteralSet.from(2, 4),
                    LiteralSet.from(1, 0, 2)
                ),
                LiteralSet.from(2)
        );
    }

    @Test
    public void GivenFourSetsWithACommonElement_ShouldReturnThatElement() {
        assertInCommon(
                Arrays.asList(
                    LiteralSet.from(2, 4, 1),
                    LiteralSet.from(1, 3, 2),
                    LiteralSet.from(4, 2),
                    LiteralSet.from(1, 3, 2)
                ),
                LiteralSet.from(2)
        );
    }
}
