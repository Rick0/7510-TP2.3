package ar.fiuba.tecnicas.tetesteo.xml.generated;

import java.util.ArrayList;
import java.util.List;

import ar.fiuba.tecnicas.tetesteo.xml.generated.DAOTestSuite;

public class Root {
	List<DAOTestSuite> testsuites;
	
	public Root() {
		testsuites = new ArrayList<DAOTestSuite>();
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
	
	public void addTestSuite(DAOTestSuite suite) {
		this.testsuites.add(suite);
	}
	
	private String name;
	private int tests;
	private int failures;
	private int errors;
	private long time;
}
