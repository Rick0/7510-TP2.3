package ar.fiuba.tecnicas.tetesteo.impl;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.OutputStreamWriter;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.Test;
import ar.fiuba.tecnicas.tetesteo.TestExecutor;
import ar.fiuba.tecnicas.tetesteo.TestExecutorImpl;
import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.TestResultCollector;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;

public class TestForPerformanceTest {
	@org.junit.Test
	public void testContextOnSetup() {
		TestExecutorImpl testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Setup test") {

			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int entero = context.getProperty("enteroDePrueba");
				int result = 20/entero;
				Asserts.assertEquals(10, result);
			}

			public void setup(Context context) {
				int entero = 2;
				context.setProperty("enteroDePrueba", entero);
			}			
			
		};
		test.setTimeOut(0.0001);
		testExecutor.execute(test, new ContextImpl());
		org.junit.Assert.assertTrue(testExecutor.getLastTestResult().isSuccess());
	}
	
	@org.junit.Test
	public void regExpFilterTest() {
		String pattern = "regExp.*";
		TestExecutorImpl testExecutor = new TestExecutorImpl(pattern, new OutputStreamWriter(System.out));
		Test test = new AbstractUnitTest("regExpTest") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
			}
		};
		Test test2 = new AbstractUnitTest("Test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
			}
		};
		test.setTimeOut(0.0001);
		test2.setTimeOut(0.00001);
		Context context = new ContextImpl();
		testExecutor.execute(test, context);
		testExecutor.execute(test2, context);
		org.junit.Assert.assertEquals(1, testExecutor.getResults().size());
	}
	
	@org.junit.Test
	public void testContextOnTeardown() {
		TestExecutorImpl testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Setup test") {

			@Override
			public void test(TestExecutor testExecutor, Context context) {
			}

			public void setup(Context context) {
				int intBeforeTeardown = 2;
				context.setProperty("entero", intBeforeTeardown);
			}

			public void teardown(Context context) {
				int intAfterTeardown = 3;
				context.setProperty("entero", intAfterTeardown);
			}
		};
		test.setTimeOut(0.001);
		Context context = new ContextImpl();
		testExecutor.execute(test, context);
		org.junit.Assert.assertEquals(3, context.getProperty("entero"));
	}

}
