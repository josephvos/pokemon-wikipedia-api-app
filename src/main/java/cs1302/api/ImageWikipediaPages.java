package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;


/**
   This class contains all the information on the page that isn't quite
   specific enough to be listed under imageinfo. It contains information
   about the page imageinfo is listed on.
 */
public class ImageWikipediaPages {


    private String imagerepository;
    private int ns;
    private String title;
    private List<ImageWikipediaImages> imageinfo;
    private boolean missing;
    private boolean known;

    /**
       Gets if the page is known.
       @return known if the page is found.
    */

    public boolean getKnown() {
        return known;
    }

    /**
       Sets if the page is known.
       @param known if the page is known.
    */

    public void setKnown(boolean known) {
        this.known = known;
    }

    /**
       Gets if the page is missing.
       @return missing if the page is missing.
    */

    public boolean getMissing() {
        return missing;
    }

    /**
       Sets if the page is missing.
       @param missing for a missing page
    */

    public void setMissing(boolean missing) {
        this.missing = missing;
    }

    /**
       Gets the image repository.
       @return imagerepository the image repository.
    */

    public String getImageRepository() {
        return imagerepository;
    }

    /**
       Sets the image repository.
       @param imagerepository is the image repository.
    */

    public void setImageRepository(String imagerepository) {
        this.imagerepository = imagerepository;
    }

    /**
       Gets the name state of the image.
       @return ns the name state.
    */

    public int getNs() {
        return ns;
    }

    /**
       Sets the name state of the image.
       @param ns is the name state.
    */

    public void setNs(int ns) {
        this.ns = ns;
    }

    /**
       Gets the title of the image.
       @return title is the title of the image
    */

    public String getTitle() {
        return title;
    }

    /**
       Sets the title of the image.
       @param title is the title of the image.
    */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
       Gets a list of info about the image.
       @return imageinfo the list of info.
    */

    public List<ImageWikipediaImages> getImageInfo() {
        return imageinfo;
    }

    /**
       Sets the list of info about the image.
       @param imageinfo the list of info.
    */

    public void setImages(List<ImageWikipediaImages> imageinfo) {
        this.imageinfo = imageinfo;
    }






}
