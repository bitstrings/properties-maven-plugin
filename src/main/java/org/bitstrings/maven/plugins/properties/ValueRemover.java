package org.bitstrings.maven.plugins.properties;

import static org.bitstrings.maven.plugins.properties.util.SetSelectorHelper.regExfilter;

import java.util.Collections;

import org.bitstrings.maven.plugins.properties.selector.SetSelector;

public class ValueRemover
    implements Transformer
{
    private SetSelector valueSet;

    public SetSelector getValueSet()
    {
        return valueSet;
    }

    public void setValueSet( SetSelector valueSet )
    {
        this.valueSet = valueSet;
    }

    @Override
    public String transform( String value )
    {
        return
                regExfilter( valueSet, Collections.singletonList( value ) ).isEmpty()
                        ? value
                        : null;
    }
}
