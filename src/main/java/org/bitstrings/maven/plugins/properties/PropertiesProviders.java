package org.bitstrings.maven.plugins.properties;

import java.util.ArrayList;
import java.util.List;

public class PropertiesProviders
    implements PropertiesOperationExecutor
{
    private final List<PropertiesProvider> providers = new ArrayList<PropertiesProvider>();

}
