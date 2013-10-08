package org.bitstrings.maven.plugins.properties;

import java.util.Map;
import java.util.Properties;

import org.apache.maven.project.MavenProject;

public class BaseDefineProperties
{
    public static interface PropertiesProvider
    {
        Map.Entry<String, String> nextProperty();
    }

    public static final String TARGET_PROJECT = "project";
    public static final String TARGET_SYSTEM = "system";

    private MavenProject mavenProject;

    private String target = TARGET_PROJECT;

    private boolean override = true;

    public MavenProject getMavenProject()
    {
        return mavenProject;
    }

    public void setMavenProject(MavenProject mavenProject)
    {
        this.mavenProject = mavenProject;
    }

    public String getTarget()
    {
        return target;
    }

    public boolean isOverride()
    {
        return override;
    }

    public void defineProperties( PropertiesProvider provider )
    {
        Properties targetProperties;

        if ( TARGET_PROJECT.equalsIgnoreCase( target ) )
        {
            targetProperties = getMavenProject().getProperties();

        }
        else if ( TARGET_SYSTEM.equalsIgnoreCase( target ) )
        {
            targetProperties = System.getProperties();
        }
        else
        {
            throw new IllegalStateException( "Unknow target: " + target );
        }

        Map.Entry<String, String> property;

        while ( ( property = provider.nextProperty() ) != null )
        {
            final String name = property.getKey();

            if ( override || !targetProperties.containsKey( name ) )
            {
                targetProperties.setProperty( name, property.getValue() );
            }

        }
    }
}
