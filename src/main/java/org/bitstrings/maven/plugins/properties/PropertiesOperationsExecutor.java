package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

public class PropertiesOperationsExecutor
    implements PropertiesOperationExecutor
{
    private final List<PropertiesOperationExecutor> operationExecutors = new ArrayList<PropertiesOperationExecutor>();

    public void addDefine( PropertiesDefineOperationsExecutor defineParameter )
    {
        operationExecutors.add( defineParameter );
    }

    public void addWrite( PropertiesWriteOperationsExecutor writeParameter )
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
