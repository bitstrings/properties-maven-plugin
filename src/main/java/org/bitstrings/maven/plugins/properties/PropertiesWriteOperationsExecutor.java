package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.writer.PropertiesWriter;
import org.bitstrings.maven.plugins.properties.writer.MavenEnvironmentWriter;
import org.bitstrings.maven.plugins.properties.writer.PropertiesFileWriter;

public class PropertiesWriteOperationsExecutor
    implements PropertiesOperationExecutor
{
    private final List<PropertiesWriter> writers = new ArrayList<PropertiesWriter>();

    public void addToMavenEnvironment( MavenEnvironmentWriter writer )
    {
        writers.add( writer );
    }

    public void addToPropertiesFile( PropertiesFileWriter writer )
    {
        writers.add( writer );
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
