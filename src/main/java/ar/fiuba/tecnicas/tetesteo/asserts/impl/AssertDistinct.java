package ar.fiuba.tecnicas.tetesteo.asserts.impl;

import ar.fiuba.tecnicas.tetesteo.asserts.Assert2;


public class AssertDistinct<T> implements Assert2<T, T> {

	@Override
	public void apply(T t1, T t2) {
		boolean success = !t1.equals(t2);
		if(!success) {
			throw new AssertionError(t1.toString() + " are not distinct to " + t2.toString());
		}
	}
}

