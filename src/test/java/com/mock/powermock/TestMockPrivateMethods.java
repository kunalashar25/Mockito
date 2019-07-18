package com.mock.powermock;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

@RunWith(PowerMockRunner.class)
public class TestMockPrivateMethods {

    @Mock
    Dependency dependency;

    @InjectMocks
    SystemUnderTest sut;

    @Test
    public void initialTest() throws Exception {

        List<Integer> list = Arrays.asList(1, 2, 3);

        Mockito.when(dependency.retrieveAllStats()).thenReturn(list);

        // below statement is used to call private method of a class
        long result = Whitebox.invokeMethod(sut, "privateMethodUnderTest");

        System.out.println(result);
        Assert.assertEquals(6, result);
    }
}
