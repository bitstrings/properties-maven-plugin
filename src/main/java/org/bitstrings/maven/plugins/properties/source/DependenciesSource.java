package org.bitstrings.maven.plugins.properties.source;

import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.project.MavenProject;
import org.bitstrings.maven.plugins.properties.DependencyPatternSet;

public class DependenciesSource
    extends PropertiesSource
{
    private List<DependencyPatternSet> dependencySets;

    public List<DependencyPatternSet> getDependencySets()
    {
        return dependencySets;
    }

    public void setDependencySets( List<DependencyPatternSet> dependencySets )
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
