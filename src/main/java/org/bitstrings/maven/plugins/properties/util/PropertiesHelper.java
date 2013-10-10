package org.bitstrings.maven.plugins.properties.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.google.common.io.Closer;

public final class PropertiesHelper
{
    private PropertiesHelper() {}

    public static Properties loadProperties( File file, boolean isXml )
        throws IOException
    {
        final Properties properties = new Properties();

        Closer closer = Closer.create();
        try
        {
            if ( isXml )
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

        return properties;
    }
}
