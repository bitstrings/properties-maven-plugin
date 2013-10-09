package org.bitstrings.maven.plugins.properties;

public class SetProperties
    extends BasePropertiesSetter
{
    public static final String TARGET_PROJECT = "project";
    public static final String TARGET_SYSTEM = "system";

    private String target = TARGET_PROJECT;

    private boolean override = true;

    public boolean isOverride()
    {
        return override;
    }

    public void setOverride( boolean override )
    {
        this.override = override;
    }

    public String getTarget()
    {
        return target;
    }

    public void setTarget( String target )
    {
        this.target = target;
    }
}
