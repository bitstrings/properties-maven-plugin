package org.bitstrings.maven.plugins.properties.source;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.PropertiesPatternSet;

public class GroupsSource
    extends PropertiesSource
{
    private final List<PropertiesPatternSet> propertiesSets = new ArrayList<PropertiesPatternSet>();

    public List<PropertiesPatternSet> getPropertiesSets()
    {
        return propertiesSets;
    }

    public void addPropertiesSet( PropertiesPatternSet propertiesSet )
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
