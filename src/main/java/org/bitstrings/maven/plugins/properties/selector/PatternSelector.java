package org.bitstrings.maven.plugins.properties.selector;

import org.bitstrings.maven.plugins.properties.PatternSet;

public class PatternSelector
    extends PatternSet
    implements Selector
{
    @Override
    public boolean accept( String value )
    {
        return false;
    }
}
