package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;
import java.util.Map;


/**
   The second highest level of the wikipedia query for the name
   of the image. Creates the pages that contain all the information
   on the page.
 */
public class WikipediaQuery {

    private Map<String, WikipediaPages> pages;


    /**
       Gets a map of the pages.
       @return pages the map of the pages
    */

    public Map<String, WikipediaPages> getPages() {
        return pages;
    }

    /**
       Sets the map of the pages.
       @param pages the pages from the website
    */

    public void setPages(Map<String, WikipediaPages> pages) {
        this.pages = pages;
    }



}
