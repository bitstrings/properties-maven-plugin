package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class PropertiesSink
    extends PropertiesOperation
{
    private List<SelectPropertiesSet> selectPropertiesSets = new ArrayList<SelectPropertiesSet>();

    public List<SelectPropertiesSet> getSelectPropertiesSets()
    {
        return selectPropertiesSets;
    }

    public void addSelectPropertiesSet( SelectPropertiesSet selectPropertiesSet )
    {
        selectPropertiesSets.add( selectPropertiesSet );
    }

    public Properties getSelectedProperties()
    {
        return getContext().getProperties( selectPropertiesSets );
    }

    public void write()
    {
        final Properties properties = getSelectedProperties();

        writeProperties( properties );
    }

    protected abstract void writeProperties( Properties properties );
}
