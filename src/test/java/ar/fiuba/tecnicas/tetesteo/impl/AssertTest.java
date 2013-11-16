package ar.fiuba.tecnicas.tetesteo.impl;

import java.util.LinkedList;

import ar.fiuba.tecnicas.tetesteo.*;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;


public class AssertTest {

	@org.junit.Test
	public void AssertContainsOnContained() {
		TestExecutor testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				LinkedList<String> testList = new LinkedList<>();
				String testString = "testString";
				testList.add(testString);
				Asserts.assertContains(testList, testString);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult();
		org.junit.Assert.assertTrue(result.isSuccess());
	}

	@org.junit.Test
	public void AssertContainsOnNonContained() {
		TestExecutor testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				LinkedList<String> testList = new LinkedList<String>();
				String testString = "testString";
				Asserts.assertContains(testList, testString);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult();
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertEqualsOnEquals() {
		TestExecutor testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int testElement = 1;
				Asserts.assertEquals(testElement, 1);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertTrue(result.isSuccess());
	}

	@org.junit.Test
	public void AssertEqualsOnInequals() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int testElement = 1;
				Asserts.assertEquals(testElement, 2);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertTrueOnTrue() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(true);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertTrue(result.isSuccess());
	}

	@org.junit.Test
	public void AssertTrueOnFalse() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(false);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertDistinctOnEquals() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertDistinct(1, 1);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertDistinctOnDistinct() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertDistinct(1, 2);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertTrue(result.isSuccess());
	}

	@org.junit.Test
	public void AssertGreaterThanOnGreater() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertGreaterThan(2, 1);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertTrue(result.isSuccess());
	}

	@org.junit.Test
	public void AssertGreaterThanOnLesser() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertGreaterThan(1, 2);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertLowerThanOnGreater() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertLowerThan(2, 1);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertLowerThanOnLesser() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertLowerThan(1, 2);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertTrue(result.isSuccess());
	}

	@org.junit.Test
	public void AssertFalseOnTrue() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertFalse(true);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult(); 
		org.junit.Assert.assertFalse(result.isSuccess());
	}

	@org.junit.Test
	public void AssertFalseOnFalse() {
		TestExecutor testExecutor = new TestExecutorImpl();
		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertFalse(false);
			}
		};
		testExecutor.execute(test, new ContextImpl());
		TestResult result = testExecutor.getLastTestResult();
		org.junit.Assert.assertTrue(result.isSuccess());
	}

}
