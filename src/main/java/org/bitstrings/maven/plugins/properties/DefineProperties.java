package org.bitstrings.maven.plugins.properties;

import java.util.Properties;

public class DefineProperties
{
    private String groupName;

    protected Properties properties = new Properties();

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }
}
