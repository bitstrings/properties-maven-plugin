package org.bitstrings.maven.plugins.properties;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.bitstrings.maven.plugins.properties.util.SelectSets;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PropertiesPluginContext
{
    private final MavenProject mavenProject;

    private final Multimap<String, AbstractPropertiesProvider>
                        groupedPropertiesProvidersMap =
                                ArrayListMultimap.<String, AbstractPropertiesProvider> create();

    public PropertiesPluginContext( MavenProject mavenProject )
    {
        this.mavenProject = mavenProject;
    }

    public MavenProject getMavenProject()
    {
        return mavenProject;
    }

    public void addPropertiesProvider( AbstractPropertiesProvider propertiesProvider )
    {
        groupedPropertiesProvidersMap.put( propertiesProvider.getGroupName(), propertiesProvider );
    }

    public Properties getUnifiedProperties( List<String> groupsNames )
    {
        final Properties properties = new Properties();

        for ( String groupName : groupsNames )
        {
            Collection<AbstractPropertiesProvider> propertiesProviders = groupedPropertiesProvidersMap.get( groupName );

            for ( AbstractPropertiesProvider propertiesProvider : propertiesProviders )
            {
                properties.putAll( propertiesProvider.getProperties() );
            }
        }

        return properties;
    }

    public Properties getProperties( SelectPropertiesSet selectPropertiesSet )
    {
        SelectSet groupSet = selectPropertiesSet.getSelectGroups();

        if ( groupSet == null )
        {
            groupSet = new SelectSet();
            groupSet.set( AbstractPropertiesProvider.DEFAULT_GROUP_NAME );
        }

        final List<String> selectedGroups =
                    SelectSets.filter(
                        groupSet,
                        groupedPropertiesProvidersMap.keySet() );

        final Properties properties = getUnifiedProperties( selectedGroups );

        SelectSet propertiesSet = selectPropertiesSet.getSelectProperties();

        if ( propertiesSet != null )
        {
            final List<String> selectedProperties =
                        SelectSets.filter(
                            propertiesSet,
                            properties.stringPropertyNames() );

            properties.keySet().retainAll( selectedProperties );
        }

        return properties;
    }

    public Properties getProperties( List<SelectPropertiesSet> selectPropertiesSets )
    {
        final Properties properties = new Properties();

        for ( SelectPropertiesSet selectPropertiesSet : selectPropertiesSets )
        {
            properties.putAll( getProperties( selectPropertiesSet ) );
        }

        return properties;
    }
}
