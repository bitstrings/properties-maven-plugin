package org.bitstrings.maven.plugins.properties;

import java.util.Collection;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.SelectSets;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PropertiesStore
{
    private final Multimap<String, AbstractPropertiesProvider>
                        groupedPropertiesProvidersMap =
                                ArrayListMultimap.<String, AbstractPropertiesProvider> create();

    public PropertiesStore() {}

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
        final List<String> selectedGroups =
                    SelectSets.filter(
                        selectPropertiesSet.getSelectGroups(),
                        groupedPropertiesProvidersMap.keySet() );

        final Properties properties = getUnifiedProperties( selectedGroups );

        final List<String> selectedProperties =
                    SelectSets.filter(
                        selectPropertiesSet.getSelectProperties(),
                        properties.stringPropertyNames() );

        properties.keySet().retainAll( selectedProperties );

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
