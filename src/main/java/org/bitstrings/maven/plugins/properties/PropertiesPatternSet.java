package org.bitstrings.maven.plugins.properties;

public class PropertiesPatternSet
{
    private PatternSet propertySet;

    private PatternSet groupSet;

    public PatternSet getPropertySet()
    {
        return propertySet;
    }

    public void setPropertiesSet( PatternSet propertiesSet )
    {
        this.propertySet = propertiesSet;
    }

    public PatternSet getGroupSet()
    {
        return groupSet;
    }

    public void setGroupSet( PatternSet groupSet )
    {
        this.groupSet = groupSet;
    }
}
