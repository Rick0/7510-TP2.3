package ar.fiuba.tecnicas.tetesteo.store;

public class TestRunStoreAccess {
	private TestRunStore testRunStore;	
	private static TestRunStoreAccess singleton = new TestRunStoreAccess();		
	

	private TestRunStoreAccess(){
		testRunStore = null;
	}

	public static TestRunStoreAccess getInstance( ) {		
		return singleton;
	}
		
	public static void setTestRunStore(TestRunStore test){		
		singleton.testRunStore = test;
	}
		
	public boolean hasStores(){
		setDefaultStore();
		return singleton.testRunStore.hasStores();
	}

	public boolean isTestOk(String testName){
		setDefaultStore();
		return singleton.testRunStore.isTestOk(testName);
	}

	public void addTestOk(String testName){
		setDefaultStore();
		singleton.testRunStore.addTestOk(testName);
	}


	public void deleteOldStores(){
		setDefaultStore();
		singleton.testRunStore.deleteOldStores();
	}
	
	private static void setDefaultStore(){
		if (singleton.testRunStore == null){
			singleton.testRunStore = new TxtFileTestRunStore("Reporte.txt");
		}
	}	
	


}
