package org.bitstrings.maven.plugins.properties.source;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.PropertiesOperation;
import org.bitstrings.maven.plugins.properties.PropertiesOperationException;

public abstract class PropertiesSource
    extends PropertiesOperation
{
    public static final String DEFAULT_GROUP_NAME = "default";

    private String groupName = DEFAULT_GROUP_NAME;

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
        final Properties properties = resolveProperties();

        transform( properties );

        return properties;
    }

    protected abstract Properties resolveProperties()
                    throws PropertiesOperationException;
}
