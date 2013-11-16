package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.*;

import java.util.Collection;
import java.util.LinkedList;
//import java.util.regex.Pattern;

/**
 * Agrupacion de tests unitarios, para un mejor reporte de resultados, idealmente debiera haber un test suite por cada
 * clase a testear conteniendo los distintos tests unitarios relativos a esa clase.
 */
public class TestSuite extends AbstractTest {

    private Collection<Test> childTest;
    
    private String suite;

    public TestSuite(String name, boolean skipped, String... labels) {
        super(name, skipped, labels);
        childTest = new LinkedList<>();
        suite = "";
    }

    public TestSuite(String name, String... labels) {
        this(name, false, labels);
    }

    public void addTest(Test test) {
    	if(isValidName(test)) {
    		childTest.add(test);
    	} else {
    		System.out.println("El test " + test.getName() + " ya existe en la suite " + getName());
    	}
    }

    private boolean isValidName(Test test) {
    	for(Test t: childTest) {
    		if(t.getName().equals(test.getName())) {
    			return false;
    		}
    	}
    	return true;
	}

    public void test(TestExecutor testExecutor, Context context) {
        for (Test test: childTest) {
            testExecutor.executeOnSuite(getName(), test, context.duplicar());
        }
        if(!childTestAreSuccesses(testExecutor.getResults().get(this.getName()))) {
        	throw new AssertionError();
        }
    }

    private boolean childTestAreSuccesses(Collection<TestResult> results) {
    	for(TestResult result : results) {
    		if(!result.isSuccess())
    			return false;
    	}
    	return true;
    }
    
    @Override
    public boolean isSuite() {
        return true;
    }

    @SuppressWarnings("unused")
	private String getSuiteFullName() {
    	if(suite.isEmpty()) {
    		return getName();
    	} else {
    		return suite + "." + getName();
    	}
    }
}