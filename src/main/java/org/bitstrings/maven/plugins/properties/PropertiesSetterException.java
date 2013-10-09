package org.bitstrings.maven.plugins.properties;

public class PropertiesSetterException
    extends RuntimeException
{
    public PropertiesSetterException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public PropertiesSetterException( String message )
    {
        super( message );
    }

    public PropertiesSetterException( Throwable cause )
    {
        super( cause );
    }
}
