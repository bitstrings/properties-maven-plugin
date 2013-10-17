package org.bitstrings.maven.plugins.properties;

import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.MavenProject;
import org.bitstrings.maven.plugins.properties.selector.PropertiesSelector;
import org.bitstrings.maven.plugins.properties.selector.SetSelector;
import org.bitstrings.maven.plugins.properties.util.SetSelectorHelper;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PropertiesPluginContext
{
    private static final SetSelector NO_SELECT_IS_DEFAULT_GROUP = new SetSelector();

    static
    {
        NO_SELECT_IS_DEFAULT_GROUP.set( PropertiesProvider.DEFAULT_GROUP_NAME );
    }

    private static final List<PropertiesSelector>
                    NO_SELECT_LIST_IS_ALL =
                                    Collections.singletonList( new PropertiesSelector() );

    private final MavenSession mavenSession;

    private final Multimap<String, PropertiesProvider>
                        groupedPropertiesProvidersMap =
                                ArrayListMultimap.<String, PropertiesProvider> create();

    private final AbstractMojo mojo;

    public PropertiesPluginContext( MavenSession mavenSession, AbstractMojo mojo )
    {
        this.mavenSession = mavenSession;
        this.mojo = mojo;
    }

    public AbstractMojo getMojo()
    {
        return mojo;
    }

    public MavenSession getMavenSession()
    {
        return mavenSession;
    }

    public MavenProject getMavenProject()
    {
        return mavenSession.getCurrentProject();
    }

    public void addPropertiesProvider( PropertiesProvider propertiesProvider )
    {
        groupedPropertiesProvidersMap.put( propertiesProvider.getGroupName(), propertiesProvider );
    }

    public Properties getUnifiedProperties( List<String> groupsNames )
    {
        final Properties properties = new Properties();

        for ( String groupName : groupsNames )
        {
            for ( PropertiesProvider propertiesProvider : groupedPropertiesProvidersMap.get( groupName ) )
            {
                properties.putAll( propertiesProvider.getProperties() );
            }
        }

        return properties;
    }

    public Properties getProperties( PropertiesSelector selectPropertiesSet )
    {
        SetSelector groupSet = selectPropertiesSet.getGroupSet();

        if ( groupSet == null )
        {
            groupSet = NO_SELECT_IS_DEFAULT_GROUP;
        }

        final List<String> selectedGroups =
                    SetSelectorHelper.regExfilter(
                        groupSet,
                        groupedPropertiesProvidersMap.keySet() );

        final Properties properties = getUnifiedProperties( selectedGroups );

        final SetSelector propertiesSet = selectPropertiesSet.getPropertySet();

        if ( propertiesSet != null )
        {
            final List<String> selectedProperties =
                        SetSelectorHelper.regExfilter(
                            propertiesSet,
                            properties.stringPropertyNames() );

            properties.keySet().retainAll( selectedProperties );
        }

        return properties;
    }

    public Properties getProperties( List<PropertiesSelector> selectPropertiesSets )
    {
        final Properties properties = new Properties();

        if ( selectPropertiesSets.isEmpty() )
        {
            selectPropertiesSets = NO_SELECT_LIST_IS_ALL;
        }

        for ( PropertiesSelector selectPropertiesSet : selectPropertiesSets )
        {
            properties.putAll( getProperties( selectPropertiesSet ) );
        }

        return properties;
    }
}
