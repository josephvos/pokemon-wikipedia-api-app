package cs1302.api;

import java.net.http.HttpClient;
import javafx.application.Application;
import java.util.List;
//import cs1302.api.PokemonType;


/**
 * Represents a result in a response from the iTunes Search API. This is
 * used by Gson to create an object from the JSON response body. This class
 * is provided with project's starter code, and the instance variables are
 * intentionally set to package private visibility.
 * @see <a href="https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/UnderstandingSearchResults.html#//apple_ref/doc/uid/TP40017632-CH8-SW1">Understanding Search Results</a>
 */
public class PokemonDataTypeHigh {
    //  String id;
    //  String name;
    //  String sprites;

    List<PokemonTypeData> types;

         public List<PokemonTypeData> getTypes() {
             return types;
         }

    public void setType(List<PokemonTypeData> types) {
        this.types = types;
    }


    // the rest of the result is intentionally omitted since we don't use it
} //
