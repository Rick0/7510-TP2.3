package ar.fiuba.tecnicas.tetesteo;

import java.util.List;
import java.util.Map;
//import ar.fiuba.tecnicas.tetesteo.impl.TestSuite;

public interface TestExecutor {

    void execute(Test test, Context context);

    void executeOnSuite(String suiteName, Test test, Context context);

    Map<String, List<TestResult>> getResults();

    TestResult getLastTestResult();
}
