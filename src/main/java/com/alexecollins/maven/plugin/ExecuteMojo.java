package com.alexecollins.maven.plugin;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.mozilla.javascript.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Map;

/**
 * Execute a script.
 *
 * @goal execute
 * @since 1.0.0
 */
public class ExecuteMojo extends AbstractScriptMojo {

	/**
	 * The language, e.g. "groovy".
	 *
	 * @parameter
	 */
	public String language = null;

	/**
	 * The engine to use, e.g. "org.codehaus.groovy.bsf.GroovyEngine".
	 *
	 * @parameter
	 */
	public String engine = null;

	/**
	 * A script to execute, e.g. "println('hello world')".
	 *
	 * @parameter
	 */
	public String script = null;

	/**
	 * A file to execute, e.g. "hello-world.groovy".
	 *
	 * @parameter
	 */
	public File scriptFile = null;

    public void execute() throws MojoExecutionException, MojoFailureException {
	    try {
		    execute2();
	    } catch (MojoFailureException e) {
		    throw e;
	    } catch (Exception e) {
		    throw new MojoExecutionException(e.getMessage(), e);
	    }
    }
	private void execute2() throws Exception {

		if (script == null && scriptFile == null) {
			throw new MojoFailureException("either script or scriptFile must be provided");
		}
		if (script != null && scriptFile != null) {
			throw new MojoFailureException("only one of script of scriptFile may be provided, but NOT both");
		}

		if (language == null && scriptFile != null) {
			try {
				language = BSFManager.getLangFromFilename(scriptFile.getName());
			} catch (BSFException e) {
				//
			}
			final int i = scriptFile.getName().indexOf(".");
			if (language == null && i >= 0) {
				language = scriptFile.getName().substring(i+1);
			}
		}

		if (language == null) {
			language = "beanshell";
		}

		if (engine != null) {
			BSFManager.registerScriptingEngine(language, engine, new String[]{language});
		}

		final BSFManager mgr = new BSFManager();

		if (language.equals("javascript")) {
			Context.enter();
		}

		mgr.loadScriptingEngine(language);
		try {
			for (Map.Entry<String, Object> entry : getBeans().entrySet()) {
				mgr.declareBean(entry.getKey(), entry.getValue(), entry.getValue().getClass());
			}
		} catch (BSFException e) {
			if (!e.getMessage().contains("does not support")) {
				throw e;
			} else {
				getLog().warn(e.getMessage());
			}
		}

		getLog().info("executing " + language + " script");

		if (script == null) {
			final BufferedReader in = new BufferedReader(new FileReader(scriptFile));
			final StringBuilder s =new StringBuilder();
			String l;
			while ((l = in.readLine()) != null) {
				s.append(l).append('\n');
			}
			script = s.toString();
		}

		mgr.eval(language, String.valueOf(scriptFile), 0, 0, script);
    }
}
