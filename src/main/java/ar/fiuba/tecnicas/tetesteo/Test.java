package ar.fiuba.tecnicas.tetesteo;

import java.util.Set;
//import java.util.regex.Pattern;

/**
 * Interfaz unica para los test
 */
public interface Test {

	void test(TestExecutor testExecutor, Context context);

	String getName();

	Set<String> getLabels();

	void setup(Context context);

	void teardown(Context context);

	boolean isSuite();

	boolean isSkipped();
}