package org.bitstrings.maven.plugins.properties.selector;

public class DependencySetSelector
{
    private SetSelector dependencySet;

    private String scope = "compile";

    private boolean excludeSnapshots = false;

    private boolean transitive = true;

    public SetSelector getDependencySet()
    {
        return dependencySet;
    }

    public void setDependencySet( SetSelector dependencySet )
    {
        this.dependencySet = dependencySet;
    }

    public String getScope()
    {
        return scope;
    }

    public void setScope( String scope )
    {
        this.scope = scope;
    }

    public boolean isExcludeSnapshots()
    {
        return excludeSnapshots;
    }

    public void setExcludeSnapshots( boolean excludeSnapshots )
    {
        this.excludeSnapshots = excludeSnapshots;
    }

    public boolean isTransitive()
    {
        return transitive;
    }

    public void setTransitive( boolean transitive )
    {
        this.transitive = transitive;
    }
}
