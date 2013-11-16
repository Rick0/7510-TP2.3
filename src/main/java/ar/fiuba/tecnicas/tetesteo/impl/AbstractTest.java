package ar.fiuba.tecnicas.tetesteo.impl;

import ar.fiuba.tecnicas.tetesteo.Context;
import ar.fiuba.tecnicas.tetesteo.Test;

//import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;


public abstract class AbstractTest implements Test {

    private final String name;

    private final Set<String> labels;
    private final boolean skipped;

    public AbstractTest(String name, boolean skipped, String... labels) {
        this.name = name;
        this.labels = new TreeSet(Arrays.asList(labels));
        this.skipped = skipped;
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
}
