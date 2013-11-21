package ar.fiuba.tecnicas.tetesteo.store;

/* Clase que es una combinacion entre Singleton y Proxy para controlar el acceso
 * a la utilizacion del TestRunStore. Si no se especifica un TestRunStore especifico,
 * por defecto utilizara TxtFileTestRunStore.
 */
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
		testRunStore = test;
	}

	public void setTrueCheckOldStores(){
		checkOldStores = true;
	}

	public boolean hasStores(){
		setDefaultStore();
		return testRunStore.hasStores();
	}

	public boolean isTestOk(String testName){
		setDefaultStore();
		return checkOldStores && testRunStore.isTestOk(testName);
	}

	public void addTestOk(String testName){
		if (checkOldStores){
			setDefaultStore();
			testRunStore.addTestOk(testName);
		}
	}

	public void deleteOldStores(){
		setDefaultStore();
		testRunStore.deleteOldStores();
	}

	private void setDefaultStore(){
		if (testRunStore == null){
			testRunStore = new TxtFileTestRunStore("Reporte.txt");
		}
	}	

}
