package org.bitstrings.maven.plugins.properties.source;

import static java.lang.String.format;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.PropertiesOperationException;

import com.google.common.io.Files;

public class PropertiesEntriesSource
    extends PropertiesSource
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
            throws Exception;
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
            throws IOException
        {
            return Files.toString( file, Charset.forName( charset ) );
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
                            this,
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

        for ( AbstractProperty property : properties )
        {
            try
            {
                revolvedProperties.put( property.getName(), property.getValue() );
            }
            catch ( Exception e )
            {
                throw new PropertiesOperationException( this, e );
            }
        }

        return revolvedProperties;
    }
}
