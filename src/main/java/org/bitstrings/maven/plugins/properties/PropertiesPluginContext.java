package org.bitstrings.maven.plugins.properties;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.project.MavenProject;
import org.bitstrings.maven.plugins.properties.util.SelectSetHelper;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PropertiesPluginContext
{
    private static final SelectSet NO_SELECT_IS_DEFAULT_GROUP = new SelectSet();

    static
    {
        NO_SELECT_IS_DEFAULT_GROUP.set( AbstractPropertiesProvider.DEFAULT_GROUP_NAME );
    }

    private static final List<SelectPropertiesSet>
                    NO_SELECT_LIST_IS_ALL =
                                    Collections.singletonList( new SelectPropertiesSet() );

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
            for ( AbstractPropertiesProvider propertiesProvider : groupedPropertiesProvidersMap.get( groupName ) )
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
            groupSet = NO_SELECT_IS_DEFAULT_GROUP;
        }

        final List<String> selectedGroups =
                    SelectSetHelper.filter(
                        groupSet,
                        groupedPropertiesProvidersMap.keySet() );

        final Properties properties = getUnifiedProperties( selectedGroups );

        SelectSet propertiesSet = selectPropertiesSet.getSelectProperties();

        if ( propertiesSet != null )
        {
            final List<String> selectedProperties =
                        SelectSetHelper.filter(
                            propertiesSet,
                            properties.stringPropertyNames() );

            properties.keySet().retainAll( selectedProperties );
        }

        return properties;
    }

    public Properties getProperties( List<SelectPropertiesSet> selectPropertiesSets )
    {
        final Properties properties = new Properties();

        if ( selectPropertiesSets.isEmpty() )
        {
            selectPropertiesSets = NO_SELECT_LIST_IS_ALL;
        }

        for ( SelectPropertiesSet selectPropertiesSet : selectPropertiesSets )
        {
            properties.putAll( getProperties( selectPropertiesSet ) );
        }

        return properties;
    }
}
