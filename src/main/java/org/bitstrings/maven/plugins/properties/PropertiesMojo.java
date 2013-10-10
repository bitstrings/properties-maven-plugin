package org.bitstrings.maven.plugins.properties;

import static org.apache.maven.plugins.annotations.LifecyclePhase.INITIALIZE;

import java.util.List;

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

    @Parameter( defaultValue = "false" )
    private boolean verbose;

    @Parameter( alias="define" )
    private List<AbstractPropertiesProvider> propertiesProviders;

    @Parameter( alias="sink" )
    private List<AbstractPropertiesSink> propertiesSinks;

    @Override
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        PropertiesPluginContext context = new PropertiesPluginContext( mavenProject );

        for ( AbstractPropertiesProvider propertiesProvider : propertiesProviders  )
        {
            context.addPropertiesProvider( propertiesProvider );
            System.out.println( "--- [ define ] ---" );
            System.out.println( propertiesProvider );
        }

        for ( AbstractPropertiesSink propertiesSink : propertiesSinks  )
        {
            propertiesSink.setContext( context );
            propertiesSink.write();
            System.out.println( "--- [ sink ] ---" );
            System.out.println( propertiesSink );
        }

        System.out.println( mavenProject.getProperties() );
    }
}
