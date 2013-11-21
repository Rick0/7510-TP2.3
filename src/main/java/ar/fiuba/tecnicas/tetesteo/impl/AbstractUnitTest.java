package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.TestExecutor;

/**
 * Implementacion parcial de un unit test para que los distintos test unitarios deban solo implementar el metodo
 * test para su ejecucion.
 */
public abstract class AbstractUnitTest extends AbstractTest {
	
	private double timeOut;

	public AbstractUnitTest(String name, boolean skipped, String... labels) {
		super(name, skipped, labels);
		timeOut = -1;
	}

	public AbstractUnitTest(String name) {
		this(name, false);
		timeOut = -1;
	}

	/**
	 * Template method, para poder dejar el coportamiento comun de seteo de nombre y registro del result en esta clase.
	 */
	public abstract void test(TestExecutor testExecutor, Context context);

	@Override
	public boolean isSuite() {
		return false;
	}
	
	@Override
	public void setTimeOut (double timeForTimeOut){
		timeOut = timeForTimeOut;
	}
	
	@Override
	public double getTimeOut(){
		return timeOut;
	}

}
