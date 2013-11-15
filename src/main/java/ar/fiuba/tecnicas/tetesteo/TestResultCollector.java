package ar.fiuba.tecnicas.tetesteo;

import java.util.List;
import java.util.Map;

/**
 * Visitor para ir registrando los distintos resultados de los tests
 */
public interface TestResultCollector {

	void collect(TestResult testResult);

	TestResult getLastTestResult();

	TestResult getTestResultFromSuite(String testName, String suiteName);
	
	TestResult.Builder createTestResultBuilder();

	Map<String, List<TestResult>> getResults();
}
