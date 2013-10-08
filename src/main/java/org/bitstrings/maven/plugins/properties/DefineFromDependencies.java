package org.bitstrings.maven.plugins.properties;

import java.util.List;

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
        extends BaseDefineProperties
    {

    }
}
