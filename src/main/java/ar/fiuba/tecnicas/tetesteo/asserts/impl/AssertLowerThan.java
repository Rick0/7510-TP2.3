package ar.fiuba.tecnicas.tetesteo.asserts.impl;

import ar.fiuba.tecnicas.tetesteo.asserts.Assert2;

public class AssertLowerThan<T extends Comparable<T> > implements Assert2<T, T> {

    @Override
    public void apply(T t1, T t2) {
        boolean success = t1.compareTo(t2) < 0;
        if(!success) {
        	throw new AssertionError(t1.toString() + " are not lower than " + t2.toString());
        }
    }
}

