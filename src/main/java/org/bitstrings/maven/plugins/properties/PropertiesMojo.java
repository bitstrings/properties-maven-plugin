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
import org.apache.maven.project.DefaultDependencyResolutionRequest;
import org.apache.maven.project.DependencyResolutionRequest;
import org.apache.maven.project.DependencyResolutionResult;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectDependenciesResolver;
import org.sonatype.aether.util.filter.PatternInclusionsDependencyFilter;

@Mojo( name = "properties", defaultPhase = INITIALIZE, threadSafe = true )
public class PropertiesMojo
    extends AbstractMojo
{
    @Component
    private MavenProject mavenProject;

    @Component
    private MavenSession mavenSession;

    @Component
    private ProjectDependenciesResolver projectDependenciesResolver;

    @Parameter( defaultValue = "false" )
    private boolean verbose;

    @Parameter( alias="define" )
    private List<PropertiesProvider> propertiesProviders;

    @Parameter( alias="write" )
    private List<PropertiesWriter> propertiesSinks;

    @Parameter( alias="properties" )
    private List<PropertiesOperationExecutor> propertiesOperationExecutors;

    @Override
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        PropertiesPluginContext context =
                        new PropertiesPluginContext( mavenSession, projectDependenciesResolver, this );

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

        for ( PropertiesOperationExecutor exec : propertiesOperationExecutors  )
        {
        }

        System.out.println( mavenProject.getProperties() );

        try
        {
            System.out.println( "mavenProject: " + mavenProject );
            System.out.println( "mavenSession: " + mavenSession );
            System.out.println( "projectDependenciesResolver: " + projectDependenciesResolver );

            DependencyResolutionRequest request =
                            new DefaultDependencyResolutionRequest( mavenProject, mavenSession.getRepositorySession() );

            request.setResolutionFilter( new PatternInclusionsDependencyFilter( "*:*:*:*" ) );

            DependencyResolutionResult result =
                        projectDependenciesResolver
                                .resolve( request );

            System.out.println( result.getCollectionErrors() );
            System.out.println( result.getResolvedDependencies() );
        }
        catch ( Exception e )
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
