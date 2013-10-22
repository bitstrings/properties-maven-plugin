package org.bitstrings.maven.plugins.properties.writer;

import java.util.ArrayList;
import java.util.List;

import org.bitstrings.maven.plugins.properties.PropertiesOperationExecutor;
import org.bitstrings.maven.plugins.properties.PropertiesPluginContext;

public class PropertiesWriters
    implements PropertiesOperationExecutor
{
    private final List<PropertiesWriter> writers = new ArrayList<PropertiesWriter>();

    public void addToMavenEnvironment( MavenEnvironmentWriter writer )
    {
        writer.setOperationTag( "ToMavenEnvironment" );

        writers.add( writer );
    }

    public void addToPropertiesFile( PropertiesFileWriter writer )
    {
        writer.setOperationTag( "ToPropertiesFile" );

        writers.add( writer );
    }

    public void addToSystemOut( SystemOutWriter writer )
    {
        writer.setOperationTag( "ToSystemOut" );

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
