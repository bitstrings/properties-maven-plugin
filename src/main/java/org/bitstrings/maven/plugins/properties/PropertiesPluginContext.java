package org.bitstrings.maven.plugins.properties;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.maven.execution.MavenSession;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.project.DefaultDependencyResolutionRequest;
import org.apache.maven.project.DependencyResolutionRequest;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectDependenciesResolver;
import org.bitstrings.maven.plugins.properties.source.PropertiesSource;
import org.bitstrings.maven.plugins.properties.util.PatternHelper;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class PropertiesPluginContext
{
    private static final PatternSet NO_SELECT = new PatternSet();

    static
    {
        NO_SELECT.set( PropertiesSource.DEFAULT_GROUP_NAME );
    }

    private static final List<PropertiesPatternSet>
                    NO_PROPERTIES_SELECT_LIST =
                                    Collections.singletonList( new PropertiesPatternSet() );

    private final MavenSession mavenSession;

    private final ProjectDependenciesResolver projectDependenciesResolver;

    private final Multimap<String, PropertiesSource>
                        groupedPropertiesSourcesMap =
                                ArrayListMultimap.<String, PropertiesSource> create();

    private final Map<String, Properties>
                        cachedPropertiesFromSourcesMap =
                                new HashMap<String, Properties>();

    private final AbstractMojo mojo;

    public PropertiesPluginContext(
                        MavenSession mavenSession,
                        ProjectDependenciesResolver projectDependenciesResolver,
                        AbstractMojo mojo )
    {
        this.mavenSession = mavenSession;
        this.projectDependenciesResolver = projectDependenciesResolver;
        this.mojo = mojo;
    }

    public AbstractMojo getMojo()
    {
        return mojo;
    }

    public MavenSession getMavenSession()
    {
        return mavenSession;
    }

    public ProjectDependenciesResolver getProjectDependenciesResolver()
    {
        return projectDependenciesResolver;
    }

    public MavenProject getMavenProject()
    {
        return mavenSession.getCurrentProject();
    }

    public DependencyResolutionRequest createDependencyResolutionRequest( )
    {
        return new DefaultDependencyResolutionRequest( getMavenProject(), getMavenSession().getRepositorySession() );
    }

    public void addPropertiesSource( PropertiesSource source )
    {
        groupedPropertiesSourcesMap.put( source.getGroupName(), source );
    }

    public Properties getProperties( String groupName )
    {
        Properties properties = cachedPropertiesFromSourcesMap.get( groupName );

        if ( properties != null )
        {
            return properties;
        }

        properties = new Properties();

        for ( PropertiesSource source : groupedPropertiesSourcesMap.get( groupName ) )
        {
            properties.putAll( source.getProperties() );
        }

        cachedPropertiesFromSourcesMap.put( groupName, properties );

        return properties;
    }

    public Properties getUnifiedProperties( List<String> groupsNames )
    {
        Properties properties = new Properties();

        for ( String groupName : groupsNames )
        {
            properties.putAll( getProperties( groupName ) );
        }

        return properties;
    }

    public Properties getProperties( PropertiesPatternSet selectPropertiesSet )
    {
        PatternSet groupSet = selectPropertiesSet.getGroupSet();

        if ( groupSet == null )
        {
            groupSet = NO_SELECT;
        }

        final List<String> selectedGroups =
                    PatternHelper.regExfilter(
                        groupSet,
                        groupedPropertiesSourcesMap.keySet() );

        final Properties properties = getUnifiedProperties( selectedGroups );

        final PatternSet propertiesSet = selectPropertiesSet.getPropertySet();

        if ( propertiesSet != null )
        {
            final List<String> selectedProperties =
                        PatternHelper.regExfilter(
                            propertiesSet,
                            properties.stringPropertyNames() );

            properties.keySet().retainAll( selectedProperties );
        }

        return properties;
    }

    public Properties getProperties( List<PropertiesPatternSet> selectPropertiesSets )
    {
        final Properties properties = new Properties();

        if ( selectPropertiesSets.isEmpty() )
        {
            selectPropertiesSets = NO_PROPERTIES_SELECT_LIST;
        }

        for ( PropertiesPatternSet selectPropertiesSet : selectPropertiesSets )
        {
            properties.putAll( getProperties( selectPropertiesSet ) );
        }

        return properties;
    }
}
