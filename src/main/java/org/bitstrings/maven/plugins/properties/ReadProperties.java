package org.bitstrings.maven.plugins.properties;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import com.google.common.io.Closer;

public class ReadProperties
    extends BaseDefineProperties
{
    public static class Callback
    {
        public void loadedProperties( Properties properties, ReadableFile readableFile ) {}

        public void missingFile( ReadableFile readableFile ) {}

        public void errorReadingFile( DefinePropertiesException e, ReadableFile readableFile ) {}
    }

    public static class ReadableFile
    {
        private File file;

        private boolean xmlFormat;

        private boolean ignoreMissing;

        public File getFile()
        {
            return file;
        }

        public boolean isXmlFormat()
        {
            return xmlFormat;
        }

        public boolean isIgnoreMissing()
        {
            return ignoreMissing;
        }
    }

    private List<ReadableFile> fromFiles;

    public List<ReadableFile> getFromFiles()
    {
        return fromFiles;
    }

    public void readFiles( Callback callback )
    {
        for ( ReadableFile readableFile : fromFiles )
        {
            final File file = readableFile.getFile();

            if ( !file.exists() )
            {
                if ( callback != null )
                {
                    callback.missingFile( readableFile );
                }

                continue;
            }

            Properties properties = new Properties();

            try
            {
                Closer closer = Closer.create();
                try
                {
                    if ( readableFile.isXmlFormat() )
                    {
                        BufferedInputStream in =
                                        closer.register(
                                            new BufferedInputStream( new FileInputStream( file ) ) );

                        properties.loadFromXML( in );
                    }
                    else
                    {
                        BufferedReader in =
                                        closer.register(
                                            new BufferedReader( new FileReader( file ) ) );

                        properties.load( in );
                    }

                    if ( callback != null )
                    {
                        callback.loadedProperties( properties, readableFile );
                    }
                }
                finally
                {
                    closer.close();
                }
            }
            catch ( IOException e )
            {
                if ( callback != null )
                {
                    callback.errorReadingFile( new DefinePropertiesException( e ), readableFile );
                }
            }
        }
    }

    @Override
    public void defineProperties(PropertiesProvider provider)
    {
        // TODO Auto-generated method stub
        super.defineProperties(provider);
    }
}
