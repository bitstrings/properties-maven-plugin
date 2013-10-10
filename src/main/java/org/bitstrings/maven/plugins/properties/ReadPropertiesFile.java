package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.toStringHelper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.common.io.Closer;

public class ReadPropertiesFile
    extends AbstractPropertiesProvider
{
    private File file;

    private boolean xmlFormat;

    private boolean ignoreMissing;

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

    public boolean isIgnoreMissing()
    {
        return ignoreMissing;
    }

    public void setIgnoreMissing( boolean ignoreMissing )
    {
        this.ignoreMissing = ignoreMissing;
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
            Closer closer = Closer.create();
            try
            {
                if ( isXmlFormat() )
                {
                    BufferedInputStream in = closer.register( new BufferedInputStream( new FileInputStream( file ) ) );

                    properties.loadFromXML( in );
                }
                else
                {
                    BufferedReader in = closer.register( new BufferedReader( new FileReader( file ) ) );

                    properties.load( in );
                }
            }
            finally
            {
                closer.close();
            }
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
                .add( "ignoreMissing", ignoreMissing )
                .addValue( super.toString() )
                .toString();
    }
}
