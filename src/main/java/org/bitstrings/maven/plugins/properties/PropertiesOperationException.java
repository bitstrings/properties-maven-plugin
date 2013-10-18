package org.bitstrings.maven.plugins.properties;

public class PropertiesOperationException
    extends RuntimeException
{
    private final PropertiesOperation propertiesOperation;

    public PropertiesOperationException( PropertiesOperation propertiesOperation, String message, Throwable cause )
    {
        super( message, cause );

        this.propertiesOperation = propertiesOperation;
    }

    public PropertiesOperationException( PropertiesOperation propertiesOperation, String message )
    {
        super( message );

        this.propertiesOperation = propertiesOperation;
    }

    public PropertiesOperationException( PropertiesOperation propertiesOperation, Throwable cause )
    {
        super( cause );

        this.propertiesOperation = propertiesOperation;
    }

    public PropertiesOperation getPropertiesOperation()
    {
        return propertiesOperation;
    }
}
