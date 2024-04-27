package cs1302.api;


import java.util.List;
import java.util.ArrayList;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonDataTypeHigh;


/**
 * Represents a result in a response from the iTunes Search API. This is
 * used by Gson to create an object from the JSON response body. This class
 * is provided with project's starter code, and the instance variables are
 * intentionally set to package private visibility.
 * @see <a href="https://developer.apple.com/library/archive/documentation/AudioVideo/Conceptual/iTuneSearchAPI/UnderstandingSearchResults.html#//apple_ref/doc/uid/TP40017632-CH8-SW1">Understanding Search Results</a>
 */
public class ImageWikipediaPages {

    //  String title;
    // List<WikipediaData> images;
    //  int ns;
/*
    private Query query;
    public Query getQuery() {
        return query;

    }
    public void setQuery(Query quer) {
        this.query = query;
    }
*/

    private String imagerepository;
    private int ns;
    private String title;
    private List<ImageWikipediaImages> imageinfo;
    private boolean missing;
    private boolean known;

    public boolean getKnwon() {
        return known;
    }
    public void setKnown(boolean known) {
        this.known = known;
    }
    public boolean getMissing() {
        return missing;
    }
    public void setMissing(boolean missing) {
        this.missing = missing;
    }


    public String getImageRepository() {
        return imagerepository;
        }

        public void setImageRepository(String imagerepository) {
            this.imagerepository = imagerepository;
        }

        public int getNs() {
            return ns;
        }

        public void setNs(int ns) {
            this.ns = ns;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<ImageWikipediaImages> getImageInfo() {
            return imageinfo;
        }

        public void setImages(List<ImageWikipediaImages> imageinfo) {
            this.imageinfo = imageinfo;
        }


    //   public String getTitle() {
    //      return title;
    //   }
    //   public void setTitle(String title) {
        //        this.title = title;
          //   return title;
    //  }

/*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;

    }
    public void setUrl(String url) {
        this.url = url;
    }

   @Override
       public String toString() {
        //  String result = "toString PType.java: ";
       //  for (PokemonType type : types) {
       //  result += types.getName();
         String result = name;
         //  }
          return result;
          //  return name;
       }
*/



    //  public List<PokemonType> getTypes() {
    //      return types;
    //   }




} // ItunesResult
