package ar.fiuba.tecnicas.tetesteo.impl;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.TestExecutor;
import ar.fiuba.tecnicas.tetesteo.TestExecutorImpl;
import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;


public class TestForPerformanceTest {

	public static class AssertErrorTestPerformance extends AbstractUnitTest {
		public AssertErrorTestPerformance() {
			super("Error Performance Test");
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

		public void setup(Context context){
			setTimeOut(1000);
		}
	}


	public static class AssertOkTestPerformance extends AbstractUnitTest {
		public AssertOkTestPerformance() {
			super("OK Performance Test");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			int testElement = 1;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
			}
			Asserts.assertEquals(testElement, 1);
		}

		public void setup(Context context){
			setTimeOut(1000);
		}
	}


	@org.junit.Test
	public void okPerformanceTest(){
		TestSuite testSuite = new TestSuite("Test suite");	
		testSuite.addTest(new AssertOkTestPerformance());
		Map<String, List<TestResult>> results = executeTestSuite(testSuite);		
		assertTrue(results.get("Test suite").get(0).isSuccess());
	}


	@org.junit.Test
	public void failedPerformanceTest(){
		TestSuite testSuite = new TestSuite("Test suite");	
		testSuite.addTest(new AssertErrorTestPerformance());
		Map<String, List<TestResult>> results = executeTestSuite(testSuite);		
		assertTrue(results.get("Test suite").get(0).isError());
	}


	private Map<String, List<TestResult>> executeTestSuite(TestSuite testSuite){
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		return results;
	}


}
