package org.bitstrings.maven.plugins.properties.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.bitstrings.maven.plugins.properties.selector.SetSelector;

import com.google.common.base.Splitter;

public final class SetSelectorHelper
{
    private SetSelectorHelper() {}

    public static List<String> regExfilter( SetSelector selectSet, Collection<String> source )
    {
        final List<String> result = new LinkedList<String>();

        if ( ( selectSet.getIncludes() == null ) || selectSet.getIncludes().isEmpty() )
        {
            result.addAll( source );
        }
        else
        {
            for ( String include : selectSet.getIncludes() )
            {
                final Pattern pattern = Pattern.compile( include );

                for ( String elem : source )
                {
                    if ( pattern.matcher( elem ).matches() )
                    {
                        result.add( elem );

                        break;
                    }
                }
            }
        }

        if ( selectSet.getExcludes() != null )
        {
            for ( String exclude : selectSet.getExcludes() )
            {
                final Pattern pattern = Pattern.compile( exclude );

                for ( Iterator<String> resultIter = result.listIterator(); resultIter.hasNext(); )
                {
                    if ( pattern.matcher( resultIter.next() ).matches() )
                    {
                        resultIter.remove();

                        break;
                    }
                }
            }
        }

        return result;
    }

    public static boolean matchWildcard( String expression, String source )
    {
        boolean firstIsFixed = ( '*' != expression.charAt( 0 ) );

        for (
                String part
                    : Splitter.on( '*' )
                            .omitEmptyStrings()
                            .trimResults()
                            .split( expression ) )
        {
            int index = source.indexOf( part );

            if ( index == -1 || ( firstIsFixed && index > 0 ) )
            {
                return false;
            }
        }

        return true;
    }
}
