package org.bitstrings.maven.plugins.properties;

import static org.apache.maven.plugins.annotations.LifecyclePhase.INITIALIZE;

import java.util.List;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

@Mojo( name = "properties", defaultPhase = INITIALIZE, threadSafe = true )
public class PropertiesMojo
    extends AbstractMojo
{
    @Component
    private MavenProject mavenProject;

    @Component
    private MavenSession mavenSession;

    @Parameter( defaultValue = "false" )
    private boolean verbose;

    @Parameter( alias="define" )
    private List<PropertiesProvider> propertiesProviders;

    @Parameter( alias="write" )
    private List<PropertiesWriter> propertiesSinks;

    @Override
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        PropertiesPluginContext context = new PropertiesPluginContext( mavenSession, this );

        for ( PropertiesProvider propertiesProvider : propertiesProviders  )
        {
            try
            {
                context.addPropertiesProvider( propertiesProvider );
                propertiesProvider.setContext( context );
            }
            catch ( PropertiesOperationException e )
            {
                throw new MojoExecutionException(
                            "Define properties error [" + propertiesProvider.getClass().getSimpleName() + "]",
                            e );
            }
        }

        for ( PropertiesWriter propertiesSink : propertiesSinks  )
        {
            try
            {
                propertiesSink.setContext( context );
                propertiesSink.write();
            }
            catch ( PropertiesOperationException e )
            {
                throw new MojoExecutionException(
                            "Properties write error [" + propertiesSink.getClass().getSimpleName() + "]",
                            e );
            }
        }

        System.out.println( mavenProject.getProperties() );
    }
}
