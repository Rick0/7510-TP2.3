package ar.fiuba.tecnicas.tetesteo.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
import ar.fiuba.tecnicas.tetesteo.store.MemoryTestRunStore;
import ar.fiuba.tecnicas.tetesteo.store.TestRunStoreAccess;
import ar.fiuba.tecnicas.tetesteo.store.TxtFileTestRunStore;


/* Clase destinada a realizar los tests de Stores usando el framework del TP */

public class TestRunStoreOnFramework {

	/**
	 * @param args
	 */

	public static class AssertTestFailErrorNewWithTxt extends AbstractUnitTest {
		int ValueA;
		int ValueB;
		public AssertTestFailErrorNewWithTxt() {
			super("TestFailErrorNewWithTXT");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			ValueA = context.getProperty("ValueA");
			ValueB = context.getProperty("ValueB");
			Asserts.assertEquals(ValueA, ValueB);
		}

		@Override
		public void setup(Context context) {						
			createTestRunStoreTxt("TestSuccessfullTestWithTxt.txt");
			TestRunStoreAccess.getInstance().deleteOldStores();
			TestSuite testSuite = getTestForThreeCases();
			Map<String, List<TestResult>> results = executeTestSuite(testSuite);
			createTestRunStoreTxt("TestSuccessfullTestWithTxt.txt");		
			testSuite.addTest(new GenericOkTest("T4"));		
			results = executeTestSuite(testSuite);					
			context.setProperty("ValueA", results.get("Test suite").size());
			context.setProperty("ValueB", 3);						
		}
	}


	public static class AssertThreeOkOnTXT extends AbstractUnitTest {
		int ValueA;
		int ValueB;
		public AssertThreeOkOnTXT() {
			super("ThreeOkOnTXT");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			ValueA = context.getProperty("ValueA");
			ValueB = context.getProperty("ValueB");
			Asserts.assertEquals(ValueA, ValueB);
		}

		@Override
		public void setup(Context context) {						
			createTestRunStoreTxt("ThreeOkOnTxt.txt");		
			TestRunStoreAccess.getInstance().deleteOldStores();
			TestSuite testSuite = getTestForThreeOKs();

			Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

			results = executeTestSuite(testSuite);
			context.setProperty("ValueA",results.size());
			context.setProperty("ValueB",0);			
		}
	}


	public static class AssertTestFailErrorNewOnMemory extends AbstractUnitTest {
		int ValueA;
		int ValueB;
		public AssertTestFailErrorNewOnMemory() {
			super("TestFailErrorNewOnMemory");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			ValueA = context.getProperty("ValueA");
			ValueB = context.getProperty("ValueB");
			Asserts.assertEquals(ValueA, ValueB);
		}

		@Override
		public void setup(Context context) {


			createTestRunStoreOnMemory();
			TestRunStoreAccess.getInstance().deleteOldStores();

			TestSuite testSuite = getTestForThreeCases();
			Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

			testSuite.addTest(new GenericOkTest("T4"));		
			results = executeTestSuite(testSuite);					
			context.setProperty("ValueA", results.get("Test suite").size());
			context.setProperty("ValueB", 3);						
		}
	}


	public static class AssertThreeOkOnMemory extends AbstractUnitTest {
		int ValueA;
		int ValueB;
		public AssertThreeOkOnMemory() {
			super("ThreeOkOnMemory");
		}
		@Override
		public void test(TestExecutor testExecutor, Context context) {
			ValueA = context.getProperty("ValueA");
			ValueB = context.getProperty("ValueB");
			Asserts.assertEquals(ValueA, ValueB);
		}

		@Override
		public void setup(Context context) {						
			createTestRunStoreOnMemory();		
			TestRunStoreAccess.getInstance().deleteOldStores();
			TestSuite testSuite = getTestForThreeOKs();

			Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

			results = executeTestSuite(testSuite);
			context.setProperty("ValueA",results.size());
			context.setProperty("ValueB",0);			
		}
	}





	public static class GenericOkTest extends AbstractUnitTest {

		GenericOkTest(String name) {
			super(name);
		}

		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertTrue(true);
		}

	}

	public static class GenericFailTest extends AbstractUnitTest {
		List<String> myList;
		String b;

		GenericFailTest(String name) {
			super(name);
		}

		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertContains(myList, b);
		}

	}

	public static class GenericErrorTest extends AbstractUnitTest {

		GenericErrorTest(String name) {
			super(name);
		}

		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertTrue(false);
		}

	}





	public void ThreeOkOnMemory(){
		createTestRunStoreOnMemory();
		TestRunStoreAccess.getInstance().deleteOldStores();
		TestSuite testSuite = getTestForThreeOKs();

		Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

		assertTrue(results.get("Test suite").get(0).isSuccess());
		assertTrue(results.get("Test suite").get(1).isSuccess());		
		assertTrue(results.get("Test suite").get(2).isSuccess());	

		results = executeTestSuite(testSuite);
		assertEquals(results.size(),0);			
	}


	private static  TestSuite getTestForThreeCases(){				
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericOkTest("T1"));
		testSuite.addTest(new GenericErrorTest("T2"));
		testSuite.addTest(new GenericFailTest("T3"));				
		return testSuite;		
	}

	private static TestSuite getTestForThreeOKs(){				
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericOkTest("T1"));
		testSuite.addTest(new GenericOkTest("T2"));
		testSuite.addTest(new GenericOkTest("T3"));				
		return testSuite;		
	}



	private static void createTestRunStoreTxt(String path){
		TxtFileTestRunStore store = new TxtFileTestRunStore(path);
		TestRunStoreAccess.getInstance().setTrueCheckOldStores();
		TestRunStoreAccess.getInstance().setTestRunStore(store);
	}

	private static void createTestRunStoreOnMemory(){
		MemoryTestRunStore store = new MemoryTestRunStore();
		TestRunStoreAccess.getInstance().setTrueCheckOldStores();
		TestRunStoreAccess.getInstance().setTestRunStore(store);
	}

	private static Map<String, List<TestResult>> executeTestSuite(TestSuite testSuite){
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		return results;
	}



	public static void main(String[] args) {
		TestSuite suiteA  = new TestSuite("Suite TestRunStore");
		suiteA.addTest(new AssertTestFailErrorNewWithTxt());
		suiteA.addTest(new AssertThreeOkOnTXT());
		suiteA.addTest(new AssertTestFailErrorNewOnMemory());
		suiteA.addTest(new AssertThreeOkOnMemory());
		TestMain.doMain(System.out, suiteA);								
	}




}
