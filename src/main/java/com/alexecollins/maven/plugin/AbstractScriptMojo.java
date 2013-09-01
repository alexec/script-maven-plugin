package com.alexecollins.maven.plugin;

import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.metadata.ArtifactMetadataSource;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author alexec (alex.e.c@gmail.com)
 */
public abstract class AbstractScriptMojo extends AbstractMojo {
	/**
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	public MavenProject project;

	/**
	 * @component
	 */
	public ArtifactMetadataSource artifactMetadataSource;

	/**
	 * @component
	 */
	public ArtifactFactory artifactFactory;

	/**
	 * @component
	 */
	public ArtifactResolver artifactResolver;

	/**
	 * @parameter expression="${localRepository}"
	 * @required
	 * @readonly
	 */
	public ArtifactRepository localRepository;


	protected Map<String, Object> getBeans() {
		final Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("project", project);
		beans.put("artifactMetadataSource", artifactMetadataSource);
		beans.put("artifactFactory", artifactFactory);
		beans.put("artifactResolver", artifactResolver);
		beans.put("localRepository", localRepository);
		beans.put("log", getLog());

		return beans;
	}

}
