package ar.fiuba.tecnicas.tetesteo;

import ar.fiuba.tecnicas.tetesteo.impl.TestResultImpl.Builder;

/**
 * Objeto que representa el resultado de un test
 */
public interface TestResult {

	Boolean isSuccess();
	
	Boolean isFailure();
	
	Boolean isError();
	
    String getMessage();

    String getTestName();

    String getSuiteName();

    long getTime();

    String toString();

    Boolean isSuite();
    
    /**
     * Builder de resultado, asi se puede ir llenando los distintos valores del resultado antes de ser creado,
     * para evitar inconsistencias y valores erroneos o nulos en los resultados.
     */
    public static interface Builder {
    	Builder withType(String type);
    	
        Builder withSuccess(Boolean success);

        Builder andOnSuccess(Boolean success);

        Builder withMessage(String message);

        Builder concatOnMessage(String message);

        Builder withTestName(String testName);

        Builder onSuite(String suiteName);

        Builder withTime(long durationOnMillis);

        Builder setSuiteFlag(boolean suiteFlag);
        
        TestResult build();

    }
    
    public static class Success {
    	@Override
    	public String toString() {
    		return "ok";
    	}
    }

    public static class Failure {
    	@Override
    	public String toString() {
    		return "fail";
    	}
    }
    
    public static class Error {
    	@Override
    	public String toString() {
    		return "error";
    	}
    }
    
}
