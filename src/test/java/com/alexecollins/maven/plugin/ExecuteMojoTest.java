package com.alexecollins.maven.plugin;

import org.apache.bsf.BSFManager;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public class ExecuteMojoTest {

	@Ignore
	@Test
	public void testEngine() throws Exception {
		final BSFManager mgr = new BSFManager();
		mgr.loadScriptingEngine("javascript");
		mgr.eval("javascript", "Test", 0, 0, "console.log('OK');");
	}
}
