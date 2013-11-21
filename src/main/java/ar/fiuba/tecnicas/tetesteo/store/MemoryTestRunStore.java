package ar.fiuba.tecnicas.tetesteo.store;

import java.util.ArrayList;

// Store que guarda en memoria. En una nueva corrida del programa, se pierden los datos

public class MemoryTestRunStore implements TestRunStore {

	ArrayList<String> testNames;	
	
	public MemoryTestRunStore(){
		testNames = new ArrayList<String>();
	}
	@Override
	public boolean hasStores() {
		return (!testNames.isEmpty());
	}

	@Override
	public boolean isTestOk(String testName) {
		return (testNames.contains(testName));
	}

	@Override
	public void addTestOk(String testName) {
		testNames.add(testName);
	}

	@Override
	public void deleteOldStores() {
		testNames.clear();
	}
	
}
