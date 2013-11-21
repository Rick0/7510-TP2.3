package ar.fiuba.tecnicas.tetesteo;

import ar.fiuba.tecnicas.tetesteo.impl.TestResultCollectorImpl;
import ar.fiuba.tecnicas.tetesteo.store.TestRunStoreAccess;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.Map;

/**
 * Visitor para ejecutar los distintos tests
 */
public class TestExecutorImpl implements TestExecutor {

	private final Set<String> labels;
	private final Pattern pattern;
	private TestResultCollector testResultCollector;


	public TestExecutorImpl(Set<String> labels, String pattern, Writer writer) {
		this.labels = labels;
		this.pattern = Pattern.compile(pattern);
		this.testResultCollector = new TestResultCollectorImpl(writer);
	}

	public TestExecutorImpl(Set<String> labels, Writer writer) {
		this(labels, ".*", writer);
	}

	public TestExecutorImpl(String pattern, Writer writer) {
		this(new TreeSet<String>(), pattern, writer);
	}

	public TestExecutorImpl(Writer writer) {
		this(new TreeSet<String>(), writer);
	}

	public TestExecutorImpl() {
		this(new TreeSet<String>(), new OutputStreamWriter(System.out));
	}

	public void setTestResultCollector(TestResultCollector testResultCollector) {
		this.testResultCollector = testResultCollector;
	}

	@Override
	public void execute(Test test, Context context) {		
		if (shouldExecute(test)) {
			executeTest(test, testResultCollector.createTestResultBuilder(), context);
		}
	}

	@Override
	public void executeOnSuite(String suiteName, Test test, Context context) {
		if (shouldExecute(test)) {		
			executeTest(test, testResultCollector.createTestResultBuilder().onSuite(suiteName), context);
		}
	}

	private boolean shouldExecute(Test test) {
		if(test.isSkipped()) return false;

		boolean alreadyTested = TestRunStoreAccess.getInstance().isTestOk(test.getName());

		boolean regexMatches = test.isSuite() || pattern.matcher(test.getName()).matches();
		boolean labelsMatches = labels.isEmpty() || hasLabel(test.getLabels());
		return (!alreadyTested) && regexMatches && labelsMatches;

	}

	@Override
	public Map<String, List<TestResult>> getResults() {
		return testResultCollector.getResults();
	}

	@Override
	public TestResult getLastTestResult() {
		return testResultCollector.getLastTestResult();
	}

	private void executeTest(Test test, TestResult.Builder testResultBuilder, Context context) {
		testResultBuilder.withTestName(test.getName());
		testResultBuilder.setSuiteFlag(test.isSuite());
		long inicio = System.nanoTime();
		try {
			test.setup(context);
			test.test(this, context);
			test.teardown(context);
		} catch (AssertionError assertError) {
			testResultBuilder.withMessage(assertError.getMessage());
			testResultBuilder.andOnSuccess(false);
			testResultBuilder.withType(new TestResult.Error().toString());
		} catch (Exception exception) {
			testResultBuilder.withMessage("ERROR: " + exception.toString() + '\n');
			testResultBuilder.andOnSuccess(false);
			testResultBuilder.withType(new TestResult.Failure().toString());
		} finally {
			long durationOnMillis= (System.nanoTime() - inicio) / 1000000;
			if ((test.getTimeOut() < durationOnMillis) && (test.getTimeOut() > 0)){
				testResultBuilder.withMessage("ERROR: TIME OUT" + '\n');
				testResultBuilder.andOnSuccess(false);
				testResultBuilder.withType(new TestResult.Error().toString());
			}
			testResultBuilder.withTime(durationOnMillis);
			testResultCollector.collect(testResultBuilder.build());
		}
	}

	private boolean hasLabel(Set<String> testLabels) {
		Set<String> copyOfTestLabels = new TreeSet<>(testLabels);
		copyOfTestLabels.retainAll(labels);
		return !copyOfTestLabels.isEmpty();
	}


}
