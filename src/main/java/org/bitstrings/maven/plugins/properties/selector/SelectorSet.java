package org.bitstrings.maven.plugins.properties.selector;

public class SelectorSet
    extends AbstractSelectorSet
{
    @Override
    public boolean accept( String value )
    {
        for ( Selector selector : getSelectors() )
        {
            if ( !selector.accept( value ) )
            {
                return false;
            }
        }

        return true;
    }
}
