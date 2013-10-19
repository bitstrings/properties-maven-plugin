package org.bitstrings.maven.plugins.properties.source;

import static java.lang.String.format;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.PropertiesOperationException;
import org.bitstrings.maven.plugins.properties.selector.SetSelector;
import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class MavenEnvironmentSource
    extends PropertiesSource
{
    public static final String SOURCE_PROJECT = "project";
    public static final String SOURCE_SYSTEM = "system";

    private String source = SOURCE_PROJECT;

    private SetSelector propertySet = new SetSelector();

    public String getSource()
    {
        return source;
    }

    public void setSource( String source )
    {
        this.source = source;
    }

    public SetSelector getPropertySet()
    {
        return propertySet;
    }

    public void setPropertySet( SetSelector propertySet )
    {
        this.propertySet = propertySet;
    }

    @Override
    protected Properties resolveProperties()
        throws PropertiesOperationException
    {
        Properties sourceProperties;

        if ( SOURCE_PROJECT.equalsIgnoreCase( source ) )
        {
            sourceProperties = getMavenProject().getProperties();

        }
        else if ( SOURCE_PROJECT.equalsIgnoreCase( source ) )
        {
            sourceProperties = System.getProperties();
        }
        else
        {
            throw new PropertiesOperationException(
                            this,
                            format( "Unknown source '%s'.", source ) );
        }

        return PropertiesHelper.regExFilter( propertySet, sourceProperties );
    }
}