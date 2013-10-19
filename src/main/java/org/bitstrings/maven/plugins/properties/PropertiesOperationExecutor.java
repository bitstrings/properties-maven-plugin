package org.bitstrings.maven.plugins.properties;


public interface PropertiesOperationExecutor
{
    void execute( PropertiesPluginContext context )
        throws PropertiesOperationException;
}
