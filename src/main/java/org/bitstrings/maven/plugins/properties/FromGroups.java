package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FromGroups
    extends PropertiesProvider
{
    private final List<SelectPropertiesSet> propertiesSets = new ArrayList<SelectPropertiesSet>();

    public List<SelectPropertiesSet> getPropertiesSets()
    {
        return propertiesSets;
    }

    public void addPropertiesSet( SelectPropertiesSet propertiesSet )
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
