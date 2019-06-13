package com.mock.testImpl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.List;

public class ListInterfaceMockTest {

    @Test
    public void testListSize() {
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.size()).thenReturn(2);

        Assert.assertEquals(2, listMock.size());
        Assert.assertEquals(2, listMock.size());
        Assert.assertEquals(2, listMock.size());
    }

    @Test
    public void testListSize_MultipleValueReturn() {
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.size()).thenReturn(2).thenReturn(3);

        Assert.assertEquals(2, listMock.size()); // Will return 2 on first call
        Assert.assertEquals(3, listMock.size()); // Will return 3 on rest calls
        Assert.assertEquals(3, listMock.size());
    }

    @Test
    public void testListGet() {
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.get(0)).thenReturn("0 index");
        Mockito.when(listMock.get(1)).thenReturn("1 index");

        Assert.assertEquals("0 index", listMock.get(0));
        Assert.assertEquals("1 index", listMock.get(1));
        Assert.assertEquals(null, listMock.get(2)); // will return null if it not stubbed
    }

    @Test
    public void testListGet_ArgumentMatcher() {
        List listMock = Mockito.mock(List.class);
        // Matchers.anyInt() is called as Argument Matcher
        Mockito.when(listMock.get(Matchers.anyInt())).thenReturn("Dynamic Index");

        // list will always return Dynamic Index String for any random index used in list due to anyInt() method.
        Assert.assertEquals("Dynamic Index", listMock.get(0));
        Assert.assertEquals("Dynamic Index", listMock.get(1));
    }

    @Test(expected = IllegalStateException.class)
    public void testListGet_ExceptionChecker() {
        List listMock = Mockito.mock(List.class);
        Mockito.when(listMock.get(Matchers.anyInt())).thenThrow(new IllegalStateException("Invalid data"));

        listMock.get(0); // Will throw Exception: java.lang.IllegalStateException: Invalid data
        // using expected attribute of junit @Test annotation to avoid testcase failure.
    }
}

