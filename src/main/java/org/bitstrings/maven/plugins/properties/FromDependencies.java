package org.bitstrings.maven.plugins.properties;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.bitstrings.maven.plugins.properties.selector.DependencySetSelector;

public class FromDependencies
    extends PropertiesProvider
{
    private List<DependencySetSelector> dependencySets;

    public List<DependencySetSelector> getDependencySets()
    {
        return dependencySets;
    }

    public void setDependencySets( List<DependencySetSelector> dependencySets )
    {
        this.dependencySets = dependencySets;
    }

    @Override
    protected Properties resolveProperties()
    {
        final Properties properties = new Properties();

        final MavenProject project = getContext().getMavenProject();

        final Set<Artifact> artifacts = project.getArtifacts();


        return properties;
    }

}
