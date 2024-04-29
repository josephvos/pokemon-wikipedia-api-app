package cs1302.api;

import java.net.http.HttpClient;
import javafx.application.Application;
import java.util.List;


/**
 * This class is the highest class in the PokeAPI data. It has the types
 list which contains all other information to be used.
 */
public class PokemonDataTypeHigh {


    List<PokemonTypeData> types;

    /**
       Gets the type list.
       @return types list
     */

    public List<PokemonTypeData> getTypes() {
        return types;
    }

    /**
       Sets the type list.
       @param types is the list of pokemon types
    */

    public void setType(List<PokemonTypeData> types) {
        this.types = types;
    }


}
