package org.bitstrings.maven.plugins.properties;

public class SelectPropertiesSet
{
    private SelectSet selectProperties;

    private SelectSet selectGroups;

    public SelectSet getSelectProperties()
    {
        return selectProperties;
    }

    public void setSelectProperties( SelectSet selectProperties )
    {
        this.selectProperties = selectProperties;
    }

    public SelectSet getSelectGroups()
    {
        return selectGroups;
    }

    public void setSelectGroups( SelectSet selectGroups )
    {
        this.selectGroups = selectGroups;
    }
}
