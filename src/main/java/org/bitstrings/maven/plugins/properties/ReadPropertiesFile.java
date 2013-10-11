package org.bitstrings.maven.plugins.properties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class ReadPropertiesFile
    extends PropertiesProvider
{
    private File file;

    private Boolean xmlFormat;

    private boolean ignoreMissingFile = false;

    public File getFile()
    {
        return file;
    }

    public void setFile( File file )
    {
        this.file = file;
    }

    public Boolean getXmlFormat()
    {
        return xmlFormat;
    }

    public void setXmlFormat( Boolean xmlFormat )
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
        throws PropertiesOperationException
    {
        try
        {
            PropertiesHelper.loadProperties( file, xmlFormat );
        }
        catch ( IOException e )
        {
            throw new PropertiesOperationException( e );
        }
    }
}
