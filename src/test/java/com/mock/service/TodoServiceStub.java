package com.mock.service;

import java.util.Arrays;
import java.util.List;

public class TodoServiceStub implements TodoService {
    // Issues with Stubs:
    // 1. Need to add a lot of conditions to test all logic.
    // 2. Need to maintain stub a lot if there is any addition or deletion of method in the interface.

    public List<String> retrieveTodos(String user) {
        return Arrays.asList("Core Java", "Spring Tutorial", "Advance Java", "Spring Configurations");
    }

    public void deleteTodo(String todo) {
        // adding unimplemented method
    }
}
