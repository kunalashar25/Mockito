package com.mock.testImpl;

import com.mock.impl.TodoImpl;
import com.mock.service.TodoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

// @RunWith allows us to this run this class with MockitoRunner so we can use mockito annotations.
@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationsTest {

    // @Mock Used to mock any class
    @Mock
    TodoService mockService;

    // @InjectMocks scans entire class to see if there are any dependencies required. It'll find TodoSerice as one dependency.
    // It'll automtically check all @Mock annotation to check for matching type and will internally pass appropriate mocks.
    @InjectMocks
    TodoImpl impl;

    @Test
    public void testBlankMockSize() {
        // compare testcase structure with TodoImplMockTest.testBlankMockSize()
        List<String> todos = Arrays.asList();
        Mockito.when(mockService.retrieveTodos("dummyUser")).thenReturn(todos);

        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummyUser");

        Assert.assertEquals(0, filteredData.size());
    }
}
