package org.bitstrings.maven.plugins.properties.util;

import java.util.Map;

public final class MapHelper
{
    private MapHelper() {}

    public static <K, V> void putAllIfAbsent( Map<K, V> source, Map<? super K, ? super V> target )
    {
        for ( Map.Entry<K, V> entry : source.entrySet() )
        {
            if ( !target.containsKey( entry.getKey() ) )
            {
                target.put( entry.getKey(), entry.getValue() );
            }
        }
    }
}
