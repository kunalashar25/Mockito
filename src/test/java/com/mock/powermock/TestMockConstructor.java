package com.mock.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SystemUnderTest.class)
public class TestMockConstructor {

    @Mock
    ArrayList mockList;

    @InjectMocks
    SystemUnderTest sut;

    @Test
    public void initialTest() throws Exception {

        List<Integer> list = Arrays.asList(1, 2, 3);

        // mocks constructor of ArrayList.class
        PowerMockito.whenNew(ArrayList.class).withAnyArguments().thenReturn(mockList);

        // initial list size will be 0
        int size = sut.methodUsingAnArrayListConstructor();
        Assert.assertEquals(0, size);

        // mocking to change list size to 10
        Mockito.when(mockList.size()).thenReturn(10);
        size = sut.methodUsingAnArrayListConstructor();
        Assert.assertEquals(10, size);
    }
}
