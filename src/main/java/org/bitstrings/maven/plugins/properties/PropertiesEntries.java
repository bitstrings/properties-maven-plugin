package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.toStringHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

public class PropertiesEntries
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

        }
        finally
        {
            in.close();
        }
    }

    @Override
    protected void resolveProperties( Properties properties )
    {
        properties.putAll( this.properties );
    }

    @Override
    public String toString()
    {
        return
                toStringHelper( this )
                .add( "properties", properties )
                .addValue( super.toString() )
                .toString();
    }
}
