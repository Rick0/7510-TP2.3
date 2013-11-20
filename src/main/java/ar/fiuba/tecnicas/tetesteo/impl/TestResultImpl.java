package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.TestResult;
import ar.fiuba.tecnicas.tetesteo.TestResult.Builder;
import ar.fiuba.tecnicas.tetesteo.store.TestRunStoreAccess;

public class TestResultImpl implements TestResult {
	
	private final String type;
	private final Boolean success;
	private final String message;
	private final long time;
	private final String testName;
	private final String suiteName;
	private final Boolean isSuite;
	
	private TestResultImpl(String type, Boolean success, String message,
			long time, String testName, String suiteName, Boolean suiteFlag) {
		this.type = type;
		if (success){			
			TestRunStoreAccess.getInstance().addTestOk(testName);
		}
		this.success = success;
		this.message = message;
		this.time = time;
		this.testName = testName;
		this.suiteName = suiteName;
		this.isSuite = suiteFlag;
	}

	public String toString() {
		return ("[" + type + "] " + testName + " duration: " + time + '\n');
	}

	@Override
	public Boolean isSuccess() {
		return success;
	}

	@Override
	public Boolean isFailure() {
		return type.equals(new Failure().toString());
	}

	@Override
	public Boolean isError() {
		return type.equals(new Error().toString());
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getTestName() {
		return testName;
	}

	@Override
	public String getSuiteName() {
		return suiteName;
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public Boolean isSuite() {
		return isSuite;
	}
	


	public static class Builder implements TestResult.Builder {

		private String type = new Success().toString();
		private Boolean success = Boolean.TRUE;
		private String message = "";
		private String testName = "";
		private String suiteName = ".";
		private long time = 0;
		private Boolean isSuite = Boolean.FALSE;
		private String path = ".";

		public Builder withType(String type) {
			this.type = type;
			return this;
		}

		public Builder withSuccess(Boolean success) {
			this.success = success;
			return this;
		}

		public Builder andOnSuccess(Boolean success) {
			this.success = this.success && success;
			return this;
		}

		public Builder withMessage(String message) {
			this.message = message;
			return this;
		}

		public Builder concatOnMessage(String message) {
			this.message = this.message.concat(message);
			return this;
		}

		public Builder withTestName(String testName) {
			this.testName = testName;
			return this;
		}

		public Builder setSuiteFlag(boolean flag) {
			this.isSuite = Boolean.valueOf(flag);
			return this;
		}

		@Override
		public TestResult.Builder onSuite(String suiteName) {
			this.suiteName = suiteName;
			return this;
		}

		@Override
		public TestResult.Builder withTime(long time) {
			this.time = time;
			return this;
		}
				
		public TestResult.Builder setPath(String path){
			this.path = path;
			return this;
		}

		public TestResult build() {
//			return new TestResultImpl(type, success, message, time, testName,
//					suiteName, isSuite);
			return new TestResultImpl(type, success, message, time, testName,
					suiteName, isSuite);

		}
	}
}
