package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;


/**
   Contains all the data from the pages. Also contains images
   which contains all of the other specific data from the website.
 */
public class WikipediaPages {



    private int pageid;
    private int ns;
    private String title;
    private List<WikipediaImages> images;


    /**
       Gets the pageid from Wikipedia.
       @return pageid the page id
    */

    public int getPageid() {
        return pageid;
    }

    /**
       Sets the pageid.
       @param pageid the pageid from the website
    */

    public void setPageid(int pageid) {
        this.pageid = pageid;
    }

    /**
       Gets the namespace from Wikipedia.
       @return ns the namespace
    */

    public int getNs() {
        return ns;
    }

    /**
       Sets the namespace on Wikipedia.
       @param ns the namespace
    */

    public void setNs(int ns) {
        this.ns = ns;
    }

    /**
       Gets the title of the page.
       @return title the title of the page.
    */

    public String getTitle() {
        return title;
    }

    /**
       Sets the title of the page.
       @param title is the title of the page.
    */

    public void setTitle(String title) {
        this.title = title;
    }

    /**
       Gets a list of images from the page.
       @return images a list of images from the page.
    */

    public List<WikipediaImages> getImages() {
        return images;
    }

    /**
       Sets the images in the list of images from the page.
       @param images the images from Wikipedia.
    */

    public void setImages(List<WikipediaImages> images) {
        this.images = images;
    }






}
