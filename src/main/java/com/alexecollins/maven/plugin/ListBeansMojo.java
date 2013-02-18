package com.alexecollins.maven.plugin;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * List the beans that are scoped in the scripts.
 *
 * @goal list-beans
 * @author alexec (alex.e.c@gmail.com)
 * @since 1.0.0
 */
public class ListBeansMojo extends AbstractScriptMojo {
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info(String.valueOf(getBeans().keySet()));
	}
}
