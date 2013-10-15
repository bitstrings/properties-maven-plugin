package org.bitstrings.maven.plugins.properties;

import java.util.Properties;

public abstract class PropertiesProvider
    extends PropertiesOperation
{
    public static final String DEFAULT_GROUP_NAME = "default";

    private String groupName = DEFAULT_GROUP_NAME;

    private Properties properties;

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName( String groupName )
    {
        this.groupName = groupName;
    }

    public Properties getProperties()
        throws PropertiesOperationException
    {
        if ( properties == null )
        {
            properties = resolveProperties();
        }

        return properties;
    }

    protected abstract Properties resolveProperties()
        throws PropertiesOperationException;
}
