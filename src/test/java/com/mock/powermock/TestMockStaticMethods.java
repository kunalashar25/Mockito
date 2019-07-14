package com.mock.powermock;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Arrays;
import java.util.List;

// use powermock runner class for mock static methods
@RunWith(PowerMockRunner.class)
@PrepareForTest(UtilityClass.class) // Prepares class to mock static methods.
public class TestMockStaticMethods {

	@Mock
	Dependency dependency;

	@InjectMocks
	SystemUnderTest sut;

	@Test
	public void initialTest() {

		List<Integer> list = Arrays.asList(1, 2, 3);

		Mockito.when(dependency.retrieveAllStats()).thenReturn(list);

		// Initialize class having static methods
		PowerMockito.mockStatic(UtilityClass.class);
		Mockito.when(UtilityClass.staticMethod(6)).thenReturn(150);

		sut.methodCallingAStaticMethod();
	}

}
