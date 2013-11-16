package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.*;

//import java.io.OutputStream;
import java.io.Writer;
//import java.util.regex.*;

//import java.util.Collection;

import java.util.Map;

import java.util.List;

public class TestRunnerImpl implements TestRunner {

    private TestExecutor testExecutor;

    public TestRunnerImpl(Writer writer) {
        testExecutor = new TestExecutorImpl(writer);
    }

    @Override
	public void run(Test test) {
        testExecutor.execute(test, new ContextImpl());

		@SuppressWarnings("unused")
		Map<String, List<TestResult>> results = testExecutor.getResults();
		// resultados
	}

	
	@Override
	public void run(Test test, String regularExpresion) {
        testExecutor.execute(test, new ContextImpl());

        @SuppressWarnings("unused")
		Map<String, List<TestResult>> results = testExecutor.getResults();
        // resultados
	}
}
