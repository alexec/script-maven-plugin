package com.alexecollins.maven.plugin;

import org.apache.bsf.BSFManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Execute a JSR-223 script
 *
 * @goal execute
 */
public class ExecuteMojo extends AbstractMojo {

	/**
	 * The language, e.g. "groovy"
	 *
	 * @parameter
	 */
	public String language = null;

	/**
	 * The engine to use, e.g.
	 *
	 * @parameter
	 */
	public String engine = null;

	/**
	 * @parameter
	 */
	public String script = null;

	/**
	 * @parameter
	 */
	public File scriptFile = null;

	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	public MavenProject project;

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
			throw new MojoFailureException("either script of scriptFile must be provided");
		}
		if (script != null && scriptFile != null) {
			throw new MojoFailureException("only one of script of scriptFile may be provided, but NOT both");
		}

		if (engine != null) {
			BSFManager.registerScriptingEngine(language, engine, new String[]{language});
		}

		if (language == null && scriptFile != null) {
			language = BSFManager.getLangFromFilename(scriptFile.getName());
			final int i = scriptFile.getName().indexOf(".");
			if (language == null && i >= 0) {
				language = scriptFile.getName().substring(i+1);
			}
		}

		if (language == null) {
			throw new MojoFailureException("language not specified and cannot determine from scriptFile (if provided)");
		}

		final BSFManager mgr = new BSFManager();

		mgr.loadScriptingEngine(language);
		mgr.declareBean("project", project, MavenProject.class);

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
