package ar.fiuba.tecnicas.tetesteo.xml.generated;

import java.util.List;
import ar.fiuba.tecnicas.tetesteo.xml.generated.DAOTestCase;
import java.util.ArrayList;

public class DAOTestSuite {
	private List<DAOTestCase> TestCases;
	private String name;
	private int tests;
	private int failures;
	private int errors;
	private long time;

	public DAOTestSuite() {
		TestCases = new ArrayList<DAOTestCase>();
	}

	public List<DAOTestCase> getTestCases() {
		return TestCases;
	}
	public void setTestCases(List<DAOTestCase> testCases) {
		TestCases = testCases;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTests() {
		return tests;
	}
	public void setTests(int tests) {
		this.tests = tests;
	}
	public int getFailures() {
		return failures;
	}
	public void setFailures(int failures) {
		this.failures = failures;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}

	public void addTestCase(DAOTestCase testCase) {
		this.TestCases.add(testCase);
	}

}
