package ar.fiuba.tecnicas.tetesteo.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.impl.TestResultCollectorImpl;
import ar.fiuba.tecnicas.tetesteo.TestResultCollector;

public class TestResultTest {

	@Test
	public void getMessage() {
		String message = new String("message");
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.withMessage(message);
		TestResult tr = resultBuilder.build();
		assertEquals(message, tr.getMessage());
	}

	@Test
	public void withAndOnSuccess() {
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.withSuccess(true);
		resultBuilder.andOnSuccess(true);
		TestResult tr = resultBuilder.build();
		assertEquals(true, tr.isSuccess());
	}
	
	@Test
	public void concatOnMessage() {
		String message = new String("message");
		String concatMessage = new String("concatMessage");
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.withMessage(message);
		resultBuilder.concatOnMessage(concatMessage);
		TestResult tr = resultBuilder.build();
		assertEquals(message + concatMessage, tr.getMessage());
	}
	
	@Test
	public void withTestName() {
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.withTestName("TestName");
		TestResult tr = resultBuilder.build();
		assertEquals("TestName", tr.getTestName());
	}
	
	@Test
	public void withSuitName() {
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.onSuite("SuiteName");
		TestResult tr = resultBuilder.build();
		assertEquals("SuiteName", tr.getSuiteName());
	}
	
	@Test
	public void toStringEquals() {
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		TestResult.Builder anotherResultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.withTestName("TestName");
		resultBuilder.withSuccess(true);
		anotherResultBuilder.withTestName("TestName");
		resultBuilder.withSuccess(true);
		TestResult tr = resultBuilder.build();
		TestResult tr2 = anotherResultBuilder.build();
		assertEquals(tr.toString(), tr2.toString());
	}
	
	@Test
	public void toStringInequals() {
		TestResultCollector testResultCollector = new TestResultCollectorImpl();
		TestResult.Builder resultBuilder = testResultCollector.createTestResultBuilder();
		TestResult.Builder anotherResultBuilder = testResultCollector.createTestResultBuilder();
		resultBuilder.withTestName("TestName");
		resultBuilder.withSuccess(true);
		resultBuilder.andOnSuccess(true);
		anotherResultBuilder.withTestName("AnotherTestName");
		resultBuilder.withSuccess(false);
		anotherResultBuilder.andOnSuccess(false);
		TestResult tr = resultBuilder.build();
		TestResult tr2 = anotherResultBuilder.build();
		assertFalse(tr.toString().equals(tr2.toString()));
	}
}
