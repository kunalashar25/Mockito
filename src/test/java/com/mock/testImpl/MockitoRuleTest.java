package com.mock.testImpl;

import com.mock.impl.TodoImpl;
import com.mock.service.TodoService;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;

public class MockitoRuleTest {

	@Rule // rules should be public to access everything
	public MockitoRule rule = MockitoJUnit.rule();
	// @Rule annotation was introduced inorder to avoid hardcoding of @RunWith annotation. We need to bind a class to run with specific rule.
	// There can only be one @RunWith in a class but we can have multiple @Rule.
	// @Rule will run before and after the test.

	@Mock
	TodoService mockService;

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
