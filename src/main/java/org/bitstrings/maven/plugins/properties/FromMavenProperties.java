package org.bitstrings.maven.plugins.properties;

import static java.lang.String.format;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class FromMavenProperties
    extends PropertiesProvider
{
    public static final String SOURCE_PROJECT = "project";
    public static final String SOURCE_SYSTEM = "system";

    private String source = SOURCE_PROJECT;

    private SelectSet selectSet = new SelectSet();

    public String getSource()
    {
        return source;
    }

    public void setSource( String source )
    {
        this.source = source;
    }

    public SelectSet getSelectSet()
    {
        return selectSet;
    }

    public void setSelectSet( SelectSet selectSet )
    {
        this.selectSet = selectSet;
    }

    @Override
    protected Properties resolveProperties()
        throws PropertiesOperationException
    {
        Properties targetProperties = new Properties();

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

        PropertiesHelper.filter( selectSet, sourceProperties, targetProperties );

        return targetProperties;
    }
}
