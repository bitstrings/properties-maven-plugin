package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.selector.PropertiesSelector;

public abstract class PropertiesWriter
    extends PropertiesOperation
{
    private List<PropertiesSelector> propertiesSets = new ArrayList<PropertiesSelector>();

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

    public void write()
        throws PropertiesOperationException
    {
        final Properties properties = getSelectedProperties();

        writeProperties( properties );
    }

    protected abstract void writeProperties( Properties properties )
        throws PropertiesOperationException;
}
