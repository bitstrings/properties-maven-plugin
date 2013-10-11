package org.bitstrings.maven.plugins.properties;

import static java.lang.String.format;

import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class MavenProperties
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
    protected void resolveProperties( Properties properties )
        throws PropertiesOperationException
    {
        Properties targetProperties;

        if ( SOURCE_PROJECT.equalsIgnoreCase( source ) )
        {
            targetProperties = getMavenProject().getProperties();

        }
        else if ( SOURCE_PROJECT.equalsIgnoreCase( source ) )
        {
            targetProperties = System.getProperties();
        }
        else
        {
            throw new PropertiesOperationException(
                            format( "Unknown source '%s'.", source ) );
        }

        PropertiesHelper.filter( selectSet, targetProperties, properties );
    }
}
