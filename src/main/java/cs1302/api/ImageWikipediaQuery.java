package cs1302.api;

import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;
import java.util.Map;


/**
   Retrieves the page information from Wikipedia about the image
   title that was just received.

 */
public class ImageWikipediaQuery {



    private Map<String, ImageWikipediaPages> pages;

    /**
       Gets the page information from Wikipedia.
       @return pages the page information from Wikipedia.
    */

    public Map<String, ImageWikipediaPages> getPages() {
        return pages;
    }

    /**
       Sets the pages information from Wikipedia.
       @param pages the pages information from Wikipedia.
    */

    public void setPages(Map<String, ImageWikipediaPages> pages) {
        this.pages = pages;
    }






}
