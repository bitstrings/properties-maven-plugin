package org.bitstrings.maven.plugins.properties;

import java.util.Properties;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public class PropertiesOperation
{
    private PropertiesPluginContext context;

    private Log log;

    private String operationTag = this.getClass().getSimpleName();

    private PropertiesTransformersParameter transformers;

    public PropertiesPluginContext getContext()
    {
        return context;
    }

    public void setContext( PropertiesPluginContext context )
    {
        this.context = context;

        this.log = context.getMojo().getLog();
    }

    public MavenProject getMavenProject()
    {
        return context.getMavenProject();
    }

    public String getOperationTag()
    {
        return operationTag;
    }

    public void setOperationTag( String operationTag )
    {
        this.operationTag = operationTag;
    }

    public PropertiesTransformersParameter getTransformers()
    {
        return transformers;
    }

    public void transform( Properties properties )
    {
        if ( transformers == null )
        {
            return;
        }

        transformers.transform( properties );
    }

    public Log getLog()
    {
        return log;
    }
}
