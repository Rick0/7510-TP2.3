package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.xml.generated.*;
import ar.fiuba.tecnicas.tetesteo.TestResult;

import com.thoughtworks.xstream.XStream;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

//TODO FALTA

public class XmlMapper {

	public XmlMapper() {
		xmlGenerator = new XStream();
		xml = new StringBuffer();
		suites = new Root();
		setAliases();
	}

	private XStream xmlGenerator;
	private StringBuffer xml;
	private Root suites;

	public void addTestCaseOnSuite(DAOTestSuite suite, TestResult result) {
		DAOTestCase testcase = new DAOTestCase();
		testcase.setName(result.getTestName());
		testcase.setStatus(result.isSuccess());
		testcase.setTime(result.getTime());
		suite.addTestCase(testcase);
	}

	public void addTestCase(TestResult result) {
		DAOTestCase testcase = new DAOTestCase();
		testcase.setName(result.getTestName());
		testcase.setStatus(result.isSuccess());
		testcase.setTime(result.getTime());
		xml.append(xmlGenerator.toXML(testcase));
	}


	public void addTestSuite(TestSuite suite, Map<String, List<TestResult>> resultLists) {
		DAOTestSuite suiteXml = new DAOTestSuite();
		TestResult suiteResult = getTestResultFromSuite(suite.getName(), ".", resultLists);
		suiteXml.setName(suite.getName());
		suiteXml.setTime(suiteResult.getTime());
		addTestSuiteRecursive(suite.getName(), suiteXml, resultLists);
		suites.addTestSuite(suiteXml);
	}

	private TestResult getTestResultFromSuite(String testName, String suiteName,
			Map<String, List<TestResult>> results) {
		for(TestResult result : results.get(suiteName)) {
			if(testName.equals(result.getTestName())) {
				return result;
			}
		}
		return null;
	}


	private void addTestSuiteRecursive(String currentSuiteName, DAOTestSuite suite, Map<String, List<TestResult>> resultLists) {
		for(TestResult result : resultLists.get(currentSuiteName)) {
			if(result.isSuite()) {
				addTestSuiteRecursive(result.getTestName(), suite, resultLists);
			} else {
				addTestCaseOnSuite(suite, result);
			}
		}
	}

	private void setAliases() {
		xmlGenerator.alias("testsuite", DAOTestSuite.class);
		xmlGenerator.alias("testcase", DAOTestCase.class);
		xmlGenerator.alias("root", Root.class);
	}

	public void print() {
		// TODO Cambiar por metodo de verdad
		PrintWriter writer;
		try {
			writer = new PrintWriter("tetesteo.xml");
			xml.append(xmlGenerator.toXML(this.suites));
			writer.write(xml.toString());
			writer.close();
		} catch(Exception e) {

		}
	}

}
