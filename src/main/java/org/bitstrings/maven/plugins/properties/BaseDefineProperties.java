package org.bitstrings.maven.plugins.properties;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.project.MavenProject;

public class BaseDefineProperties<C extends BaseDefineProperties.Callback>
{
    public static class Callback
    {
        public Map.Entry<String, String> processProperty( Map.Entry<String, String> property )
        {
            return property;
        }
    }

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

    public void defineProperties( MavenProject project, Properties properties, C callback )
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
            throw new IllegalStateException( "Unknow target: " + target );
        }

        for ( Map.Entry<String, String> entry :
                    ( (Set<Map.Entry<String, String>>) (Set<?>) properties.entrySet() ) )
        {
            final String name = entry.getKey();

            if ( override || !targetProperties.containsKey( name ) )
            {
                if ( callback != null )
                {
                    entry = callback.processProperty( entry );
                }

                targetProperties.setProperty( name, entry.getValue() );
            }
        }
    }
}
