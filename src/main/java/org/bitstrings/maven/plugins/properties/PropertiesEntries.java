package org.bitstrings.maven.plugins.properties;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;

public class PropertiesEntries
    extends DefineProperties
{
    public void addProperty( Map.Entry<String, String> property )
    {
        properties.put( property.getKey(), property.getValue() );
    }

    public void set( String value )
    {
        StringReader in = new StringReader( value );

        try
        {
            properties.load( in );
        }
        catch ( IOException e )
        {

        }
        finally
        {
            in.close();
        }
    }
}
