package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.toStringHelper;

import java.util.Properties;

public class PropertiesProvider
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

    @Override
    public String toString()
    {
        return
                toStringHelper( this )
                .add( "groupName", groupName )
                .add( "properties", properties )
                .toString();
    }
}
