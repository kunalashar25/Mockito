package com.mock.testImpl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class MockitoSpyTest {

    /**
     * Mock vs Spy
     **/
    // Mock object replace mocked class entirely, returning recorded or default values.

    // When spying, you take an existing object and "replace" only some methods. This is useful when you have a huge
    // class and only want to mock certain methods (partial mocking).
    @Test
    public void simpleMockTest() {
        List listMock = Mockito.mock(ArrayList.class);

        // it will return default value
        Assert.assertEquals(0, listMock.size());

        // Stubbing to return size as 5
        Mockito.stub(listMock.size()).toReturn(5);

        // validating size
        Assert.assertEquals(5, listMock.size());

        // we cannot alter mocks so below scenario will return null
        listMock.add("test");
        System.out.println(listMock.get(0)); // Output: null
    }

    @Test
    public void spyTest() {
        // spy gets all logic of a class. It is as good as creating a new List. Spy is used to override specific
        // methods of a class. Spy is also known as partial mock.
        List listSpy = Mockito.spy(ArrayList.class);

        // it will return default value
        Assert.assertEquals(0, listSpy.size());

        // with spy we can alter objects.
        listSpy.add("hello");
        System.out.println(listSpy.get(0)); // Output: hello

        // validating size
        Assert.assertEquals(1, listSpy.size());

        // stubbing size method
        Mockito.stub(listSpy.size()).toReturn(5);
        Assert.assertEquals(5, listSpy.size());

        // Verifying method calling
        Mockito.verify(listSpy, Mockito.never()).clear(); // checks if clear method call is never made
        Mockito.verify(listSpy).add("hello"); // checks if add method call is made with value hello

    }
}
