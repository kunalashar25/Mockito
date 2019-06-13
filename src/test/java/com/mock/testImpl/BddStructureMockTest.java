package com.mock.testImpl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

public class BddStructureMockTest {

    @Test
    public void testListSize() {
        // Given
        List<String> listMock = Mockito.mock(List.class);
        BDDMockito.given(listMock.get(Mockito.anyInt())).willReturn("test");

        // When
        String s = listMock.get(0);

        // Then
        Assert.assertThat("test", CoreMatchers.is(s));
    }
}
