package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;


public class PropertiesSetter
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
}
