package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.transformer.Transformer;
import org.bitstrings.maven.plugins.properties.util.PatternHelper;

public class PropertiesTransformers
{
    private PatternSet propertySet = new PatternSet();

    private final List<Transformer> forKeys = new ArrayList<Transformer>();

    private final List<Transformer> forValues = new ArrayList<Transformer>();

    public PatternSet getPropertySet()
    {
        return propertySet;
    }

    public void setPropertySet( PatternSet propertySet )
    {
        this.propertySet = propertySet;
    }

    public List<Transformer> getForKeys()
    {
        return forKeys;
    }

    public List<Transformer> getForValues()
    {
        return forValues;
    }

    public void transform( Properties properties )
    {
        final List<String> selectedPropertyNames =
                        PatternHelper.regExfilter( propertySet, properties.stringPropertyNames() );

        final Properties transformedProperties = new Properties();

        for ( String propertyName : selectedPropertyNames )
        {
            String propertyValue = properties.getProperty( propertyName );

            for ( Transformer transformer : forKeys )
            {
                propertyName = transformer.transform( propertyName );

                if ( propertyName == null )
                {
                    break;
                }
            }

            for ( Transformer transformer : forValues )
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

        properties.keySet().removeAll( selectedPropertyNames );

        properties.putAll( transformedProperties );
    }
}
