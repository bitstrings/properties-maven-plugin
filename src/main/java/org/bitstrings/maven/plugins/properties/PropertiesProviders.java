package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

public class PropertiesProviders
    implements PropertiesOperationExecutor
{
    private final List<PropertiesProvider> providers = new ArrayList<PropertiesProvider>();

    public List<PropertiesProvider> getProviders()
    {
        return providers;
    }

    @Override
    public void execute( PropertiesPluginContext context )
        throws PropertiesOperationException
    {
        for ( PropertiesProvider provider : providers  )
        {
            context.addPropertiesProvider( provider );
            provider.setContext( context );
        }
    }
}
