package org.bitstrings.maven.plugins.properties;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Splitter;

public class SelectSet
{
    private List<String> includes;

    private List<String> excludes;

    public List<String> getIncludes()
    {
        return includes;
    }

    public void setIncludes( List<String> includes )
    {
        this.includes = includes;
    }

    public List<String> getExcludes()
    {
        return excludes;
    }

    public void setExcludes( List<String> excludes )
    {
        this.excludes = excludes;
    }

    public void set( String value )
    {
        includes =
            Splitter.on( ',' )
                .omitEmptyStrings()
                .trimResults()
                .splitToList( value );
    }

    public Properties resolve( Properties source )
    {
        final Properties resolved = new Properties();

        for ( Map.Entry<Object, Object> entry : source.entrySet() )
        {
            String name = (String) entry.getKey();

            for ( String include : includes )
            {
                boolean match;

                if ( name.matches( include ) )
                {
                    match = true;

                    for ( String exclude : excludes )
                    {
                        if ( name.matches( exclude ) )
                        {
                            match = false;

                            break;
                        }
                    }

                    if ( match )
                    {
                        resolved.setProperty( name, (String) entry.getValue() );

                        break;
                    }
                }
            }
        }

        return resolved;
    }
}
