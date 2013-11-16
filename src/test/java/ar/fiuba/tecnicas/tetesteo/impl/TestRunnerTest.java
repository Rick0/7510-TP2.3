package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.*;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;

import java.io.OutputStreamWriter;

public class TestRunnerTest {

	@org.junit.Test
	public void runTestSuite() {
		TestRunner testRunner = new TestRunnerImpl(new OutputStreamWriter(System.out));
		TestSuite testSuite = new TestSuite("Test suite 1");
		testSuite.addTest(new AbstractUnitTest("Test1") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(true);
			}
		});
		testSuite.addTest(new AbstractUnitTest("Test2") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertFalse(false);
			}
		});
		testRunner.run(testSuite);
	}

	@org.junit.Test
	public void runWithPattern() {
		TestRunner testRunner = new TestRunnerImpl(new OutputStreamWriter(System.out));
		Test test = new AbstractUnitTest("Test") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertFalse(false);
			}
		};
		testRunner.run(test, "DoesntMatch");
	}

}
