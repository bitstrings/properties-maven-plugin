package org.bitstrings.maven.plugins.properties.transformer;

import static org.bitstrings.maven.plugins.properties.util.PatternHelper.regExfilter;

import java.util.Collections;

import org.bitstrings.maven.plugins.properties.PatternSet;

public class ValueRemover
    implements Transformer
{
    private PatternSet valueSet;

    public PatternSet getValueSet()
    {
        return valueSet;
    }

    public void setValueSet( PatternSet valueSet )
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
