package org.bitstrings.maven.plugins.properties.XXX;

import java.util.List;

import org.bitstrings.maven.plugins.properties.BasePropertiesSetter;

public class DefineFromDependencies
{
    public static class SelectDependencies
    {
        private List<String> includes;

        private List<String> excludes;

        public List<String> getIncludes()
        {
            return includes;
        }

        public List<String> getExcludes()
        {
            return excludes;
        }
    }

    public static class Define
        extends BasePropertiesSetter
    {

    }
}
