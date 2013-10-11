package org.bitstrings.maven.plugins.properties;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public class PropertiesOperation
{
    private PropertiesPluginContext context;

    private Log log;

    private String operationTag;

    @Parameter
    private boolean verbose = false;

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

    public Log getLog()
    {
        return log;
    }

    public boolean isVerbose()
    {
        return verbose;
    }

    public void setVerbose( boolean verbose )
    {
        this.verbose = verbose;
    }
}
