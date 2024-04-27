package cs1302.api;

import java.net.http.HttpClient;
import javafx.application.Application;
import java.util.List;
import cs1302.api.PokemonType;
import cs1302.api.PokemonDataTypeHigh;


/**
 * Represents a result in a response from the iTunes Search API. This is
 * used by Gson to create an object from the JSON response body. This class
 * is provided with project's starter code, and the instance variables are
 * intentionally set to package private visibility.
 * @see <a href="https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/UnderstandingSearchResults.html#//apple_ref/doc/uid/TP40017632-CH8-SW1">Understanding Search Results</a>
 */
public class PokemonTypeData {
    //  String id;
    //  String name;
    //  String sprites;

    //   public PokemonType type;
    //  public int slot;
    public PokemonType type;
    public int slot;


/*
    public PokemonTypeData(int slot, PokemonType type) {
        this.slot = slot;
        this.type = type;
    }
    */

    public int getSlot() {
        return slot;
    }

    public PokemonType getType() {
        return type;
    }

    public void setType(PokemonType type) {
        this.type = type;
    }
/*
    @Override
    public String toString() {
        String result2 = "Pokemon Types:\n";
        for (PokemonType types : type) {
            result2 += type.toString() + "\n";
            //     result.getName();
        }
        return result2;
    }
*/
    // int slot;
    // the rest of the result is intentionally omitted since we don't use it
} //
