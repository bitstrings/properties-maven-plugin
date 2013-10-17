package org.bitstrings.maven.plugins.properties.util;

import static org.bitstrings.maven.plugins.properties.util.SetSelectorHelper.matchWildcard;

import java.util.Arrays;
import java.util.List;

import org.apache.maven.artifact.Artifact;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

public class ArtifactMatcher
{
    public static final String MATCH_ALL_PATTERN = "::::";

    private static final List<String> MATCH_ALL_PATTERN_APPEND = Arrays.asList( MATCH_ALL_PATTERN );

    private static final int MAX_COMPONENTS = 4;

    private final String[] components;

    public ArtifactMatcher( String pattern )
    {
        this.components =
                    Iterables.toArray(
                            Iterables.concat(
                                Splitter.on( ':' ).trimResults().limit( MAX_COMPONENTS ).split( pattern ),
                                MATCH_ALL_PATTERN_APPEND ),
                        String.class );
    }

    public boolean match( Artifact artifact )
    {
        return
                matchWildcard( components[0], artifact.getGroupId() )
                    && matchWildcard( components[1], artifact.getArtifactId() )
                    && matchWildcard( components[2], artifact.getType() )
                    && matchWildcard( components[3], artifact.getClassifier() );
    }
}
