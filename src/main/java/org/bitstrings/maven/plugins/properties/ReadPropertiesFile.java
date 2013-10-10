package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class ReadPropertiesFile
    extends AbstractPropertiesProvider
{
    private File file;

    private boolean xmlFormat = false;

    private boolean ignoreMissingFile = false;

    public File getFile()
    {
        return file;
    }

    public void setFile( File file )
    {
        this.file = file;
    }

    public boolean isXmlFormat()
    {
        return xmlFormat;
    }

    public void setXmlFormat( boolean xmlFormat )
    {
        this.xmlFormat = xmlFormat;
    }

    public boolean isIgnoreMissingFile()
    {
        return ignoreMissingFile;
    }

    public void setIgnoreMissingFile( boolean ignoreMissingFile )
    {
        this.ignoreMissingFile = ignoreMissingFile;
    }

    public void set( File file )
    {
        this.file = file;
    }

    @Override
    protected void resolveProperties( Properties properties )
    {
        try
        {
            PropertiesHelper.loadProperties( file, xmlFormat );
        }
        catch ( IOException e )
        {
        }
    }

    @Override
    public String toString()
    {
        return
                toStringHelper( this )
                .add( "file", file )
                .add( "xmlFormat", xmlFormat )
                .add( "ignoreMissing", ignoreMissingFile )
                .addValue( super.toString() )
                .toString();
    }
}
