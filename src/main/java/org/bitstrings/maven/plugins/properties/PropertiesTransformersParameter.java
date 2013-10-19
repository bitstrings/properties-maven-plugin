package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.selector.SetSelector;
import org.bitstrings.maven.plugins.properties.transformer.ValueTransformer;
import org.bitstrings.maven.plugins.properties.util.SetSelectorHelper;

public class PropertiesTransformersParameter
{
    private SetSelector propertySet = new SetSelector();

    private final List<ValueTransformer> forKeys = new ArrayList<ValueTransformer>();

    private final List<ValueTransformer> forValues = new ArrayList<ValueTransformer>();

    public SetSelector getPropertySet()
    {
        return propertySet;
    }

    public void setPropertySet( SetSelector propertySet )
    {
        this.propertySet = propertySet;
    }

    public List<ValueTransformer> getForKeys()
    {
        return forKeys;
    }

    public List<ValueTransformer> getForValues()
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

            for ( ValueTransformer transformer : forKeys )
            {
                propertyName = transformer.transform( propertyName );

                if ( propertyName == null )
                {
                    break;
                }
            }

            for ( ValueTransformer transformer : forValues )
            {
                propertyValue = transformer.transform( propertyValue );

                if ( propertyValue == null )
                {
                    propertyValue = "";
                }
            }

            if ( propertyName != null )
            {
                transformedProperties.setProperty( propertyName, propertyValue );
            }
        }

        System.out.println( " -P- " + properties );

        properties.keySet().removeAll( selectedPropertyNames );

        properties.putAll( transformedProperties );

        System.out.println( " -T- " + transformedProperties );
    }
}
