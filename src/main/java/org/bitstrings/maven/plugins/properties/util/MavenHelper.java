package org.bitstrings.maven.plugins.properties.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;

import com.google.common.base.Strings;

public final class MavenHelper
{
    private MavenHelper() {}

    public static Set<Artifact> filterArtifacts( Set<Artifact> artifacts, List<String> includes, List<String> excludes )
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
                    for ( Artifact artifact : artifacts )
                    {
                        if ( new ArtifactMatcher( include ).match( artifact ) )
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
                for ( Artifact artifact : artifacts )
                {
                    if ( new ArtifactMatcher( exclude ).match( artifact ) )
                    {
                        selectedArtifactSet.remove( artifact );

                        break;
                    }
                }
            }
        }

        return selectedArtifactSet;
    }
}
