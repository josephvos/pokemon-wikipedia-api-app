package cs1302.api;

import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;


/**
   This class is the deepsest class inside the ImageWikipedia classes. It has the
   direct, specific information about the particular image and provides the needed
   url to show the image on the screen.
 */

public class ImageWikipediaImages {


    private String timestamp;
    private String descriptionurl;
    private String user;
    private String url;
    private String descriptionshorturl;

    /**
       Gets the timestamp of the image retrieval.
       @return timestamp the timestamp of the image retrieval.
    */

    public String getTimestamp() {
        return timestamp;
    }

    /**
       Sets the timestamp of the image retrieval.
       @param timestamp is the timestamp of the image retrieval.
    */

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
       Gets the description of the image url.
       @return descriptionurl the description of the image url.
    */

    public String getDescriptionUrl() {
        return descriptionurl;
    }

    /**
       Sets the description url.
       @param descriptionurl is the description of the image url.
    */

    public void setDescriptionUrl(String descriptionurl) {
        this.descriptionurl = descriptionurl;
    }

    /**
       Gets the user of the image retriever.
       @return user the name of the image retriever.
    */

    public String getUser() {
        return user;
    }

    /**
       Sets the name of the image retriever.
       @param user the name of the image retriever.
    */

    public void setUser(String user) {
        this.user = user;
    }

    /**
       Gets the necessary url to show the image.
       @return url the url to show the image.
    */

    public String getUrl() {
        return url;
    }
    /**
       Sets the url to show the image.
       @param url the url to show the image.
    */

    public void setUrl(String url) {
        this.url = url;
    }
    /**
       Gets a shorter version of the url.
       @return descriptionshorturl a shorter version of the url.
    */

    public String getDescriptionShortUrl() {
        return descriptionshorturl;
    }
    /**
     Sets a shorter description of the url.
     @param descriptionshorturl is a shorter version of the url.
    */

    public void setShortDescriptionUrl(String descriptionshorturl) {
        this.descriptionshorturl = descriptionshorturl;
    }


}
