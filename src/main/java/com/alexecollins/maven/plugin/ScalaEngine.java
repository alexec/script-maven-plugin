package com.alexecollins.maven.plugin;

import org.apache.bsf.BSFException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class ScalaEngine extends org.apache.bsf.util.BSFEngineImpl {


	public Object call(Object o, String s, Object[] objects) throws BSFException {
		throw new UnsupportedOperationException("not supported");
	}

	public Object eval(String filename, int i, int i2, Object script) throws BSFException {
		if (i != 0 || i2 != 0) {
			throw new IllegalArgumentException();
		}

		File scalaFile;
		File tmp;
		try {
			tmp = File.createTempFile("scala", "dir");
			if (!tmp.delete() || !tmp.mkdirs()) {
				throw new IllegalStateException();
			}
			scalaFile = new File(tmp, filename);
			final PrintWriter out = new PrintWriter(new FileOutputStream(scalaFile));
			try {
				out.print(script);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			throw new BSFException("failed to create tmp dir: " + e.getMessage());
		}

		/*
		final Global global = new Global(new ConsoleReporter(new Settings()));
		final Global.Run run = global.new Run();
		run.compile(scala.collection.immutable.List.fromString(tmp.getPath(), ','));
		*/

		throw new UnsupportedOperationException("not supported");
	}
}