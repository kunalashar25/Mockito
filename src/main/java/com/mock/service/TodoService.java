package com.mock.service;

import java.util.List;

// TodoService is dependency to run SUT.
// TodoService can be an external interface or service created by someother team. There is no actual implementation of TodoService in this project.
public interface TodoService {

    public List<String> retrieveTodos(String user);

    void deleteTodo(String todo);
}
