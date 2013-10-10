package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.toStringHelper;

import java.util.Properties;

public abstract class AbstractPropertiesProvider
{
    public static final String DEFAULT_GROUP_NAME = "default";

    private String groupName = DEFAULT_GROUP_NAME;

    private final Properties properties = new Properties();

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public Properties getProperties()
    {
        resolveProperties( properties );

        return properties;
    }

    protected abstract void resolveProperties( Properties properties );

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
