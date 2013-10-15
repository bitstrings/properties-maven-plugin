package org.bitstrings.maven.plugins.properties;

import java.util.Properties;

public class FromDependencies
    extends PropertiesProvider
{
    private SelectSet dependencySet;

    public SelectSet getDependencySet()
    {
        return dependencySet;
    }

    public void setDependencySet( SelectSet dependencySet )
    {
        this.dependencySet = dependencySet;
    }

    @Override
    protected Properties resolveProperties()
    {
        return null;
    }
}
