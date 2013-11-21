package ar.fiuba.tecnicas.tetesteo.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;


public abstract class TestMain {

	public static int doMain(OutputStream output, TestSuite mainTestSuite) {
		Writer writer = new OutputStreamWriter(output);
		new TestRunnerImpl(writer).run(mainTestSuite);
		try {			
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
		}
		return 0;
	}

}
