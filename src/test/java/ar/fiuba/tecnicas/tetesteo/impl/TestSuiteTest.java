package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.*;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;

import static org.junit.Assert.*;

import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.Collection;


public class TestSuiteTest {

	public class GenericTest extends AbstractUnitTest {

		GenericTest(String name) {
			super(name);
		}

		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertTrue(true);
		}
	}

	@org.junit.Test
	public void testSuiteWith2Tests() throws Exception {
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericTest("Test 1"));
		testSuite.addTest(new GenericTest("Test2"));
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		assertTrue(results.get("Test suite").get(0).isSuccess());
		assertTrue(results.get("Test suite").get(1).isSuccess());
	}

	@org.junit.Test
	public void testSuccessOnChildSuitesIsSuccessOnParentSuite() throws Exception {
		TestSuite testSuiteParent = new TestSuite("Test suite");
		TestSuite testSuiteChild = new TestSuite("Test suite child");
		testSuiteChild.addTest(new GenericTest("Test true is true"));

		testSuiteParent.addTest(testSuiteChild);
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuiteParent, new ContextImpl());
		Map<String, List<TestResult>> results =  testExecutor.getResults();
		assertTrue(results.get("Test suite").get(0).isSuccess());
	}

	@org.junit.Test
	public void testFailureOnChildSuitesIsFailureOnParentSuite() throws Exception {
		TestSuite testSuiteParent = new TestSuite("Test suite");
		TestSuite testSuiteChild = new TestSuite("Test suite child");
		testSuiteChild.addTest(new AbstractUnitTest("True must be true") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				Asserts.assertTrue(false);
			}
		});

		testSuiteParent.addTest(testSuiteChild);
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuiteParent, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		assertFalse(results.get("Test suite child").get(0).isSuccess());
		assertFalse(results.get("Test suite").get(0).isSuccess());
	}

	@org.junit.Test
	public void testContextOnSuiteOnSuite() throws Exception {
		TestSuite testSuiteParent = new TestSuite("Test suite");
		TestSuite testSuiteChild = new TestSuite("Test suite child") {
			@Override
			public void setup(Context context) {
				int arbitraryValue = 15;
				context.setProperty("arbitraryValue", arbitraryValue);
			}
		};
		testSuiteChild.addTest(new AbstractUnitTest("True must be true") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int valueFromParentSetup = context.getProperty("arbitraryValue");
				int valueFromOwnSetup = context.getProperty("arbitraryValueInterior");
				Asserts.assertEquals(10, valueFromOwnSetup);
				Asserts.assertEquals(15, valueFromParentSetup);
			}

			@Override
			public void setup(Context context) {
				int arbitraryValueInterior = 10;
				context.setProperty("arbitraryValueInterior", arbitraryValueInterior);
			}
		});

		testSuiteParent.addTest(testSuiteChild);
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuiteParent, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		Collection<List<TestResult>> resLists = results.values();
		for(List<TestResult> list : resLists) {
			for(TestResult result : list) {
				org.junit.Assert.assertTrue(result.isSuccess());
			}
		}
	}

	@org.junit.Test
	public void testSetupTeardownOverwriteFromParents() throws Exception {
		TestSuite testSuiteParent = new TestSuite("Test suite") {
			@Override
			public void setup(Context context) {
				int arbitraryValue = 15;
				context.setProperty("arbitraryValue", arbitraryValue);
			}	
		};
		TestSuite testSuiteChild = new TestSuite("Test suite child") {
			@Override
			public void setup(Context context) {
				int arbitraryValueOverwrite = 5;
				context.setProperty("arbitraryValue", arbitraryValueOverwrite);
			}
		};
		testSuiteChild.addTest(new AbstractUnitTest("True must be true") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int valueFromParentSetup = context.getProperty("arbitraryValue");
				Asserts.assertEquals(5, valueFromParentSetup);
			}
		});

		testSuiteParent.addTest(testSuiteChild);
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuiteParent, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		Collection<List<TestResult>> resLists = results.values();
		for(List<TestResult> list : resLists) {
			for(TestResult result : list) {
				org.junit.Assert.assertTrue(result.isSuccess());
			}
		}
	}


	@org.junit.Test
	public void testSetupTeardownDontOverwriteFromSiblings() throws Exception {
		TestSuite testSuite = new TestSuite("Test suite") {
			@Override
			public void setup(Context context) {
				int arbitraryValue = 5;
				context.setProperty("arbitraryValue", arbitraryValue);
			}
		};
		testSuite.addTest(new AbstractUnitTest("overwriterTest") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int arbitraryValueOverwrite = 4;
				Asserts.assertTrue(true);
				context.setProperty("arbitraryValue", arbitraryValueOverwrite);
			}
		});
		testSuite.addTest(new AbstractUnitTest("shouldNotBeOverwritedTest") {
			@Override
			public void test(TestExecutor testExecutor, Context context) {
				int valueFromParentSetup = context.getProperty("arbitraryValue");
				Asserts.assertEquals(5, valueFromParentSetup);
			}
		});
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		Collection<List<TestResult>> resLists = results.values();
		for(List<TestResult> list : resLists) {
			for(TestResult result : list) {
				org.junit.Assert.assertTrue(result.isSuccess());
			}
		}
	}

	@org.junit.Test
	public void duplicatedTestsTest() {
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericTest("Test 1"));
		testSuite.addTest(new GenericTest("Test 1"));

		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		assertEquals(1, results.get("Test suite").size());
		assertEquals(1, results.get(".").size());
	}

	@org.junit.Test
	public void testWithNotMatchingPattern() {
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericTest("Test 1"));
		String pattern = "DoesntMatch";
		TestExecutor testExecutor = new TestExecutorImpl(pattern, new OutputStreamWriter(System.out));
		testExecutor.execute(testSuite, new ContextImpl());
		org.junit.Assert.assertEquals(1, testExecutor.getResults().size());
	}

	@org.junit.Test
	public void testWithMatchingPattern() {
		TestSuite testSuite = new TestSuite("Suite");
		testSuite.addTest(new GenericTest("Test 1"));
		testSuite.addTest(new GenericTest("XTest 1"));
		String pattern = "Test.*";
		TestExecutor testExecutor = new TestExecutorImpl(pattern, new OutputStreamWriter(System.out));
		testExecutor.execute(testSuite, new ContextImpl());
		org.junit.Assert.assertEquals(2, testExecutor.getResults().size());
	}

}
