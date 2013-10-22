package org.bitstrings.maven.plugins.properties.selector;

public class PropertiesSelector
    implements Selector
{
    private SelectorSet keysSelectors;

    private SelectorSet valuesSelectors;

    @Override
    public boolean accept( String value )
    {
        return ( ( keysSelectors == null ) || keysSelectors.accept( value ) )
                        && ( ( valuesSelectors == null ) || valuesSelectors.accept( value ) );
    }
}
