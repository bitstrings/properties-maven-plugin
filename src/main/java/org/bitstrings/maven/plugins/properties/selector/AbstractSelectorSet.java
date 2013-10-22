package org.bitstrings.maven.plugins.properties.selector;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSelectorSet
    implements Selector
{
    private final List<Selector> selectors = new ArrayList<Selector>();

    public List<Selector> getSelectors()
    {
        return selectors;
    }

    public void addNot( NotSelectorSet selector )
    {
        selectors.add( selector );
    }

    public void addOr( OrSelectorSet selector )
    {
        selectors.add( selector );
    }

    public void addSelectorSet( SelectorSet selector )
    {
        selectors.add( selector );
    }

    public void addIsNumber( IsNumberSelector selector )
    {
        selectors.add( selector );
    }
}
