package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

public class PropertiesOperations
    implements PropertiesOperationExecutor
{
    private final List<PropertiesOperationExecutor> operationExecutors = new ArrayList<PropertiesOperationExecutor>();

    public void addDefine( PropertiesDefineOperations defineParameter )
    {
        operationExecutors.add( defineParameter );
    }

    public void addWrite( PropertiesWriteOperations writeParameter )
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
