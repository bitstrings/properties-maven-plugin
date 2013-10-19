package org.bitstrings.maven.plugins.properties.transformer;

import java.util.regex.Pattern;

public class RegExReplacer
    implements ValueTransformer
{
    private String match;

    private String replacement;

    private boolean caseInsensitive = false;

    private boolean replaceAll = true;

    @Override
    public String transform( String value )
    {
        final Pattern pattern =
                        Pattern.compile(
                                    match,
                                    caseInsensitive
                                            ? Pattern.CASE_INSENSITIVE
                                            : 0 );

        return
                replaceAll
                    ? pattern.matcher( value  ).replaceAll( replacement )
                    : pattern.matcher( value  ).replaceFirst( replacement );
    }
}
