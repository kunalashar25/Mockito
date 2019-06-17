package com.mock.testImpl;

import com.mock.impl.TodoImpl;
import com.mock.service.TodoService;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

public class TodoImplMockTest {
// 1. Mocking is creating objects that simulate the behavior of real objects.
// 2. Unlike stubs, mocks can be dynamically created from code at runtime.
// 3. Mocks offer more functionality than stubbing.

    @Test
    public void testFilteredMockSize() {
        // No need of using stub class with mockito.
        TodoService mockService = Mockito.mock(TodoService.class);

        List<String> todos = Arrays.asList("Sprint", "Spring");
        Mockito.when(mockService.retrieveTodos("dummyUser")).thenReturn(todos);
        // String value passed during mocking must match with value used during method calling for successful mocking.

        TodoImpl impl = new TodoImpl(mockService);
        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummyUser");

        Assert.assertEquals(1, filteredData.size());
    }

    @Test
    public void testBlankMockSize() {
        // No need of using stub class with mockito.
        TodoService mockService = Mockito.mock(TodoService.class);

        List<String> todos = Arrays.asList();
        Mockito.when(mockService.retrieveTodos("dummyUser")).thenReturn(todos);

        TodoImpl impl = new TodoImpl(mockService);
        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummyUser");

        Assert.assertEquals(0, filteredData.size());
    }

    @Test
    public void deleteTodosNotRelatedToSpring_ArgumentCaptor() {

        // Argument Captor
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        TodoService mockService = Mockito.mock(TodoService.class);

        List<String> todos = Arrays.asList("Spring", "Sprint", "Sprite");
        Mockito.when(mockService.retrieveTodos("dummyUser")).thenReturn(todos);

        TodoImpl impl = new TodoImpl(mockService);
        List<String> filteredData = impl.retrieveTodosRelatedToSpring("dummyUser");

        Assert.assertEquals(0, filteredData.size());

    }

    @Test
    public void deleteTodoNotRelatedToSpring() {

        TodoService mockService = Mockito.mock(TodoService.class);

        List<String> todos = Arrays.asList("Sprint", "Spring");
        Mockito.when(mockService.retrieveTodos("dummyUser")).thenReturn(todos);
        // String value passed during mocking must match with value used during method calling for successful mocking.

        TodoImpl impl = new TodoImpl(mockService);
        impl.deleteTodosNotRelatedToSpring("dummyUser");

        // verify methods in Mockito are used to ensure if a method is being called or not.

        // below statement expects that deleteTodo method is called.
        Mockito.verify(mockService).deleteTodo("Sprint");
        // this step will fail as deleteTodo is never called as it is expecting a string that does not contain Spring word in it.

        // below statement expects that deleteTodo method is never called.
        Mockito.verify(mockService, Mockito.never()).deleteTodo("Spring");

        // below statement is used to verify number of times a method is called
        Mockito.verify(mockService, Mockito.times(1)).deleteTodo("Sprint");
    }
}
