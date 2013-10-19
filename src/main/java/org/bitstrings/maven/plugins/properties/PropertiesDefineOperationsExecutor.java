package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.source.DependenciesPropertiesSource;
import org.bitstrings.maven.plugins.properties.source.GroupsPropertiesSource;
import org.bitstrings.maven.plugins.properties.source.MavenPropertiesSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesEntriesSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesFileSource;
import org.bitstrings.maven.plugins.properties.source.PropertiesSource;

public class PropertiesDefineOperationsExecutor
    implements PropertiesOperationExecutor
{
    private final List<PropertiesSource> propertiesSources = new ArrayList<PropertiesSource>();

    public void addFromDependencies( DependenciesPropertiesSource source )
    {
        propertiesSources.add( source );
    }

    public void addFromGroups( GroupsPropertiesSource source )
    {
        propertiesSources.add( source );
    }

    public void addFromMavenProperties( MavenPropertiesSource source )
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
