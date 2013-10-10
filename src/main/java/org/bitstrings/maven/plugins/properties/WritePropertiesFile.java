package org.bitstrings.maven.plugins.properties;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.util.PropertiesHelper;

public class WritePropertiesFile
    extends AbstractPropertiesSink
{
    public static final String MERGE_OVERRIDE = "override";

    public static final String MERGE_IF_ABSENT = "ifAbsent";

    public static final String MERGE_APPEND = "append";

    public static final String MERGE_SKIP = "skip";

    private File file;

    private boolean xmlFormat = false;

    private String mergeScheme = MERGE_OVERRIDE;

    private boolean overwrite = true;

    public File getFile()
    {
        return file;
    }

    public void setFile( File file )
    {
        this.file = file;
    }

    public void set( File file )
    {
        setFile( file );
    }

    public String getMergeScheme()
    {
        return mergeScheme;
    }

    public void setMergeScheme(String mergeScheme)
    {
        this.mergeScheme = mergeScheme;
    }

    public boolean isOverwrite()
    {
        return overwrite;
    }

    public void setOverwrite( boolean overwrite )
    {
        this.overwrite = overwrite;
    }

    @Override
    protected void writeProperties( Properties properties )
    {
        Properties targetProperties;

        if ( file.exists() )
        {
            if ( overwrite )
            {
                targetProperties = properties;
            }
            else
            {
                try
                {
                    if ( MERGE_SKIP.equalsIgnoreCase( mergeScheme ) )
                    {
                        return;
                    }

                    targetProperties = PropertiesHelper.loadProperties( file, xmlFormat );

                    if ( MERGE_APPEND.equalsIgnoreCase( mergeScheme ) )
                    {
                        properties.putAll( targetProperties );
                    }
                }
                catch ( IOException e )
                {
                }
            }
        }
    }
}
