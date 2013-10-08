package org.bitstrings.maven.plugins.properties;

import static org.apache.maven.plugins.annotations.LifecyclePhase.INITIALIZE;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

@Mojo( name = "compose", defaultPhase = INITIALIZE, threadSafe = true )
public class PropertiesMojo
    extends AbstractMojo
{
    @Component
    private MavenProject project;

    @Override
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
    }
}
