package ar.fiuba.tecnicas.tetesteo;

public interface Context {

	<T> T getProperty(String name);

	<T> void setProperty(String name, T value);

	/**
	 * @return Una copia del contexto, para poder tener contextos independientes segun el scope
	 * Por ejemplo, que un unit test no vea variables que definio otro unit test hermano (que comparten la misma test suite)
	 */
	Context duplicar();
}
