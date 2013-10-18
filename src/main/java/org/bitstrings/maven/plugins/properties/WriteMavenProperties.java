package org.bitstrings.maven.plugins.properties;

import static java.lang.String.format;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.MapHelper;

public class WriteMavenProperties
    extends PropertiesWriter
{
    public static final String TARGET_PROJECT = "project";
    public static final String TARGET_SYSTEM = "system";

    private String target = TARGET_PROJECT;

    private boolean override = true;

    public boolean isOverride()
    {
        return override;
    }

    public void setOverride( boolean override )
    {
        this.override = override;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget( String target )
    {
        this.target = target;
    }

    public void set( String target )
    {
        setTarget( target );
    }

    @Override
    protected void writeProperties( Properties properties )
        throws PropertiesOperationException
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
            throw new PropertiesOperationException(
                            this,
                            format( "Unknown target '%s'.", target ) );
        }

        if ( override )
        {
            targetProperties.putAll( properties );
        }
        else
        {
            MapHelper.putAllIfAbsent( properties, targetProperties );
        }
    }
}
