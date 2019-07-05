package com.mock.powermock;

import java.util.ArrayList;
import java.util.List;

public class SystemUnderTest {
	private Dependency dependency;

	public int methodUsingAnArrayListConstructor() {
		ArrayList list = new ArrayList();
		return list.size();
	}

	public int methodCallingAStaticMethod() {
		//privateMethodUnderTest calls static method SomeClass.staticMethod
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return UtilityClass.staticMethod(sum);
	}

	private long privateMethodUnderTest() {
		List<Integer> stats = dependency.retrieveAllStats();
		long sum = 0;
		for (int stat : stats)
			sum += stat;
		return sum;
	}

	public static void main(String[] args) {
		System.out.println("Test run");
	}
}
