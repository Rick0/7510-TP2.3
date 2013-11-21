package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.TestReporter;
import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.TestResultCollector;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestResultCollectorImpl implements TestResultCollector {

	private Map<String, List<TestResult>> results;

	private TestResult last;

	private TestReporter reporter;

	public TestResultCollectorImpl(Writer writer) {
		results = new HashMap<String, List<TestResult>>();
		reporter = new TestConsoleReporter(writer);
	}

	public TestResultCollectorImpl() {
		results = new HashMap<String, List<TestResult>>();
		reporter = new TestConsoleReporter(new OutputStreamWriter(System.out));
	}

	@Override
	public void collect(TestResult testResult) {
		last = testResult;
		if(!results.containsKey(testResult.getSuiteName())) {
			ArrayList<TestResult> suiteResults = new ArrayList<TestResult>();
			suiteResults.add(testResult);
			results.put(testResult.getSuiteName(), suiteResults);
		} else {
			List<TestResult> suiteResults = results.get(testResult.getSuiteName());
			suiteResults.add(testResult);
		}
		reporter.report(testResult);
	}

	@Override
	public TestResult getLastTestResult() {
		return last;
	}

	@Override
	public TestResult.Builder createTestResultBuilder() {
		return new TestResultImpl.Builder();
	}

	@Override
	public Map<String, List<TestResult>> getResults() {
		return results;
	}

	public TestResult getTestResultFromSuite(String testName, String suiteName) {
		for(TestResult result : results.get(suiteName)) {
			if(testName.equals(result.getTestName())) {
				return result;
			}
		}
		return null;
	}

}
