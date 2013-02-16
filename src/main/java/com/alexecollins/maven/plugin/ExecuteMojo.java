package com.alexecollins.maven.plugin;

import org.apache.bsf.BSFManager;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;

/**
 * Execute a JSR-223 script
 *
 * @goal execute
 */
public class ExecuteMojo extends AbstractMojo {

	/**
	 * @parameter
	 */
	public String language = "beanshell";

	/**
	 * @parameter
	 */
	public String engine = null;

	/**
	 * @parameter
	 */
	public String script = null;

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
		getLog().info("executing " + language + " script");

		final BSFManager x = new BSFManager();

		if (engine != null) {
			BSFManager.registerScriptingEngine(language, engine, new String[] {language});
		}

		x.loadScriptingEngine(language);
		x.registerBean("project", project);

		if (script != null) {
			getLog().info(String.valueOf(x.eval(language, "Script", 0, 0, script)));
		} else {
			throw new IllegalStateException("no script provided");
		}
    }
}
