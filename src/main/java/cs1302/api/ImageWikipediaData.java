package cs1302.api;

import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;
import cs1302.api.WikipediaQuery;


/**
   The hgihest class in retrieving the actual url of the image
   from Wikipedia after having received the actual title of the image
   with a different call.
*/
public class ImageWikipediaData {


    private ImageWikipediaQuery query;

    /**
       Gets the query of the website from Wikipedia.
       @return query the query of data.
     */
    public ImageWikipediaQuery getQuery() {
        return query;

    }

    /**
       Sets the query of data from Wikipedia.
       @param query the query of data.
    */
    public void setQuery(ImageWikipediaQuery query) {
        this.query = query;
    }






}
