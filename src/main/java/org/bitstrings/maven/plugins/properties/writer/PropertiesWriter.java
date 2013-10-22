package org.bitstrings.maven.plugins.properties.writer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.PropertiesOperation;
import org.bitstrings.maven.plugins.properties.PropertiesOperationException;
import org.bitstrings.maven.plugins.properties.PropertiesPatternSet;

public abstract class PropertiesWriter
    extends PropertiesOperation
{
    private List<PropertiesPatternSet> propertiesSets = new ArrayList<PropertiesPatternSet>();

    public List<PropertiesPatternSet> getPropertiesSets()
    {
        return propertiesSets;
    }

    public void addPropertiesSet( PropertiesPatternSet propertiesSet )
    {
        propertiesSets.add( propertiesSet );
    }

    public Properties getSelectedProperties()
    {
        return getContext().getProperties( propertiesSets );
    }

    public void write()
        throws PropertiesOperationException
    {
        final Properties properties = getSelectedProperties();

        transform( properties );

        writeProperties( properties );
    }

    protected abstract void writeProperties( Properties properties )
        throws PropertiesOperationException;
}
