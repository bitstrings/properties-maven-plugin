package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

public class PropertiesWriters
    implements PropertiesOperationExecutor
{
    private final List<PropertiesWriter> writers = new ArrayList<PropertiesWriter>();

    public List<PropertiesWriter> getWriters()
    {
        return writers;
    }

    @Override
    public void execute( PropertiesPluginContext context )
    {
        for ( PropertiesWriter writer : writers  )
        {
            writer.setContext( context );
            writer.write();
        }
    }
}
