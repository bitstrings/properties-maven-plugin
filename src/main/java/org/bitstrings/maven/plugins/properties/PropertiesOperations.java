package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.source.PropertiesSources;
import org.bitstrings.maven.plugins.properties.writer.PropertiesWriters;

public class PropertiesOperations
    implements PropertiesOperationExecutor
{
    private final List<PropertiesOperationExecutor> operationExecutors = new ArrayList<PropertiesOperationExecutor>();

    public void addDefine( PropertiesSources defineParameter )
    {
        operationExecutors.add( defineParameter );
    }

    public void addWrite( PropertiesWriters writeParameter )
    {
        operationExecutors.add( writeParameter );
    }

    @Override
    public void execute( PropertiesPluginContext context )
        throws PropertiesOperationException
    {
        for ( PropertiesOperationExecutor executor : operationExecutors  )
        {
            executor.execute( context );
        }
    }
}
