package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.firstNonNull;

import java.util.Collections;
import java.util.List;

import com.google.common.base.Splitter;

public class PatternSet
{
    private List<String> includes;

    private List<String> excludes;

    public List<String> getIncludes()
    {
        return firstNonNull( includes, Collections.EMPTY_LIST );
    }

    public void setIncludes( List<String> includes )
    {
        this.includes = includes;
    }

    public List<String> getExcludes()
    {
        return firstNonNull( excludes, Collections.EMPTY_LIST );
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
