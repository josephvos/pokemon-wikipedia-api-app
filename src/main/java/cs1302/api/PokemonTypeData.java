package cs1302.api;

import java.net.http.HttpClient;
import javafx.application.Application;
import java.util.List;
import cs1302.api.PokemonType;
import cs1302.api.PokemonDataTypeHigh;


/**
   The second highest class in the PokeAPi retrieval. This
   contains the slot (Which type is being used) and the
   piece of data that contains the information about the pokemon
   type.
 */
public class PokemonTypeData {

    public PokemonType type;
    public int slot;


    /**
       Gets the slot.
       @return the number the type is
    */

    public int getSlot() {
        return slot;
    }

    /**
       Sets the slot.
       @param slot the slot.
    */

    public void setSlot(int slot) {
        this.slot = slot;
    }

    /**
       Gets the type data.
       @return the type data
    */
    public PokemonType getType() {
        return type;
    }

    /** Sets the type data.
        @param type the type data.
    */

    public void setType(PokemonType type) {
        this.type = type;
    }


}
