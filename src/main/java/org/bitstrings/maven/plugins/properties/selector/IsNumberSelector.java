package org.bitstrings.maven.plugins.properties.selector;


public class IsNumberSelector
    implements Selector
{
    private Double minimum;

    private boolean minimumIsInclusive = false;

    private Double maximum;

    private boolean maximumIsInclusive = false;

    @Override
    public boolean accept( String value )
    {
        try
        {
            final double number = Double.parseDouble( value );

            if ( ( minimum == null ) || ( number < minimum ) || ( ( number == minimum ) && !minimumIsInclusive ) )
            {
                return false;
            }

            if ( ( maximum == null ) || ( number > maximum ) || ( ( number == maximum ) && !maximumIsInclusive ) )
            {
                return false;
            }
        }
        catch ( NumberFormatException e )
        {
            return false;
        }

        return true;
    }
}
