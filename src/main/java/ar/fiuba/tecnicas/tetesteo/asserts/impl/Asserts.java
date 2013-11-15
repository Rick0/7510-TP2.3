package ar.fiuba.tecnicas.tetesteo.asserts.impl;

import java.util.Collection;

/**
 * Provee facil acceso a los distintos asserts
 */
public class Asserts {

    public static<T> void assertEquals(T t1, T t2) {
        new AssertEquals<T>().apply(t1, t2);
    }

    public static<T> void assertDistinct(T t1, T t2) {
        new AssertDistinct<T>().apply(t1, t2);
    }

    public static<T extends Comparable> void assertGreaterThan(T t1, T t2) {
        new AssertGreaterThan<T>().apply(t1, t2);
    }

    public static<T extends Comparable> void assertLowerThan(T t1, T t2) {
        new AssertLowerThan<T>().apply(t1, t2);
    }

    public static<T> void assertContains(Collection<T> collection, T t) {
        new AssertContains<T>().apply(collection, t);
    }

    public static void assertTrue(Boolean bool) {
        new AssertTrue().apply(bool);
    }

    public static void assertFalse(Boolean bool) {
        new AssertFalse().apply(bool);
    }

}
