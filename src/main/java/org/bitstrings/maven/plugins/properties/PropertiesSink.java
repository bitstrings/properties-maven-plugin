package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public abstract class PropertiesSink
    extends PropertiesOperation
{
    private PropertiesPluginContext context;

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
        return context.getProperties( selectPropertiesSets );
    }

    public void write()
    {
        final Properties properties = getSelectedProperties();

        if ( isVerbose() )
        {
            PropertiesHelper.logOperationProperties( getLog(), getOperationTag(), properties, null );
        }

        writeProperties( properties );
    }

    protected abstract void writeProperties( Properties properties );
}
