package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;


/**
   Contains the direct data about the images including the actual
   name space and the title of the images.
 */
public class WikipediaImages {


    private int ns;
    private String title;

    /**
       Gets the actual name space of the images.
       @return ns the name space
    */

    public int getNs() {
        return ns;
    }

    /**
       Sets the actual name space of the images.
       @param ns the name space of the images.
    */

    public void setNs(int ns) {
        this.ns = ns;
    }

    /**
       Gets the actual title of the images.
       @return title the actual title of the images.
    */

    public String getTitle() {
        return title;
    }

    /**
       Sets the actual title of the images.
       @param title the actual title of the images.
    */

    public void setTitle(String title) {
        this.title = title;
    }








}
