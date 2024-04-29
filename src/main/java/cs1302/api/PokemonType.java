package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;


/**
   The lowest level in the PokeAPI classes. This contains the direct
   name and url of the type data. This is enclosed inside the type data.
 */
public class PokemonType {

    private String name;
    private String url;

    /**
       Gets the name of the type.
       @return name the name of the type
     */
    public String getName() {
        return name;
    }

    /**
       Sets the name of the type.
       @param name the name of the type.
    */

    public void setName(String name) {
        this.name = name;
    }

    /**
       Gets the url of the type.
       @return the url of the type.
    */

    public String getUrl() {
        return url;
    }

    /**
       Sets the url of the type.
       @param url the url of the type.
    */

    public void setUrl(String url) {
        this.url = url;
    }

    /**
      A to string method to return the actual
      name of the type instead of a bunch
      of unreadable data.

       @return result the name of the tpye
    */

    @Override
       public String toString() {
        String result = name;
        return result;
    }


}
