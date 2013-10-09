package org.bitstrings.maven.plugins.properties;

import java.io.File;

public class ReadPropertiesFile
    extends PropertiesProvider
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

//    public void readFiles( Callback callback )
//    {
//        for ( ReadableFile readableFile : fromFiles )
//        {
//            final File file = readableFile.getFile();
//
//            if ( !file.exists() )
//            {
//                if ( callback != null )
//                {
//                    callback.missingFile( readableFile );
//                }
//
//                continue;
//            }
//
//            Properties properties = new Properties();
//
//            try
//            {
//                Closer closer = Closer.create();
//                try
//                {
//                    if ( readableFile.isXmlFormat() )
//                    {
//                        BufferedInputStream in =
//                                        closer.register(
//                                            new BufferedInputStream( new FileInputStream( file ) ) );
//
//                        properties.loadFromXML( in );
//                    }
//                    else
//                    {
//                        BufferedReader in =
//                                        closer.register(
//                                            new BufferedReader( new FileReader( file ) ) );
//
//                        properties.load( in );
//                    }
//
//                    if ( callback != null )
//                    {
//                        callback.loadedProperties( properties, readableFile );
//                    }
//                }
//                finally
//                {
//                    closer.close();
//                }
//            }
//            catch ( IOException e )
//            {
//                if ( callback != null )
//                {
//                    callback.errorReadingFile( new PropertiesSetterException( e ), readableFile );
//                }
//            }
//        }
//    }
}
