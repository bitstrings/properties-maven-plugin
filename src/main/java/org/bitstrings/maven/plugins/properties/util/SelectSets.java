package org.bitstrings.maven.plugins.properties.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.SelectSet;

public final class SelectSets
{
    private SelectSets() {}

    public static List<String> filter( SelectSet selectSet, Collection<String> source )
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
}
