package ar.fiuba.tecnicas.tetesteo.impl;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.TestExecutor;
import ar.fiuba.tecnicas.tetesteo.TestExecutorImpl;
import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.asserts.impl.Asserts;
import ar.fiuba.tecnicas.tetesteo.store.*;


public class TestRunStoreTest {

	public class GenericOkTest extends AbstractUnitTest {

		GenericOkTest(String name) {
			super(name);
		}

		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertTrue(true);
		}

	}

	public class GenericFailTest extends AbstractUnitTest {
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

	public class GenericErrorTest extends AbstractUnitTest {

		GenericErrorTest(String name) {
			super(name);
		}

		@Override
		public void test(TestExecutor testExecutor, Context context) {
			Asserts.assertTrue(false);
		}

	}



	@Test
	public void TestFailErrorNewWithTXT(){

		createTestRunStoreTxt("TestSuccessfullTestWithTxt.txt");
		TestRunStoreAccess.getInstance().deleteOldStores();

		TestSuite testSuite = getTestForThreeCases();

		Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

		assertTrue(results.get("Test suite").get(0).isSuccess());
		assertTrue(results.get("Test suite").get(1).isError());		
		assertTrue(results.get("Test suite").get(2).isFailure());		//

		createTestRunStoreTxt("TestSuccessfullTestWithTxt.txt");		
		testSuite.addTest(new GenericOkTest("T4"));		
		results = executeTestSuite(testSuite);		
		assertEquals(results.get("Test suite").size(),3);		
	}	

	@Test
	public void ThreeOkOnTXT(){
		createTestRunStoreTxt("ThreeOkOnTxt.txt");		
		TestRunStoreAccess.getInstance().deleteOldStores();
		TestSuite testSuite = getTestForThreeOKs();

		Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

		assertTrue(results.get("Test suite").get(0).isSuccess());
		assertTrue(results.get("Test suite").get(1).isSuccess());		
		assertTrue(results.get("Test suite").get(2).isSuccess());	

		results = executeTestSuite(testSuite);
		assertEquals(results.size(),0);			
	}	


	@Test
	public void RunTestFailErrorNewOnMemory(){
		createTestRunStoreOnMemory();
		TestRunStoreAccess.getInstance().deleteOldStores();

		TestSuite testSuite = getTestForThreeCases();
		Map<String, List<TestResult>> results = executeTestSuite(testSuite);		

		assertTrue(results.get("Test suite").get(0).isSuccess());
		assertTrue(results.get("Test suite").get(1).isError());		
		assertTrue(results.get("Test suite").get(2).isFailure());		//					

		testSuite.addTest(new GenericOkTest("T4"));		
		executeTestSuite(testSuite);		
		assertEquals(results.get("Test suite").size(),3);		
	}	

	@Test
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


	private TestSuite getTestForThreeCases(){				
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericOkTest("T1"));
		testSuite.addTest(new GenericErrorTest("T2"));
		testSuite.addTest(new GenericFailTest("T3"));				
		return testSuite;		
	}

	private TestSuite getTestForThreeOKs(){				
		TestSuite testSuite = new TestSuite("Test suite");
		testSuite.addTest(new GenericOkTest("T1"));
		testSuite.addTest(new GenericOkTest("T2"));
		testSuite.addTest(new GenericOkTest("T3"));				
		return testSuite;		
	}



	private void createTestRunStoreTxt(String path){
		TxtFileTestRunStore store = new TxtFileTestRunStore(path);
		TestRunStoreAccess.getInstance().setTrueCheckOldStores();
		TestRunStoreAccess.getInstance().setTestRunStore(store);
	}

	private void createTestRunStoreOnMemory(){
		MemoryTestRunStore store = new MemoryTestRunStore();
		TestRunStoreAccess.getInstance().setTrueCheckOldStores();
		TestRunStoreAccess.getInstance().setTestRunStore(store);
	}

	private Map<String, List<TestResult>> executeTestSuite(TestSuite testSuite){
		TestExecutor testExecutor = new TestExecutorImpl();
		testExecutor.execute(testSuite, new ContextImpl());
		Map<String, List<TestResult>> results = testExecutor.getResults();
		return results;
	}




}
