package org.bitstrings.maven.plugins.properties.selector;

public class NotSelectorSet
    extends SelectorSet
{
    @Override
    public boolean accept( String value )
    {
        return !super.accept( value );
    }
}
