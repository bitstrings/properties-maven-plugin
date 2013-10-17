package org.bitstrings.maven.plugins.properties;

import static java.lang.String.format;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.selector.SetSelector;
import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class FromMavenProperties
    extends PropertiesProvider
{
    public static final String SOURCE_PROJECT = "project";
    public static final String SOURCE_SYSTEM = "system";

    private String source = SOURCE_PROJECT;

    private SetSelector selectSet = new SetSelector();

    public String getSource()
    {
        return source;
    }

    public void setSource( String source )
    {
        this.source = source;
    }

    public SetSelector getSelectSet()
    {
        return selectSet;
    }

    public void setSelectSet( SetSelector selectSet )
    {
        this.selectSet = selectSet;
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
                            format( "Unknown source '%s'.", source ) );
        }

        return PropertiesHelper.regExFilter( selectSet, sourceProperties );
    }
}
