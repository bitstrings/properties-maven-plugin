package org.bitstrings.maven.plugins.properties;


public class BasePropertiesSetter
{
    private SelectSet selectProperties;

    private SelectSet selectGroups;

    public SelectSet getSelectProperties()
    {
        return selectProperties;
    }

    public void setSelectProperties( SelectSet selectProperties )
    {
        this.selectProperties = selectProperties;
    }

    public SelectSet getSelectGroups()
    {
        return selectGroups;
    }

    public void setSelectGroups( SelectSet selectGroups )
    {
        this.selectGroups = selectGroups;
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
