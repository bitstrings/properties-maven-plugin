package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.source.DependenciesSource;
import org.bitstrings.maven.plugins.properties.source.GroupsSource;
import org.bitstrings.maven.plugins.properties.source.MavenEnvironmentSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesEntriesSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesFileSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesSource;

public class PropertiesDefineOperations
    implements PropertiesOperationExecutor
{
    private final List<PropertiesSource> propertiesSources = new ArrayList<PropertiesSource>();

    public void addFromDependencies( DependenciesSource source )
    {
        source.setOperationTag( "FromDependencies" );

        propertiesSources.add( source );
    }

    public void addFromGroups( GroupsSource source )
    {
        source.setOperationTag( "FromGroups" );

        propertiesSources.add( source );
    }

    public void addFromMavenEnvironment( MavenEnvironmentSource source )
    {
        source.setOperationTag( "FromMavenEnvironment" );

        propertiesSources.add( source );
    }

    public void addFromPropertiesEntries( PropertiesEntriesSource source )
    {
        source.setOperationTag( "FromPropertiesEntries" );

        propertiesSources.add( source );
    }

    public void addFromPropertiesFile( PropertiesFileSource source )
    {
        source.setOperationTag( "FromPropertiesFile" );

        propertiesSources.add( source );
    }

    @Override
    public void execute( PropertiesPluginContext context )
        throws PropertiesOperationException
    {
        for ( PropertiesSource source : propertiesSources  )
        {
            source.setContext( context );

            context.addPropertiesSource( source );
        }
    }
}
