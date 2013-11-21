package ar.fiuba.tecnicas.tetesteo.test;


import java.util.List;
import java.util.Map;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.TestExecutor;
import ar.fiuba.tecnicas.tetesteo.TestExecutorImpl;
import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;
import ar.fiuba.tecnicas.tetesteo.impl.AbstractUnitTest;
import ar.fiuba.tecnicas.tetesteo.impl.ContextImpl;
import ar.fiuba.tecnicas.tetesteo.impl.TestMain;
import ar.fiuba.tecnicas.tetesteo.impl.TestSuite;

public class TestForPerformanceOnFramework {

	
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
	
	
	public static class OkPerformanceTest extends AbstractUnitTest {
		public OkPerformanceTest() {
			super("OkPerformanceTest");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			boolean isSuccess = context.getProperty("isSuccess");			
			Asserts.assertTrue(isSuccess);
		}

		@Override
		public void setup(Context context) {						
			TestSuite testSuite = new TestSuite("Test suite");	
			testSuite.addTest(new AssertOkTestPerformance());
			Map<String, List<TestResult>> results = executeTestSuite(testSuite);		
			context.setProperty("isSuccess",results.get("Test suite").get(0).isSuccess());
		}
	}
	
	
	public static class FailedPerformanceTest extends AbstractUnitTest {
		public FailedPerformanceTest() {
			super("FailedPerformanceTest");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			boolean isError = context.getProperty("isError");			
			Asserts.assertTrue(isError);
		}

		@Override
		public void setup(Context context) {						
			TestSuite testSuite = new TestSuite("Test suite");	
			testSuite.addTest(new AssertErrorTestPerformance());
			Map<String, List<TestResult>> results = executeTestSuite(testSuite);					
			context.setProperty("isError",results.get("Test suite").get(0).isError());
		}
	}
	
	private static Map<String, List<TestResult>> executeTestSuite(TestSuite testSuite){
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		return results;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestSuite suiteA  = new TestSuite("Suite TestPerformance");
		suiteA.addTest(new OkPerformanceTest());
		suiteA.addTest(new FailedPerformanceTest());
		TestMain.doMain(System.out, suiteA);
	}	
	
}
