package org.bitstrings.maven.plugins.properties.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.selector.PropertiesSelector;

public class GroupsPropertiesSource
    extends PropertiesSource
{
    private final List<PropertiesSelector> propertiesSets = new ArrayList<PropertiesSelector>();

    public List<PropertiesSelector> getPropertiesSets()
    {
        return propertiesSets;
    }

    public void addPropertiesSet( PropertiesSelector propertiesSet )
    {
        propertiesSets.add( propertiesSet );
    }

    public Properties getSelectedProperties()
    {
        return getContext().getProperties( propertiesSets );
    }

    @Override
    protected Properties resolveProperties()
    {
        return getSelectedProperties();
    }
}
