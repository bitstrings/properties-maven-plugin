package org.bitstrings.maven.plugins.properties;

import java.util.List;

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
}
