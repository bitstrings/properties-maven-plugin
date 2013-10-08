package org.bitstrings.maven.plugins.properties;

import java.util.Map;
import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class BaseDefineProperties
{
    public static final String TARGET_MAVEN = "maven";
    public static final String TARGET_SYSTEM = "system";

    private String target = TARGET_MAVEN;

    private boolean override = true;

    public String getTarget()
    {
        return target;
    }

    public boolean isOverride()
    {
        return override;
    }

    public void setProperty( MavenProject project, Properties properties )
        throws PropertiesDefinitionException
    {
        Properties targetProperties;

        if ( TARGET_MAVEN.equalsIgnoreCase( target ) )
        {
            targetProperties = project.getProperties();

        }
        else if ( TARGET_SYSTEM.equalsIgnoreCase( target ) )
        {
            targetProperties = System.getProperties();
        }
        else
        {
            throw new PropertiesDefinitionException( "Unknow target: " + target );
        }

        if ( override )
        {
            targetProperties.putAll( properties );
        }
        else
        {
            for ( Map.Entry<Object, Object> entry : properties.entrySet() )
            {
                final String name = (String) entry.getKey();

                if ( !targetProperties.containsKey( name ) )
                {
                    targetProperties.setProperty( name, (String) entry.getValue() );
                }
            }
        }
    }
}
