package ar.fiuba.tecnicas.tetesteo.store;

/* Interfaz genérica que deben cumplir todos los stores */
public interface TestRunStore {

	public boolean hasStores();
	public boolean isTestOk(String testName);
	public void addTestOk(String testName);
	public void deleteOldStores();   

}
