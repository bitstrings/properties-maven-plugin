package org.bitstrings.maven.plugins.properties.writer;

import java.io.IOException;
import java.util.Properties;

import org.bitstrings.maven.plugins.properties.PropertiesOperationException;

public class SystemOutWriter
    extends PropertiesWriter
{
    private String preText;

    private String postText;

    private boolean xmlFormat = false;

    private String comments;

    public String getPreText()
    {
        return preText;
    }

    public void setPreText( String preText )
    {
        this.preText = preText;
    }

    public String getPostText()
    {
        return postText;
    }

    public void setPostText( String postText )
    {
        this.postText = postText;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments( String comments )
    {
        this.comments = comments;
    }

    public boolean isXmlFormat()
    {
        return xmlFormat;
    }

    public void setXmlFormat( boolean xmlFormat )
    {
        this.xmlFormat = xmlFormat;
    }

    @Override
    protected void writeProperties( Properties properties )
        throws PropertiesOperationException
    {
        if ( preText != null )
        {
            System.out.println( preText );
        }

        try
        {
            if ( xmlFormat )
            {
                properties.storeToXML( System.out, comments );
            }
            else
            {
                properties.store( System.out, comments );
            }
        }
        catch ( IOException e )
        {
            throw new PropertiesOperationException( this, e );
        }

        if ( postText != null )
        {
            System.out.println( postText );
        }
    }
}
