package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.source.DependenciesSource;
import org.bitstrings.maven.plugins.properties.source.GroupsSource;
import org.bitstrings.maven.plugins.properties.source.MavenEnvironmentSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesEntriesSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesFileSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesSource;

public class PropertiesDefineOperationsExecutor
    implements PropertiesOperationExecutor
{
    private final List<PropertiesSource> propertiesSources = new ArrayList<PropertiesSource>();

    public void addFromDependencies( DependenciesSource source )
    {
        propertiesSources.add( source );
    }

    public void addFromGroups( GroupsSource source )
    {
        propertiesSources.add( source );
    }

    public void addFromMavenProperties( MavenEnvironmentSource source )
    {
        propertiesSources.add( source );
    }

    public void addFromPropertiesEntries( PropertiesEntriesSource source )
    {
        propertiesSources.add( source );
    }

    public void addFromPropertiesFile( PropertiesFileSource source )
    {
        propertiesSources.add( source );
    }

    @Override
    public void execute( PropertiesPluginContext context )
        throws PropertiesOperationException
    {
        for ( PropertiesSource source : propertiesSources  )
        {
            context.addPropertiesSource( source );
            source.setContext( context );
        }
    }
}
