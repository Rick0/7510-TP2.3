package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.*;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;

import java.io.OutputStreamWriter;
//import java.util.regex.Pattern;

import static org.mockito.Mockito.*;


public class AbstractUnitTestTest {

	@org.junit.Test
	public void testFailed_shouldRegisterAFalseSuccess() {

		TestResult.Builder resultBuilder = mock(TestResult.Builder.class);
		TestResultCollector testResultCollector = mock(TestResultCollector.class);
		when(testResultCollector.createTestResultBuilder()).thenReturn(resultBuilder);
		TestExecutorImpl testExecutor = new TestExecutorImpl();
		testExecutor.setTestResultCollector(testResultCollector);

		Test test = new AbstractUnitTest("Simple test") {

			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(false);
			}
		};

		testExecutor.execute(test, new ContextImpl());

		verify(testResultCollector).createTestResultBuilder();
		verify(resultBuilder).withTestName("Simple test");
		verify(resultBuilder).andOnSuccess(eq(false));
		verify(resultBuilder).withMessage("Boolean are not true.");
	}

	@org.junit.Test
	public void testSuccess_shouldRegisterPositiveSuccess() {
		TestResult.Builder resultBuilder = mock(TestResult.Builder.class);
		TestResultCollector testResultCollector = mock(TestResultCollector.class);
		when(testResultCollector.createTestResultBuilder()).thenReturn(resultBuilder);
		TestExecutorImpl testExecutor = new TestExecutorImpl();
		testExecutor.setTestResultCollector(testResultCollector);

		Test test = new AbstractUnitTest("Simple test") {

			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(true);
			}
		};
		testExecutor.execute(test, new ContextImpl());

		verify(testResultCollector).createTestResultBuilder();
	}

	@org.junit.Test
	public void testOnSuite() {
		TestExecutor testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Simple test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(true);
			}
		};
		TestSuite suite = new TestSuite("Test under TestSuite test");
		testExecutor.executeOnSuite(suite.getName(), test, new ContextImpl());
		org.junit.Assert.assertTrue(testExecutor.getLastTestResult().isSuccess());
	}

	@org.junit.Test
	public void testException() {
		TestExecutorImpl testExecutor = new TestExecutorImpl();

		Test test = new AbstractUnitTest("Exception test") {

			@Override
			public void test(TestExecutor testExecutor, Context context) {
				@SuppressWarnings("unused")
				int mathError = 5/0;
			}
		};
		testExecutor.execute(test, new ContextImpl());
		org.junit.Assert.assertTrue(testExecutor.getLastTestResult().getMessage().contains("ERROR"));
	}

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
		testExecutor.execute(test, new ContextImpl());
		org.junit.Assert.assertTrue(testExecutor.getLastTestResult().isSuccess());
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
		Context context = new ContextImpl();
		testExecutor.execute(test, context);
		org.junit.Assert.assertEquals(3, context.getProperty("entero"));
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
		Context context = new ContextImpl();
		testExecutor.execute(test, context);
		testExecutor.execute(test2, context);
		org.junit.Assert.assertEquals(1, testExecutor.getResults().size());
	}

}
