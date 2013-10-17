package org.bitstrings.maven.plugins.properties.selector;

public class PropertiesSelector
{
    private SetSelector propertySet;

    private SetSelector groupSet;

    public SetSelector getPropertySet()
    {
        return propertySet;
    }

    public void setPropertiesSet( SetSelector propertiesSet )
    {
        this.propertySet = propertiesSet;
    }

    public SetSelector getGroupSet()
    {
        return groupSet;
    }

    public void setGroupSet( SetSelector groupSet )
    {
        this.groupSet = groupSet;
    }
}
