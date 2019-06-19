package com.mock.testImpl;

import com.mock.impl.TodoImpl;
import com.mock.service.TodoService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

// @RunWith allows us to run this class with MockitoJUnitRunner so we can use mockito annotations.
@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationsTest {

    // @Mock creates new mock
    @Mock
    TodoService mockService;

    // @InjectMocks scans entire class to see if there are any dependencies required. It'll find TodoSerice as one dependency.
    // It'll automatically check all @Mock annotation to check for matching type and will internally pass appropriate mocks.
    @InjectMocks
    TodoImpl impl;

    // @Captor will automatically create an argument captor of string type
    @Captor
    ArgumentCaptor<String> argumentCaptor;

    @Test
    public void testBlankMockSize() {
        // compare testcase structure with TodoImplMockTest.testBlankMockSize()
        List<String> todos = Arrays.asList();
        Mockito.when(mockService.retrieveTodos("dummyUser")).thenReturn(todos);

        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummyUser");

        Assert.assertEquals(0, filteredData.size());
    }

    @Test
    public void testMockitoAnnotations() {

        // Given
        List<String> todos = Arrays.asList("Sprint", "Spring", "Sprite");
        BDDMockito.given(mockService.retrieveTodos("dummyUser")).willReturn(todos);

        // When
        impl.deleteTodosNotRelatedToSpring("dummyUser");

        // Define Argument Captor on specific method call
        // Then
        BDDMockito.then(mockService).should(BDDMockito.times(2)).deleteTodo(argumentCaptor.capture());

        // Check captured argument
        Assert.assertThat(argumentCaptor.getAllValues().size(), CoreMatchers.is(2));
    }
}
