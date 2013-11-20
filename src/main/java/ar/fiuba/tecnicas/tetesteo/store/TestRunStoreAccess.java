package ar.fiuba.tecnicas.tetesteo.store;

public class TestRunStoreAccess {
	private TestRunStore testRunStore;
	private boolean checkOldStores;
	private static TestRunStoreAccess singleton = new TestRunStoreAccess();		
	

	private TestRunStoreAccess(){
		testRunStore = null;
		checkOldStores = false;
	}

	public static TestRunStoreAccess getInstance( ) {		
		return singleton;
	}
		
	public void setTestRunStore(TestRunStore test){		
		singleton.testRunStore = test;
	}
	
	public void setTrueCheckOldStores(){
		checkOldStores = true;
	}
		
	public boolean hasStores(){
		setDefaultStore();
		return singleton.testRunStore.hasStores();
	}

	public boolean isTestOk(String testName){
		setDefaultStore();
		return checkOldStores && singleton.testRunStore.isTestOk(testName);
	}

	public void addTestOk(String testName){
		setDefaultStore();
		singleton.testRunStore.addTestOk(testName);
	}


	public void deleteOldStores(){
		setDefaultStore();
		singleton.testRunStore.deleteOldStores();
	}
	
	private void setDefaultStore(){
		if (singleton.testRunStore == null){
			singleton.testRunStore = new TxtFileTestRunStore("Reporte.txt");
		}
	}	
	


}
