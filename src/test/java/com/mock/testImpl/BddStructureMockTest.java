package com.mock.testImpl;

import com.mock.impl.TodoImpl;
import com.mock.service.TodoService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Arrays;
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

    @Test
    public void testMethodCalls() {
        // Given
        TodoService mockService = Mockito.mock(TodoService.class);
        List<String> todos = Arrays.asList("Sprint", "Spring");

        BDDMockito.given(mockService.retrieveTodos("dummyUser")).willReturn(todos);

        TodoImpl impl = new TodoImpl(mockService);

        // When
        impl.deleteTodosNotRelatedToSpring("dummyUser");

        // Then
        BDDMockito.then(mockService).should().deleteTodo("Sprint");

        BDDMockito.then(mockService).should(BDDMockito.never()).deleteTodo("Spring");
    }

    @Test
    public void testArgumentCapture() {

        //Declare Argument Captor
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // Given
        TodoService mockService = Mockito.mock(TodoService.class);
        List<String> todos = Arrays.asList("Sprint", "Spring");

        BDDMockito.given(mockService.retrieveTodos("dummyUser")).willReturn(todos);

        TodoImpl impl = new TodoImpl(mockService);

        // When
        impl.deleteTodosNotRelatedToSpring("dummyUser");

        // Define Argument Captor on specific method call
        // Then
        BDDMockito.then(mockService).should().deleteTodo(argumentCaptor.capture());

        // Check captured argument
        Assert.assertThat(argumentCaptor.getValue(), CoreMatchers.is("Sprint"));
    }

    @Test
    public void testArgumentCapture_MultipleCalls() {

        //Declare Argument Captor
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        // Given
        TodoService mockService = Mockito.mock(TodoService.class);
        List<String> todos = Arrays.asList("Sprint", "Spring", "Sprite");

        BDDMockito.given(mockService.retrieveTodos("dummyUser")).willReturn(todos);

        TodoImpl impl = new TodoImpl(mockService);

        // When
        impl.deleteTodosNotRelatedToSpring("dummyUser");

        // Define Argument Captor on specific method call
        // Then
        BDDMockito.then(mockService).should(BDDMockito.times(2)).deleteTodo(argumentCaptor.capture());

        // Check captured argument
        Assert.assertThat(argumentCaptor.getAllValues().size(), CoreMatchers.is(2));
    }
}
