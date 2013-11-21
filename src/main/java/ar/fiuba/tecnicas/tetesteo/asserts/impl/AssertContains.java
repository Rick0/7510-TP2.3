package ar.fiuba.tecnicas.tetesteo.asserts.impl;

import ar.fiuba.tecnicas.tetesteo.asserts.Assert2;

import java.util.Collection;
import java.lang.AssertionError;


public class AssertContains<T> implements Assert2<Collection<T>, T> {

	@Override
	public void apply(Collection<T> collection, T t) {
		Boolean success = collection.contains(t);
		if(!success) {
			throw new AssertionError(t.toString() + " are not contained on " + collection.toString());
		}
	}
}
