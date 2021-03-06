package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;


public abstract class AbstractTest implements Test {

	private final String name;
	private final Set<String> labels;
	private final boolean skipped;
	protected double timeOut;


	public AbstractTest(String name, boolean skipped, String... labels) {		
		this.name = name;
		this.labels = new TreeSet<String>(Arrays.asList(labels));		
		this.skipped = skipped;
		timeOut = -1;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Set<String> getLabels() {
		return labels;
	}

	@Override
	public void setup(Context context) {
	}

	@Override
	public void teardown(Context context) {
	}

	@Override
	public boolean isSkipped() {
		return skipped;
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
