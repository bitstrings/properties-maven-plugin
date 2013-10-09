package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;


public class BasePropertiesSetter
{
    private List<SelectPropertiesSet> selectPropertiesSets = new ArrayList<SelectPropertiesSet>();

    public List<SelectPropertiesSet> getSelectPropertiesSets()
    {
        return selectPropertiesSets;
    }

    public void addSelectPropertiesSet( SelectPropertiesSet selectPropertiesSet )
    {
        selectPropertiesSets.add( selectPropertiesSet );
    }


//    public void defineProperties( PropertiesProvider provider )
//    {
//        Properties targetProperties;
//
//        if ( TARGET_PROJECT.equalsIgnoreCase( target ) )
//        {
//            targetProperties = getMavenProject().getProperties();
//
//        }
//        else if ( TARGET_SYSTEM.equalsIgnoreCase( target ) )
//        {
//            targetProperties = System.getProperties();
//        }
//        else
//        {
//            throw new IllegalStateException( "Unknow target: " + target );
//        }
//
//        Map.Entry<String, String> property;
//
//        while ( ( property = provider.nextProperty() ) != null )
//        {
//            final String name = property.getKey();
//
//            if ( override || !targetProperties.containsKey( name ) )
//            {
//                targetProperties.setProperty( name, property.getValue() );
//            }
//
//        }
//    }
}
