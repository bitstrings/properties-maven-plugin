package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.selector.SetSelector;
import org.bitstrings.maven.plugins.properties.util.SetSelectorHelper;

public class PropertiesTransformers
{
    public static class ValueTransformers
    {
        private final List<ValueTransformer> transformers = new ArrayList<ValueTransformer>();

        public void addValueTransformer( ValueTransformer transformer )
        {
            transformers.add( transformer );
        }

        public String transform( String value )
        {
            for ( ValueTransformer transformer : transformers )
            {
                value = transformer.transform( value );
            }

            return value;
        }
    }

    private SetSelector propertySet = new SetSelector();

    private final List<ValueTransformers> forKeys = new ArrayList<ValueTransformers>();

    private final List<ValueTransformers> forValues = new ArrayList<ValueTransformers>();

    public SetSelector getPropertySet()
    {
        return propertySet;
    }

    public void setPropertySet( SetSelector propertySet )
    {
        this.propertySet = propertySet;
    }

    public List<ValueTransformers> getForKeys()
    {
        return forKeys;
    }

    public List<ValueTransformers> getForValues()
    {
        return forValues;
    }

    public void transform( Properties properties )
    {
        final List<String> selectedPropertyNames =
                        SetSelectorHelper.regExfilter( propertySet, properties.stringPropertyNames() );

        final Properties transformedProperties = new Properties();

        for ( String propertyName : selectedPropertyNames )
        {
            String propertyValue = properties.getProperty( propertyName );

            for ( ValueTransformers transformers : forKeys )
            {
                propertyName = transformers.transform( propertyName );
            }

            for ( ValueTransformers transformers : forValues )
            {
                propertyValue = transformers.transform( propertyValue );
            }

            transformedProperties.setProperty( propertyName, propertyValue );
        }

        properties.keySet().removeAll( selectedPropertyNames );

        properties.putAll( transformedProperties );

        System.out.println( transformedProperties );
    }
}
