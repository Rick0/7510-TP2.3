package ar.fiuba.tecnicas.tetesteo.test;


import java.io.StringWriter;
import java.util.LinkedList;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.TestExecutor;
import ar.fiuba.tecnicas.tetesteo.TestRunner;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;
import ar.fiuba.tecnicas.tetesteo.impl.AbstractUnitTest;
import ar.fiuba.tecnicas.tetesteo.impl.TestMain;
import ar.fiuba.tecnicas.tetesteo.impl.TestRunnerImpl;
import ar.fiuba.tecnicas.tetesteo.impl.TestSuite;

public class TestsUnderThisFramework {

	public static class AssertContainsOnContained extends AbstractUnitTest {
		public AssertContainsOnContained() {
			super("Assert Contains on Contained");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			LinkedList<String> testList = new LinkedList<String>();
			String testString = "testString";
			testList.add(testString);
			Asserts.assertContains(testList, testString);
		}
	}

	public static class AssertEqualsOnEquals extends AbstractUnitTest {
		public AssertEqualsOnEquals() {
			super("Assert Equals on Equals");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			int testElement = 1;
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
			Asserts.assertEquals(testElement, 1);
		}
	}

	public static class AssertTrueOnTrue extends AbstractUnitTest {
		public AssertTrueOnTrue() {
			super("Assert True on True");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertTrue(true);
		}
	}

	public static class AssertDistinctOnDistinct extends AbstractUnitTest {
		public AssertDistinctOnDistinct() {
			super("Assert Distinct on Distinct");
		}	
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertDistinct(1, 2);
		}
	}

	public static class AssertGreaterThanOnGreater extends AbstractUnitTest {
		public AssertGreaterThanOnGreater() {
			super("Assert Greater than on Greater");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertGreaterThan(2, 1);
		}
	}


	public static class AssertLowerThanOnLesser extends AbstractUnitTest {
		public AssertLowerThanOnLesser() {
			super("Assert Lower than on Lesser");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertLowerThan(1, 2);
		}
	}

	public static class AssertFalseOnFalse extends AbstractUnitTest {
		public AssertFalseOnFalse() {
			super("Assert False on False");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertFalse(false);
		}
	}

	public void allTestRun() {
		TestRunner runner = new TestRunnerImpl(new StringWriter());
		TestSuite allTests = new TestSuite("All Tests");
		allTests.addTest(new AssertFalseOnFalse());
		allTests.addTest(new AssertTrueOnTrue());
		allTests.addTest(new AssertGreaterThanOnGreater());
		allTests.addTest(new AssertLowerThanOnLesser());
		allTests.addTest(new AssertEqualsOnEquals());
		allTests.addTest(new AssertContainsOnContained());
		allTests.addTest(new AssertDistinctOnDistinct());
		runner.run(allTests);
	}

	public void reportTestRun() {
		TestRunner runner = new TestRunnerImpl(new StringWriter());
		TestSuite suiteA  = new TestSuite("Suite A");
		TestSuite suiteB1 = new TestSuite("Suite B1");
		TestSuite suiteB2 = new TestSuite("Suite B2");
		TestSuite suiteC  = new TestSuite("Suite C");

		suiteA.addTest(new AssertFalseOnFalse());
		suiteA.addTest(new AssertTrueOnTrue());

		suiteB1.addTest(new AssertGreaterThanOnGreater());
		suiteB1.addTest(new AssertLowerThanOnLesser());
		suiteB2.addTest(new AssertEqualsOnEquals());
		suiteB2.addTest(new AssertContainsOnContained());

		suiteA.addTest(suiteB1);
		suiteA.addTest(suiteB2);

		suiteC.addTest(new AssertDistinctOnDistinct());
		suiteB2.addTest(suiteC);

		runner.run(suiteA);
	}

	public static void main(String args[]) {
		TestSuite suiteA  = new TestSuite("Suite A");
		TestSuite suiteB1 = new TestSuite("Suite B1");
		TestSuite suiteB2 = new TestSuite("Suite B2");
		TestSuite suiteC  = new TestSuite("Suite C");

		suiteA.addTest(new AssertFalseOnFalse());
		suiteA.addTest(new AssertTrueOnTrue());

		suiteB1.addTest(new AssertGreaterThanOnGreater());
		suiteB1.addTest(new AssertLowerThanOnLesser());
		suiteB2.addTest(new AssertEqualsOnEquals());
		suiteB2.addTest(new AssertContainsOnContained());

		suiteA.addTest(suiteB1);
		suiteA.addTest(suiteB2);

		suiteC.addTest(new AssertDistinctOnDistinct());
		suiteB2.addTest(suiteC);

		TestMain.doMain(System.out, suiteA);								
		
	}
}
