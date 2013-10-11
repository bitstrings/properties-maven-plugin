package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.toStringHelper;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.MapHelper;

public class WriteMavenProperties
    extends PropertiesSink
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

    @Override
    protected void writeProperties( Properties properties )
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

        if ( override )
        {
            targetProperties.putAll( properties );
        }
        else
        {
            MapHelper.putAllIfAbsent( properties, targetProperties );
        }
    }

    @Override
    public String toString()
    {
        return
                toStringHelper( this )
                .add( "target" , target )
                .add( "override", override )
                .toString();
    }
}
