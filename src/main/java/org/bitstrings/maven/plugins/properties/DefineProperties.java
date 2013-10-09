package org.bitstrings.maven.plugins.properties;

import java.io.IOException;
import java.io.StringReader;

public class DefineProperties
    extends PropertiesDefiner
{
    public static class Property
    {
        private String name;

        private String value;

        public String getName()
        {
            return name;
        }

        public String getValue()
        {
            return value;
        }
    }

    public void addProperty( Property property )
    {
        properties.put( property.getName(), property.getValue() );
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
