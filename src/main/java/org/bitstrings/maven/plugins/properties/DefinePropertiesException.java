package org.bitstrings.maven.plugins.properties;

public class DefinePropertiesException
    extends RuntimeException
{
    public DefinePropertiesException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public DefinePropertiesException( String message )
    {
        super( message );
    }

    public DefinePropertiesException( Throwable cause )
    {
        super( cause );
    }
}
