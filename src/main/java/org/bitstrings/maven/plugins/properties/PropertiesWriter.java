package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class PropertiesWriter
    extends PropertiesOperation
{
    private List<SelectPropertiesSet> propertiesSets = new ArrayList<SelectPropertiesSet>();

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

    public void write()
        throws PropertiesOperationException
    {
        final Properties properties = getSelectedProperties();

        writeProperties( properties );
    }

    protected abstract void writeProperties( Properties properties )
        throws PropertiesOperationException;
}
