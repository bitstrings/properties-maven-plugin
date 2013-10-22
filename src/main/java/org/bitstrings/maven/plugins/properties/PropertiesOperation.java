package org.bitstrings.maven.plugins.properties;

import java.util.Properties;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

public abstract class PropertiesOperation
{
    private PropertiesPluginContext context;

    private Log log;

    private String operationTag = this.getClass().getSimpleName();

    private PropertiesTransformers transformers;

    public PropertiesPluginContext getContext()
    {
        return context;
    }

    public void setContext( PropertiesPluginContext context )
    {
        this.context = context;

        this.log = context.getMojo().getLog();
    }

    protected MavenProject getMavenProject()
    {
        return context.getMavenProject();
    }

    public PropertiesTransformers getTransformers()
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

    protected Log getLog()
    {
        return log;
    }

    public String getOperationTag()
    {
        return operationTag;
    }

    public void setOperationTag( String operationTag )
    {
        this.operationTag = operationTag;
    }
}
