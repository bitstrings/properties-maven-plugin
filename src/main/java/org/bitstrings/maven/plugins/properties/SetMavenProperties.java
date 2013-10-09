package org.bitstrings.maven.plugins.properties;

import static com.google.common.base.Objects.toStringHelper;

public class SetMavenProperties
    extends PropertiesSetter
{
    public static final String TARGET_PROJECT = "project";
    public static final String TARGET_SYSTEM = "system";

    private String target = TARGET_PROJECT;

    private boolean override = true;

    public boolean isOverride()
    {
        return override;
    }

    public void setOverride( boolean override )
    {
        this.override = override;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget( String target )
    {
        this.target = target;
    }

    //  public void defineProperties( PropertiesProvider provider )
//  {
//      Properties targetProperties;
//
//      if ( TARGET_PROJECT.equalsIgnoreCase( target ) )
//      {
//          targetProperties = getMavenProject().getProperties();
//
//      }
//      else if ( TARGET_SYSTEM.equalsIgnoreCase( target ) )
//      {
//          targetProperties = System.getProperties();
//      }
//      else
//      {
//          throw new IllegalStateException( "Unknow target: " + target );
//      }
//
//      Map.Entry<String, String> property;
//
//      while ( ( property = provider.nextProperty() ) != null )
//      {
//          final String name = property.getKey();
//
//          if ( override || !targetProperties.containsKey( name ) )
//          {
//              targetProperties.setProperty( name, property.getValue() );
//          }
//
//      }
//  }

    @Override
    public String toString()
    {
        return
                toStringHelper( this )
                .add( "target" , target )
                .add( "override", override )
                .toString();
    }
}
