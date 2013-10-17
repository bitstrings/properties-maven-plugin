package org.bitstrings.maven.plugins.properties;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.google.common.io.Files;

public class FromPropertiesEntries
    extends PropertiesProvider
{
    private final List<Property> properties = new ArrayList<Property>();

    protected static abstract class AbstractProperty
    {
        private String name;

        public String getName()
        {
            return name;
        }

        public void setName( String name )
        {
            this.name = name;
        }

        public abstract String getValue()
            throws PropertiesOperationException;
    }

    public static class Property
        extends AbstractProperty
    {
        private String value;

        public Property() {}

        public Property( String name, String value )
        {
            setName( name );
            this.value = value;
        }

        @Override
        public String getValue()
        {
            return value;
        }
    }

    public static class PropertyValueFromFile
        extends AbstractProperty
    {
        private File file;

        private String charset = "UTF-8";

        @Override
        public String getValue()
            throws PropertiesOperationException
        {
            try
            {
                return Files.toString( file, Charset.forName( charset ) );
            }
            catch ( IOException e )
            {
                throw new PropertiesOperationException( "Unable to set property [" + getName() + "].", e );
            }
        }
    }

    public void addProperty( Property property )
    {
        properties.add( property );
    }

    public void set( String value )
    {
        final Properties resolvedProperties = new Properties();

        final StringReader in = new StringReader( value );

        try
        {
            resolvedProperties.load( in );

            for ( Map.Entry entry : resolvedProperties.entrySet() )
            {
                properties.add( new Property( (String) entry.getKey(), (String) entry.getValue() ) );
            }
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
        throws PropertiesOperationException
    {
        final Properties revolvedProperties = new Properties();

        for ( Property property : properties )
        {
            revolvedProperties.put( property.getName(), property.getValue() );
        }

        return revolvedProperties;
    }
}
