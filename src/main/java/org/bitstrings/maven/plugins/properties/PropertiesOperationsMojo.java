package org.bitstrings.maven.plugins.properties;

import static org.apache.maven.plugins.annotations.LifecyclePhase.INITIALIZE;

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

@Mojo( name = "operations", defaultPhase = INITIALIZE, threadSafe = true )
public class PropertiesOperationsMojo
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

    @Parameter( alias="operations" )
    private PropertiesOperations propertiesOperationsExecutor;

    @Override
    public void execute()
        throws MojoExecutionException, MojoFailureException
    {
        PropertiesPluginContext context =
                        new PropertiesPluginContext( mavenSession, projectDependenciesResolver, this );

        try
        {
            propertiesOperationsExecutor.execute( context );
        }
        catch ( PropertiesOperationException e )
        {
            throw new MojoExecutionException(
                        "Properties operation error ["
                            + e.getPropertiesOperation().getClass().getSimpleName()
                            + "]",
                        e );
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
