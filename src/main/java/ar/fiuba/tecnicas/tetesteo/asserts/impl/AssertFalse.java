package ar.fiuba.tecnicas.tetesteo.asserts.impl;

import ar.fiuba.tecnicas.tetesteo.asserts.Assert1;

public class AssertFalse implements Assert1<Boolean> {

	@Override
	public void apply(Boolean aBoolean) {
		if(aBoolean) {
			throw new AssertionError("Boolean are not false.");
		}
	}
}
