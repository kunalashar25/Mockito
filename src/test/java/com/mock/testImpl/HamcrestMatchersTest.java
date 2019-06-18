package com.mock.testImpl;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HamcrestMatchersTest {

    @Test
    public void hamcrestIntTest() {
        List<Integer> intList = Arrays.asList(10, 50, 25, 99);

        // check if list has 4 items
        Assert.assertThat(intList, Matchers.<Integer>hasSize(4));

        // check if list contains few items
        Assert.assertThat(intList, Matchers.hasItems(25, 10));

        // to check if all int are greater than 0
        Assert.assertThat(intList, Matchers.everyItem(Matchers.greaterThan(0)));
    }

    @Test
    public void hamcrestArrayTest() {
        Integer[] marks = {5, 12, 30};

        // check if list has 3 items
        Assert.assertThat(marks, Matchers.arrayWithSize(3));

        // check list containing values
        Assert.assertThat(marks, Matchers.arrayContaining(5, 12, 30)); // matches in fixed order
        Assert.assertThat(marks, Matchers.arrayContainingInAnyOrder(12, 5, 30)); // matches in any order
    }
}
