package org.bitstrings.maven.plugins.properties;

import static java.lang.String.format;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class FromPropertiesEntries
    extends PropertiesProvider
{
    private final Properties properties = new Properties();

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
            throw new PropertiesOperationException(
                            format(
                                "Unable to parse given properties:%n%s",
                                value ) );
        }
        finally
        {
            in.close();
        }
    }

    @Override
    protected Properties resolveProperties()
    {
        return properties;
    }
}
