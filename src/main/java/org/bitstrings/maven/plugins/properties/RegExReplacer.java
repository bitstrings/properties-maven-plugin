package org.bitstrings.maven.plugins.properties;

public class RegExReplacer
    implements ValueTransformer
{
    private String match;

    private String replacement;

    private boolean replaceAll = true;

    @Override
    public String transform( String value )
    {
        return
                replaceAll
                    ? value.replaceAll( match, replacement )
                    : value.replaceFirst( match, replacement );
    }
}
