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
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.plugin.logging.Log;
import org.bitstrings.maven.plugins.properties.SelectSet;

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

    public static String propertyToString( Map.Entry<String, String> property )
    {
        return "[" + property.getKey() + "=" + property.getValue() + "]";
    }

    public static void logOperationProperties( Log log, String operationTag, Properties properties, String text )
    {
        final StringBuilder sb = new StringBuilder();

        sb.append( "-[" );
        sb.append( operationTag );
        sb.append( "] " );

        if ( text != null )
        {
            sb.append( text );
            sb.append( ' ' );
        }

        sb.append( "--" );

        log.info( sb.toString() );

        final Set<Map.Entry<String, String>> entrySet =
                            (Set<Map.Entry<String, String>>) (Set<?>) properties.entrySet();

        if ( entrySet.isEmpty() )
        {
            log.info( " --> {NO PROPERTIES}" );
        }
        else
        {
            for ( Map.Entry<String, String> property : entrySet )
            {
                log.info( " --> " + PropertiesHelper.propertyToString( property ) );
            }
        }
    }

    public static void filter( SelectSet selectSet, Properties source, Properties target )
    {
        for ( Map.Entry<String, String> elem : (Set<Map.Entry<String, String>>) (Set<?>) source.entrySet() )
        {
            for ( String include : selectSet.getIncludes() )
            {
                if ( elem.getKey().matches( include ) )
                {
                    target.put( elem.getKey(), elem.getValue() );

                    break;
                }
            }
        }

        for ( Iterator<String> resultIter = (Iterator) target.keySet().iterator(); resultIter.hasNext(); )
        {
            String groupName = resultIter.next();

            for ( String exclude : selectSet.getExcludes() )
            {
                if ( groupName.matches( exclude ) )
                {
                    resultIter.remove();

                    break;
                }
            }
        }
    }

    public static Properties filter( SelectSet selectSet, Properties source )
    {
        final Properties target = new Properties();

        filter( selectSet, source, target );

        return target;
    }
}
