package ar.fiuba.tecnicas.tetesteo;

/**
 * Clase encargada de correr los tests idealmente deberia solo correr un test suite que sea una agrupacion de
 * test unitarios o otros test suites
 */
public interface TestRunner {

	void run(Test test);

	void run(Test test, String regularExpresion);
}
