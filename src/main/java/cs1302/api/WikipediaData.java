package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;
import cs1302.api.WikipediaQuery;


/**
  The highest class for the first call from Wikipedia to get
  the title of the image. Creates the query of all the data
  from the webpage.
 */
public class WikipediaData {



    private WikipediaQuery query;

    /**
    Gets the query of data from Wikipedia.
    @return query the query of data
    */

    public WikipediaQuery getQuery() {
        return query;

    }

    /**
       Sets the query.
       @param query the query from Wikipedia
    */

    public void setQuery(WikipediaQuery query) {
        this.query = query;
    }



}
