package org.bitstrings.maven.plugins.properties;

public class PropertiesOperationException
    extends RuntimeException
{
    public PropertiesOperationException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public PropertiesOperationException( String message )
    {
        super( message );
    }

    public PropertiesOperationException( Throwable cause )
    {
        super( cause );
    }
}
