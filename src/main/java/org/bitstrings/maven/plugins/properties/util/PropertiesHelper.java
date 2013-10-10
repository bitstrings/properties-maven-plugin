package org.bitstrings.maven.plugins.properties.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Properties;

import com.google.common.io.Closer;
import com.google.common.io.Files;

public final class PropertiesHelper
{
    private PropertiesHelper() {}

    public static boolean isXmlFormat( File file )
    {
        return Files.getFileExtension( file.getName() ).equalsIgnoreCase( "xml" );
    }

    public static Properties loadProperties( File file, Boolean isXml )
        throws IOException
    {
        if ( isXml == null )
        {
            isXml = isXmlFormat( file );
        }

        final Properties properties = new Properties();

        Closer closer = Closer.create();
        try
        {
            if ( isXml )
            {
                InputStream in = closer.register( new BufferedInputStream( new FileInputStream( file ) ) );

                properties.loadFromXML( in );
            }
            else
            {
                Reader in = closer.register( new BufferedReader( new FileReader( file ) ) );

                properties.load( in );
            }
        }
        finally
        {
            closer.close();
        }

        return properties;
    }

    public static void writeProperties( File file, Boolean isXml, Properties properties, String comments )
        throws IOException
    {
        final File parent = file.getParentFile();

        if ( parent != null )
        {
            parent.mkdirs();
        }

        if ( isXml == null )
        {
            isXml = isXmlFormat( file );
        }

        Closer closer = Closer.create();
        try
        {
            if ( isXml )
            {
                OutputStream os = closer.register( new BufferedOutputStream( new FileOutputStream( file ) ) );

                properties.storeToXML( os, comments );
            }
            else
            {
                Writer os = closer.register( new BufferedWriter( new FileWriter( file ) ) );

                properties.store( os, comments );
            }
        }
        finally
        {
            closer.close();
        }
    }
}
