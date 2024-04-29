package cs1302.api;

import java.net.http.HttpClient;
import javafx.application.Application;
import javafx.application.Platform; // An exorbitant amount of import statements
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.google.gson.Gson;
import cs1302.api.WikipediaData;
import com.google.gson.GsonBuilder;
import java.io.InputStream;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import cs1302.api.ApiDriver;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.TilePane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.IOException;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonType;
import cs1302.api.PokemonDataTypeHigh;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Uses the PokeAPI to accept a user input of a pokemon name and then, using the types
 from the pokemon, searches Wikipedia to pull an image of where the pokemon would
 live if it existed in real life.
 */
public class ApiApp extends Application {

    Stage stage;
    Scene scene;
    HBox root;
    List<PokemonType> types; // creates a ton of variables and some new http
    // clients. I know that it said we didn't have to do this but I made a bunch
    // and it was already too late.

    /** HTTP client. */
    public static final HttpClient HTTP_WIKI2 = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    public static Gson GSON_WIKI2 = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)           // uses HTTP protocol version 2 where possible
        .followRedirects(HttpClient.Redirect.NORMAL)  // always redirects, except from HTTPS to HTTP
        .build();                                     // builds and returns a HttpClient object

    /** Google {@code Gson} object for parsing JSON-formatted strings. */
    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()                          // enable nice output when printing
        .create();                                    // builds and returns a Gson object

    public static final HttpClient HTTP_WIKI = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    public static Gson GSON_WIKI = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    HBox urlLayer;
    TextField url;
    static Button pokePicker;
    HBox botHBox;
    HBox superBot;
    HBox textBot;
    ImageView imgView;
    VBox bigBox;
    Text upperText = new Text("Search:");
    static Text changeText = new Text();
    Text bottomText = new Text("Images provided by iTunes Search API.");
    TilePane manyImages;
    HBox bot2Hbox;
    static String userInput;
    static String body;
    static List<String> convertList = new ArrayList<String>();
    String printableList;
    int tempCount = 0;
    static String body2;
    static String wikiType;
    static List<String> wikiList = new ArrayList<String>();
    static String wikiImageFinal;
    static List<String> finalImages = new ArrayList<String>();
    static String finalImage1;
    static Text bottomChangeText = new Text();
    static Text botTextEnd = new Text();

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public ApiApp() {
        this.stage = null;
        this.scene = null; // Initializes a bunch of stuff.
        this.root = new HBox(15); // Not really hard to understand what these all do.
        bigBox = new VBox();
        superBot = new HBox();
        imgView = new ImageView();
        textBot = new HBox();
        url = new TextField("Charmander");
        botHBox = new HBox();
        bot2Hbox = new HBox(3);
        manyImages = new TilePane();
        pokePicker = new Button("Pokemon Pick");
        urlLayer = new HBox();
        botTextEnd.setText("Waiting for input...\n");
        changeText.setText("Type In The Name Of A Pokemon" +
            " And I'll Tell You Where To Find It!");
        changeText.setFont(Font.font("TimesRoman", 10));
        bottomChangeText.setText("Created By Joey Vos Using PokeAPI & WikipediaAPI");
        bottomChangeText.setFont(Font.font("TimesRoman", 10));
        botTextEnd.setFont(Font.font("TimesRoman", 10));

    }
    /** {@inheritDoc}
     *
     *  Creates the hboxes and vboxes and initializes a blank spot for images.
     Also has the call for the Image Button Click
     */

    @Override
    public void init() {
        System.out.println("init() called");
        for (int i = 0; i < 1; i++) {
            ImageView imageView = new ImageView();
            imageView.setFitWidth(175); // adds the sizes
            imageView.setFitHeight(320);
            manyImages.getChildren().add(imageView);
        }
        // A bunch of things that add all of the hboxs to vboxes to make the pop up look right
        root.getChildren().addAll(upperText, urlLayer, pokePicker);
        botHBox.getChildren().addAll(manyImages);
        textBot.getChildren().addAll(botTextEnd);
        superBot.getChildren().addAll(bottomChangeText);
        urlLayer.getChildren().addAll(url);
        bigBox.getChildren().addAll(root, changeText, botHBox, textBot, superBot);
        pokeButton();
    } // init




   /** {@inheritDoc}

 Starts the JavaFX and sets the stage.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage; // sets the stage along with sizing
        this.scene = new Scene(bigBox, 360, 392); // Had problems with sizing so manually input it
        this.stage.setOnCloseRequest(event -> Platform.exit());
        this.stage.setTitle("PokemonFinder!");
        this.stage.setScene(this.scene);
        this.stage.sizeToScene();
        this.stage.show();
        Platform.runLater(() -> this.stage.setResizable(false));
    } // start

        /** {@inheritDoc}
Stops the JavaFX.
     */
    @Override
    public void stop() {
        // stops the code
        System.out.println("stop() called");
    } // stop

     /**

      Sees if the Pokemon name the user input is valid and if so
      searches the PokeAPI for data about the Pokemon.

       Dr. Coterell showed us the majority of this code in class.

       @param url is the url that the user tries to retrieve from PokeAPI.
       @return the body received from pokeAPI
       @throw an IOException if the body is bad

    */


    public static String fetchString(String url) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                    .build();
            HttpResponse<String> response
                = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                body = response.body().strip();
            } else {
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return body;
    }

     /**

      Uses the data from PokeAPI to search for a page and name of an image
      that exists on WikipediaAPI.

       Dr. Coterell showed us the majority of this code in class.

       @param url2 is the url that the user tries to retrieve from WikipediaAPI.
       @return the body received from WikipediaAPI
       @throw an IOException if the body is bad

    */

    public static String fetchImage(String url2) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                    .build();
            HttpResponse<String> response
                = HTTP_WIKI.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                body = response.body().strip();

            } else {
                System.out.println("Failed to fetch image: " + wikiType);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return body;
    }

     /**

    Uses the name of image receieved from Wikipedia API to further look
    into WikipediaAPI and find the actual url of the image so it can
    be shown in the app.

       Dr. Coterell showed us the majority of this code in class.

       @param url3 is the url that the user tries to retrieve from WikipediaAPI.
       @return the body received from WikipediaAPI
       @throw an IOException if the body is bad

    */

    public static String fetchImagev2(String url3) {
        try {
            HttpRequest request3 = HttpRequest.newBuilder()
                .uri(URI.create(url3))
                    .build();
            HttpResponse<String> response3
                = HTTP_WIKI2.send(request3, HttpResponse.BodyHandlers.ofString());

            if (response3.statusCode() == 200) {
                body = response3.body().strip();

            } else {
                return null;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("fail");
            e.printStackTrace();
            return null;
        }
        return body;
    }

    /**
       Uses the name of the first image that appears on the Wikipedia page and
       formats it into a new searchable url.

       @param term2 is the term used for searching.
       @return url2 is the new url used for searching after this method concludes.
     */

    public String downloadImage(String term2) {
        String url2 = "https://en.wikipedia.org/w/api.php?action=query&prop=images" +
            "&titles=" + wikiType + "&format=json"; // creates the new url

        return url2;

    }

    /**
       The method for fetching the name of the first image and returning
       the data as a json.

       @param term2 is the term the user inputted to search.
       @return wiki is the response from Wikipedia
     */

    public WikipediaData fetchWiki(String term2) {
        String url2 = downloadImage(term2); // Makes the URL for query
        String json = fetchImage(url2); // Fetch JSON data from the url
        WikipediaData wiki =
            GSON_WIKI.<WikipediaData>fromJson(json, WikipediaData.class);
        // ^^^^ Converts the JSON data into WikipediaData using GSON
        return wiki; // returns the response
    }


    /**
       Uses the image name retrieved from Wikipedia to search wikipedia
       with an encoded version to retrieve the actual url of the
       image so it can be shown.

       @param wikiImageFinal is the name of the image.
       @return url3 is the url of the page with the url of the image.
     */
    public static String downloadImagev2(String wikiImageFinal) {
        String encodedWikiImageFinal = URLEncoder.encode(wikiImageFinal, StandardCharsets.UTF_8);
        String url3 = "https://www.mediawiki.org/w/api.php?";
        url3 += "action=query&format=json" + // encodes and puts it into a new url
            "&prop=imageinfo&titles=" + encodedWikiImageFinal +
            "&prop=imageinfo&iilimit=50&iiend=2007-12-31T23:59:59Z&iiprop=timestamp" +
            "&iiprop=user&iiprop=url";

        return url3;
    }

    /**
       This method is for fetching the url of the image and returning
       the data as a json.

       @param wikiImageFinal is the name of the image retrieved from WikipediaAPI
       @return wiki2 is response from Wikipedia.
     */
    public static ImageWikipediaData fetchWikiv2(String wikiImageFinal) {
        String url3 = downloadImagev2(wikiImageFinal); // Makes the URL for query
        String json3 = fetchImagev2(url3); // Fetch JSON data from the url
        ImageWikipediaData wiki2 =
            GSON_WIKI2.<ImageWikipediaData>fromJson(json3, ImageWikipediaData.class);
        // ^^^^ Converts the JSON data into an ImageWikipediaData using GSON
        return wiki2; // returns the response
    }


/**
   Makes the string from the user input into the search as a url.

   @param term Is the term the user inputs into the search bar.
   @return userUrl is the complete url returned from the user inputs.
*/

    public static String getPokemonUrl(String term) {
        term = term.toLowerCase();
        String spacedTerm = term.replace(" ", "-"); // replaces spaces for search
        String userUrl = "https://pokeapi.co/api/v2/pokemon/" + spacedTerm;

        return userUrl;
    }

    /**
      The method that occurs when the user clicks the get image button.
      It calls the many methods which does more of the actions.
     */

    private void pokeButton() {
        pokePicker.setOnAction(event -> {
            try {
                convertList.clear();
                finalImages.clear(); // Clears all the lists
                wikiList.clear();
                userInput = url.getText(); // get the user input
                PokemonDataTypeHigh response = fetchPokemon(userInput);
                if (response == null) { // if the user inputs a pokemon that doesn't exist
                    changeText.setText("There is no Pokemon named: " + userInput);
                } else {
                    List<PokemonTypeData> pokemonTypes = response.getTypes(); // gets the types of
                    // the pokemon
                    if (pokemonTypes == null) { // if the user inputs nothing
                        changeText.setText("You forgot to input a Pokemon name!");
                    } else {
                        for (PokemonTypeData typeData : pokemonTypes) {
                            PokemonType type = typeData.getType();  // gets the types of the pokemon
                            // and adds them to a list
                            convertList.add(type.getName());
                        }
                        wikiType = convertList.get(0); // gets the main type of the pokemon
                        for (int i = 0; i < convertList.size(); i++) {
                            if (i == 1) {
                                printableList = printableList + " and " +  convertList.get(i);
                                tempCount = 2; // if there are one or two types print something else
                            } else {
                                printableList = convertList.get(i);
                                tempCount = 0;
                            }
                        }
                        if (tempCount == 2) { // if there are one or two types print something
                            // different for each scenario
                            changeText.setText("Your Pokemon is " + userInput
                                + " and it's types are " + printableList);
                        } else {
                            changeText.setText("Your Pokemon is " + userInput
                                + " and it's type is " + printableList);
                        }
                    }
                    if (wikiType == null) {
                        wikiType = null; // if the user inputted nothing
                    } else { // otherwise call these helper methods
                        bottomTextChange();
                        bottomTextChange2();
                        wikiTypeChange();
                        fetchImageEnd();
                    }
                }

            } catch (IllegalArgumentException e) { // Sees if the images fail
                changeText.setText("Last attempt to get images failed...");
            }  catch (IOException e) {
                e.printStackTrace(); // Prints the stack trace
            }
        });
    }

    /**
       Fetches the actual and shows the image using many
       different helper methods.
     */
    public void fetchImageEnd() {

        WikipediaData response2 = fetchWiki(wikiType); // calls fetchWiki
        WikipediaQuery imageTest = response2.getQuery(); // gets the query of the response
        for (String key : imageTest.getPages().keySet()) {
            WikipediaPages page = imageTest.getPages().get(key);
            List<WikipediaImages> images = page.getImages(); // creates a list of the images
            for (WikipediaImages image : images) {
                wikiList.add(image.getTitle()); //gets the titles of the images
            }
        }
        wikiImageFinal = wikiList.get(0); // gets the first image on the page
        ImageWikipediaData response3 = fetchWikiv2(wikiImageFinal); // calls fetchwikiv2
        // with the newfound image title
        ImageWikipediaQuery imageTestv2 = response3.getQuery(); // gets query
        for (String key2 : imageTestv2.getPages().keySet()) {
            ImageWikipediaPages page2 = imageTestv2.getPages().get(key2);
            List<ImageWikipediaImages> imagev2 = page2.getImageInfo(); // gets the image info
            for (ImageWikipediaImages imagez : imagev2) {
                finalImages.add(imagez.getUrl()); // adds the urls of the final image
            }
            TilePane finalImageThankJesus = new TilePane();
            finalImageThankJesus.setPrefRows(1); //creates new tile pane for the image
            finalImageThankJesus.setPrefRows(1);
            String finalImagez = finalImages.get(0); //gets the first url
            Image finalImageImage = new Image(finalImagez);
            ImageView imageView = new ImageView(finalImageImage);
            imageView.setFitHeight(320);
            imageView.setFitWidth(375);
            botHBox.getChildren().clear();
            finalImageThankJesus.getChildren().add(imageView); // adds the image to the scene
            botHBox.getChildren().add(finalImageThankJesus);
        }
    }

    /**
       Change the bottom text to show the user a little exercpt about where to find
       their pokemon.
     */

    public void bottomTextChange() {

        if (wikiType.equalsIgnoreCase("flying")) {
            botTextEnd.setText("Since " + userInput + "'s main type is flying, you would probably" +
                " find \nit in the sky with some airplanes!");
        }
        if (wikiType.equalsIgnoreCase("fire")) {
            botTextEnd.setText("Since " + userInput + "'s main type is fire, you would probably" +
                " find \nit at a volcano!");
        }
        if (wikiType.equalsIgnoreCase("bug")) {
            botTextEnd.setText("Since " + userInput + "'s main type is bug, you would probably" +
                " find \nit on some plants!");
        }
        if (wikiType.equalsIgnoreCase("dark")) {
            botTextEnd.setText("Since " + userInput + "'s main type is dark, you would probably" +
                " find \nit outside at night!");
        }
        if (wikiType.equalsIgnoreCase("dragon")) {
            botTextEnd.setText("Since " + userInput + "'s main type is dragon, you would probably" +
                " find \nit on top of a mountain peak!");
        }
        if (wikiType.equalsIgnoreCase("electric")) {
            botTextEnd.setText("Since " + userInput + "'s main type is electric, you would" +
                " probably find \nit at a power plant!");
        }
        if (wikiType.equalsIgnoreCase("fairy")) {
            botTextEnd.setText("Since " + userInput + "'s main type is fairy, you would probably" +
                " find \nit at a princess castle!");
        }
        if (wikiType.equalsIgnoreCase("fighting")) {
            botTextEnd.setText("Since " + userInput + "'s main type is fighting, you would" +
                " probably find \nit in a boxing ring!");
        }
        if (wikiType.equalsIgnoreCase("ghost")) {
            botTextEnd.setText("Since " + userInput + "'s main type is ghost, you would probably" +
                " find \nit in around a cemetery!");
        }
    }

    /**
       The previous method was too long so this covers the rest of the types and
       shows the user a little excerpt about them.
     */

    public void bottomTextChange2() {
        if (wikiType.equalsIgnoreCase("grass")) {
            botTextEnd.setText("Since " + userInput + "'s main type is grass, you would probably" +
                " find \nit in a grassland biome!");
        }
        if (wikiType.equalsIgnoreCase("ground")) {
            botTextEnd.setText("Since " + userInput + "'s main type is ground, you would probably" +
                " find \nit inside a cave!");
        }
        if (wikiType.equalsIgnoreCase("ice")) {
            botTextEnd.setText("Since " + userInput + "'s main type is ice, you would probably" +
                " find \nit in around some glaciers!");
        }
        if (wikiType.equalsIgnoreCase("normal")) {
            botTextEnd.setText("Since " + userInput + "'s main type is normal, you would probably" +
                " find \nit in a city!");
        }
        if (wikiType.equalsIgnoreCase("poison")) {
            botTextEnd.setText("Since " + userInput + "'s main type is poison, you would probably" +
                " find \nit at a landfill!");
        }
        if (wikiType.equalsIgnoreCase("psychic")) {
            botTextEnd.setText("Since " + userInput + "'s main type is psychic, you would" +
                " probably find \nit in a psychologist clinic/hospital!");
        }
        if (wikiType.equalsIgnoreCase("rock")) {
            botTextEnd.setText("Since " + userInput + "'s main type is rock, you would probably" +
                " find \nit near some exposed rocks!");
        }
        if (wikiType.equalsIgnoreCase("steel")) {
            botTextEnd.setText("Since " + userInput + "'s main type is steel, you would probably" +
                " find \nit near a steel mill or furnace!");
        }
        if (wikiType.equalsIgnoreCase("water")) {
            botTextEnd.setText("Since " + userInput + "'s main type is water, you would probably" +
                " find \nit near a large body of water!");
        }

    }

    /**
       Most of the words are far to vague for Wikipedia to understand them so I
       change the words to more specific terms that Wikipedia can actually search
       for.
     */

    public void wikiTypeChange() {
        if (wikiType.equalsIgnoreCase("flying")) {
            wikiType = "altitude";
        }
        if (wikiType.equalsIgnoreCase("fire")) {
            wikiType = "lava";
        }
        if (wikiType.equalsIgnoreCase("bug")) {
            wikiType = "Rhododendron";
        }
        if (wikiType.equalsIgnoreCase("dark")) {
            wikiType = "Paranal_Observatory";
        }
        if (wikiType.equalsIgnoreCase("dragon")) {
            wikiType = "Pyramidal_peak";
        }
        if (wikiType.equalsIgnoreCase("electric")) {
            wikiType = "Bełchatów_Power_Station";
        }
        if (wikiType.equalsIgnoreCase("fairy")) {
            wikiType = "Sleeping_Beauty_Castle";
        }
        if (wikiType.equalsIgnoreCase("fighting")) {
            wikiType = "Amateur_boxing";
        }
        if (wikiType.equalsIgnoreCase("ghost")) {
            wikiType = "Cemetery";
        }
        if (wikiType.equalsIgnoreCase("grass")) {
            wikiType = "Nachusa_Grasslands";
        }
        if (wikiType.equalsIgnoreCase("ground")) {
            wikiType = "Caving";
        }
        if (wikiType.equalsIgnoreCase("ice")) {
            wikiType = "glacier";
        }
        if (wikiType.equalsIgnoreCase("normal")) {
            wikiType = "town_square";
        }
        if (wikiType.equalsIgnoreCase("poison")) {
            wikiType = "apex_landfill";
        }
        if (wikiType.equalsIgnoreCase("psychic")) {
            wikiType = "psychologist";
        }
        if (wikiType.equalsIgnoreCase("rock")) {
            wikiType = "balancing_rock";
        }
        if (wikiType.equalsIgnoreCase("steel")) {
            wikiType = "blast_furnace";
        }
        if (wikiType.equalsIgnoreCase("water")) {
            wikiType = "Sea_of_Åland";
        }
    }
    /**
       A method for fetching the url to return it as a json.

       This method was shown to me by Dr. Coterell

       @param term is the user inputted term to search up
       @return itunes is the return of the response from Json
    */

    public static PokemonDataTypeHigh fetchPokemon(String term)
        throws IOException {
        String url = getPokemonUrl(term); // Makes the URL for query
        String json = fetchString(url); // Fetch JSON data from the url
        PokemonDataTypeHigh pkmn =
            GSON.<PokemonDataTypeHigh>fromJson(json, PokemonDataTypeHigh.class);
        // ^^^^ Converts the JSON data into a PokemonDataTypeHigh using GSON
        return pkmn; // returns the reponse
    }

}
