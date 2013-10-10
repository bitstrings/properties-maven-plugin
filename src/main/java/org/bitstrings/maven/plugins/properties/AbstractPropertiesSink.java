package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class AbstractPropertiesSink
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

    public void setContext( PropertiesPluginContext context )
    {
        this.context = context;
    }

    public PropertiesPluginContext getContext()
    {
        return context;
    }

    public Properties getSelectedProperties()
    {
        return context.getProperties( selectPropertiesSets );
    }

    public void write()
    {
        writeProperties( getSelectedProperties() );
    }

    protected abstract void writeProperties( Properties properties );
}
