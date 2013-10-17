package org.bitstrings.maven.plugins.properties.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.selector.SetSelector;

import com.google.common.base.Splitter;

public final class SetSelectorHelper
{
    private SetSelectorHelper() {}

    public static List<String> regExfilter( SetSelector selectSet, Collection<String> source )
    {
        final List<String> result = new LinkedList<String>();

        for ( String elem : source )
        {
            for ( String include : selectSet.getIncludes() )
            {
                if ( elem.matches( include ) )
                {
                    result.add( elem );

                    break;
                }
            }
        }

        for ( Iterator<String> resultIter = result.listIterator(); resultIter.hasNext(); )
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
