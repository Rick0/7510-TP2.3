package ar.fiuba.tecnicas.tetesteo.impl;

import java.io.IOException;
import java.io.Writer;

import ar.fiuba.tecnicas.tetesteo.TestReporter;
import ar.fiuba.tecnicas.tetesteo.TestResult;

public class TestConsoleReporter implements TestReporter {

    String lastSuite = "";
    int errors = 0;
    int failures = 0;
    int successes = 0;
    private Writer writer;

    public TestConsoleReporter(Writer writer) {
        this.writer = writer;
    }



    @Override
    public void report(TestResult testResult) {
        try {
            // Imprime cabecera de Suit cuando cambie la misma.
            if (!lastSuite.equals(testResult.getSuiteName())) {
                printSuiteHeader(testResult.getSuiteName());
            }

            // Imprime resultado de test
            writer.write(testResult.toString());

            // Setea variables auxiliares
            lastSuite = testResult.getSuiteName();
            if (testResult.isError()) {
                errors++;
            }
            if (testResult.isFailure()) {
                failures++;
            }
            if (testResult.isSuccess()) {
                successes++;
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();  // TODO: do something with the error
        }
    }

    private void printSuiteHeader(String suite) throws IOException {
		if (!suite.isEmpty()) {
            writer.write("");
            writer.write(suite);
            writer.write("--------------------");
		}
	}

	@SuppressWarnings("unused")
	private void printFooter(int errors, int failures, int successes) throws IOException {
        writer.write("");
		if (errors > 0 || failures > 0) {
            writer.write("[failure] Summary");
		} else {
            writer.write("[ok] Summary");
		}
        writer.write("====================");
        writer.write("Run: " + successes);
        writer.write("Errors: " + errors);
        writer.write("Failures: " + failures);
	}

}
