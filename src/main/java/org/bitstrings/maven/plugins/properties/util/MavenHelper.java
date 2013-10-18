package org.bitstrings.maven.plugins.properties.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;

import com.google.common.base.Strings;

public final class MavenHelper
{
    private MavenHelper() {}

    public static Set<Artifact> filterArtifacts(
            Set<Artifact> artifacts, List<String> includes, List<String> excludes )
    {
        final Set<Artifact> selectedArtifactSet = new HashSet<Artifact>();

        if ( ( includes == null ) || includes.isEmpty() )
        {
            selectedArtifactSet.addAll( artifacts );
        }
        else
        {
            for ( String include : includes )
            {
                if ( Strings.isNullOrEmpty( include ) )
                {
                    selectedArtifactSet.addAll( artifacts );
                }
                else
                {
                    final ArtifactMatcher artifactMatcher = new ArtifactMatcher( include );

                    for ( Artifact artifact : artifacts )
                    {
                        if ( artifactMatcher.match( artifact ) )
                        {
                            selectedArtifactSet.add( artifact );

                            break;
                        }
                    }
                }
            }
        }

        for ( String exclude : excludes )
        {
            if ( Strings.isNullOrEmpty( exclude ) )
            {
                selectedArtifactSet.clear();

                break;
            }
            else
            {
                final ArtifactMatcher artifactMatcher = new ArtifactMatcher( exclude );

                for ( Artifact artifact : artifacts )
                {
                    if ( artifactMatcher.match( artifact ) )
                    {
                        selectedArtifactSet.remove( artifact );

                        break;
                    }
                }
            }
        }

        return selectedArtifactSet;
    }

    public static Set<Artifact> filterArtifacts( Set<Artifact> artifacts, Collection<ArtifactFilter> filters )
    {


        return null;
    }
}
