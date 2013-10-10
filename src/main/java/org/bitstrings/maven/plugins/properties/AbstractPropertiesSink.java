package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public abstract class AbstractPropertiesSink
{
    private PropertiesStore propertiesStore;

    private List<SelectPropertiesSet> selectPropertiesSets = new ArrayList<SelectPropertiesSet>();

    public List<SelectPropertiesSet> getSelectPropertiesSets()
    {
        return selectPropertiesSets;
    }

    public void addSelectPropertiesSet( SelectPropertiesSet selectPropertiesSet )
    {
        selectPropertiesSets.add( selectPropertiesSet );
    }

    public void setPropertiesStore( PropertiesStore propertiesStore )
    {
        this.propertiesStore = propertiesStore;
    }

    public Properties getSelectedProperties()
    {
        return propertiesStore.getProperties( selectPropertiesSets );
    }

    public void write()
    {
        writeProperties( getSelectedProperties() );
    }

    protected abstract void writeProperties( Properties properties );
}
