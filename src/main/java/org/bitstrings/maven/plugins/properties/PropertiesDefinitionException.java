package org.bitstrings.maven.plugins.properties;

public class PropertiesDefinitionException
    extends RuntimeException
{
    public PropertiesDefinitionException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public PropertiesDefinitionException( String message )
    {
        super( message );
    }

    public PropertiesDefinitionException( Throwable cause )
    {
        super( cause );
    }
}
