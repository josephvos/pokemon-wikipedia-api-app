
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
import java.time.LocalTime;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ComboBox;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import cs1302.api.ApiDriver;
import cs1302.api.ItunesResponse;
import cs1302.api.ItunesResult;
import java.util.List;
import java.util.ArrayList;
import javafx.scene.layout.TilePane;
import java.util.Random;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.Animation;
import javafx.util.Duration;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import cs1302.api.PokemonTypeData;
import cs1302.api.PokemonType;
import cs1302.api.PokemonDataTypeHigh;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {

    //  @Autowired
    // private PokeApiClient pokeApiClient;
    Stage stage;
    Scene scene;
    HBox root;
    List<PokemonType> types;

    private static final String DEFAULT_IMG = "https://cdn.vox-cdn.com/thumbor/Ufs9bpCoZtCKAlBcaqyxfpaSllc=/0x0:1920x1080/1320x528/filters:focal(807x387:1113x693):format(webp)/cdn.vox-cdn.com/uploads/chorus_image/image/53262569/who_pokemon.0.jpg";
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

    // Tons of variables
    //  private static Stage stage;
    private Timeline timeline;
    //  private Scene scene;
    //  private HBox root;
    HBox urlLayer;
    TextField url;
    static Button getImageButton;
    static Button playButton;
    HBox botHBox;
    HBox superBot;
    HBox textBot;
    ImageView imgView;
    VBox bigBox;
    Text upperText = new Text("Search:");
    ProgressBar loading;
    static Text changeText = new Text();
    Text bottomText = new Text("Images provided by iTunes Search API.");
    ComboBox<String> dropDown = new ComboBox<>();
    TilePane manyImages;
    HBox bot2Hbox;
    Image brownPic;
    int artworkLength2 = 0;
    Random random;
    int artworkLength = 0;
    boolean computerUsage = false;
    private TilePane newManyImages;
    List<String> artworkUrls2 = new ArrayList<String>();
    List<Image> imageUrls2 = new ArrayList<Image>();
    List<String> artworkUrls = new ArrayList<String>();
    List<Image> imageUrls = new ArrayList<Image>();
    static String dropDownThing;
    static String userInput;
    // ItunesResponse response;
    int artworkLengthTemp = -1;
    String imageUrl;
    private double progress = 0.0;
    static String body;
    static int repeated = 0;
    boolean timeLineYes = false;
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
        //      root = new VBox();
        //  } // ApiApp

        this.stage = null;
        this.scene = null; // Initializes a bunch of stuff.
        this.root = new HBox(15);
        loading = new ProgressBar(0); // Not really hard to understand what these all do.
        bigBox = new VBox();
        superBot = new HBox();
        imgView = new ImageView();
        textBot = new HBox();
        url = new TextField("Tornadus-therian");

        botHBox = new HBox();
        loading.setPrefWidth(235);
        bot2Hbox = new HBox(3);
        dropDown.getItems().addAll(
            "movie", "podcast", "music", "musicVideo", "audiobook",
            "shortFilm", "tvShow", "software", "ebook", "all");
        dropDown.setValue("music");
        manyImages = new TilePane();
        getImageButton = new Button("Pokemon Pick");
        playButton = new Button("Play");
        urlLayer = new HBox();
        newManyImages = new TilePane();
        newManyImages.setPrefRows(1);
        newManyImages.setPrefColumns(2);
        playButton.setDisable(true);
        botTextEnd.setText("Waiting for input...\n");
        changeText.setText("Type In The Name Of A Pokemon" +
            " And I'll Tell You Where To Find It!");
        changeText.setFont(Font.font("TimesRoman", 10));
        bottomChangeText.setText("Created By Joey Vos Using PokeAPI & WikipediaAPI");
        bottomChangeText.setFont(Font.font("TimesRoman", 10));
        botTextEnd.setFont(Font.font("TimesRoman", 10));

    } // GalleryApp
    /** {@inheritDoc}
     *
     *  Creates the boxes and initializes some blank spots.
        Also has the calls for the button methods.
     */

    @Override
    public void init() {
        System.out.println("init() called");
        for (int i = 0; i < 1; i++) {
            brownPic = new Image(DEFAULT_IMG);
            // Sets the images to be default
            // images at first
            ImageView imageView = new ImageView(brownPic);
            imageView.setFitWidth(175); // adds the sizes
            imageView.setFitHeight(320);
            manyImages.getChildren().add(imageView);
        }
        // A bunch of things that add all of the hboxs to vboxes to make the pop up look right
        root.getChildren().addAll(upperText, urlLayer, getImageButton);
        botHBox.getChildren().addAll(manyImages);
        textBot.getChildren().addAll(botTextEnd);
        superBot.getChildren().addAll(bottomChangeText);
        //  bot2Hbox.getChildren().addAll(loading);
        urlLayer.getChildren().addAll(url);
        bigBox.getChildren().addAll(root, changeText, botHBox, textBot, superBot);
        // the methods for the buttons
        getImageButtonAction();
        //   getPlayPauseAction();
    } // init




   /** {@inheritDoc}

 Starts the JavaFX and sets the stage.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage; // sets the stage along with sizing
        this.scene = new Scene(bigBox, 360, 392); // this code was provided to us
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

       Sees if the data that is attempted to be received from iTunes
       is valid and then returns a string representing that if it's
       valid.

       Dr. Coterell showed us the majority of this code in class.

       @param url is the url that the user tries to retrieve from iTunes.
       @return the body received from iTunes.
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
                //  return response.body();
                body = response.body().strip();

            } else {
                //  System.out.println("There is no Pokemon named: " + url);
                return null;
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
        return body;
    }

    public static String fetchImage(String url2) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url2))
                    .build();

            HttpResponse<String> response
                = HTTP_WIKI.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                //  return response.body();
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

     public static String fetchImagev2(String url3) {
        try {

            HttpRequest request3 = HttpRequest.newBuilder()

                .uri(URI.create(url3))
                    .build();

            HttpResponse<String> response3
                = HTTP_WIKI2.send(request3, HttpResponse.BodyHandlers.ofString());

            if (response3.statusCode() == 200) {
                //  return response.body();
                body = response3.body().strip();

            } else {
                //  System.out.println("There is no Pokemon named: " + url);

                return null;
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("fail");
            e.printStackTrace();
            return null;
        }
        return body;
    }

    public String downloadImage(String term2) {
        String url2 = "https://en.wikipedia.org/w/api.php?action=query&prop=images" +
            "&titles=" + wikiType + "&format=json";
        return url2;

    }
    public WikipediaData fetchWiki(String term2) {
        String url2 = downloadImage(term2); // Makes the URL for query
        String json = fetchImage(url2); // Fetch JSON data from the url
        WikipediaData wiki =
            GSON_WIKI.<WikipediaData>fromJson(json, WikipediaData.class);
        // ^^^^ Converts the JSON data into an ItunesReponse using GSON
        return wiki; // returns the reponse
    } // ItunesResponse



    public static String downloadImagev2(String wikiImageFinal) {
        String encodedWikiImageFinal = URLEncoder.encode(wikiImageFinal, StandardCharsets.UTF_8);
        String url3 = "https://www.mediawiki.org/w/api.php?";
        url3 += "action=query&format=json" +
            "&prop=imageinfo&titles=" + encodedWikiImageFinal +
            "&prop=imageinfo&iilimit=50&iiend=2007-12-31T23:59:59Z&iiprop=timestamp" +
            "&iiprop=user&iiprop=url";

        return url3;


    } // getItunesUrl


    public static ImageWikipediaData fetchWikiv2(String wikiImageFinal) {
        String url3 = downloadImagev2(wikiImageFinal); // Makes the URL for query
        String json3 = fetchImagev2(url3); // Fetch JSON data from the url
        ImageWikipediaData wiki2 =
            GSON_WIKI2.<ImageWikipediaData>fromJson(json3, ImageWikipediaData.class);
        // ^^^^ Converts the JSON data into an ItunesReponse using GSON
        return wiki2; // returns the reponse
    } // ItunesResponse


/**
   Makes the string from various inputs to be input into the search as a url.

   @param term Is the term the user inputs into the search bar.
   @return userUrl is the complete url returned from the user inputs.
*/

    public static String getPokemonUrl(String term) {
        term = term.toLowerCase();
        String spacedTerm = term.replace(" ", "-");
        String userUrl = "https://pokeapi.co/api/v2/pokemon/" + spacedTerm;
        return userUrl;
    } // getItunesUrl

    /**
      Method for producing and showing alerts when an Alert
      needs to be shown.

      This is mostly taken from hw 7. I edited it a bit though
      because I couldn't figure out how to get it to work
      right.

      @param title is the title of the Alert
      @param message is the message displayed for the Alert

     */

    public static void showAlert(String title, String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(AlertType.WARNING); // Creates new aler
            alert.setTitle("ERROR"); // Sets the title
            alert.setHeaderText("ERROR"); // Sets the header
            alert.setContentText(message); // Gets the message I manually input
            alert.setResizable(true); // Sets the resizable to true
            alert.showAndWait(); // Shows and waits for the user to click
        });
    } // showAlert

    /**
      The method that occurs when the user clicks the get image button.
      It calls the listMaker method which does more of the actions.
     */

    private void getImageButtonAction() {
        getImageButton.setOnAction(event -> {
            try {
                convertList.clear();
                finalImages.clear();
                wikiList.clear();
                newManyImages.getChildren().clear(); // Clear new many images
                userInput = url.getText(); // get the user input
                dropDownThing = dropDown.getValue(); // get the combo box data
                PokemonDataTypeHigh response = fetchPokemon(userInput);
                if (response == null) {
                    changeText.setText("There is no Pokemon named: " + userInput);
                } else {
                    List<PokemonTypeData> pokemonTypes = response.getTypes();
                    if (pokemonTypes == null) {
                        changeText.setText("You forgot to input a Pokemon name!");
                    } else {
                        for (PokemonTypeData typeData : pokemonTypes) {
                            PokemonType type = typeData.getType();
                            convertList.add(type.getName());
                        }
                        wikiType = convertList.get(0);
                        for (int i = 0; i < convertList.size(); i++) {
                            if (i == 1) {
                                printableList = printableList + " and " +  convertList.get(i);
                                tempCount = 2;
                            } else {
                                printableList = convertList.get(i);
                                tempCount = 0;
                            }
                        }
                        if (tempCount == 2) {
                            changeText.setText("Your Pokemon is " + userInput
                                + " and it's types are " + printableList);
                        } else {
                            changeText.setText("Your Pokemon is " + userInput
                                + " and it's type is " + printableList);
                        }
                    }
                    bottomTextChange();
                    bottomTextChange2();
                    wikiTypeChange();
                    fetchImageEnd();
                }

            } catch (IllegalArgumentException e) { // Sees if the images fail
                changeText.setText("Last attempt to get images failed...");
            }  catch (IOException e) {
                e.printStackTrace(); // Prints the stack trace
            }
        });
    } // getImageButtonAction

    public void fetchImageEnd() {
        WikipediaData response2 = fetchWiki(wikiType);
        WikipediaQuery imageTest = response2.getQuery();
        for (String key : imageTest.getPages().keySet()) {
            WikipediaPages page = imageTest.getPages().get(key);
            List<WikipediaImages> images = page.getImages();
            for (WikipediaImages image : images) {
                wikiList.add(image.getTitle());
            }
        }
        wikiImageFinal = wikiList.get(0);
        ImageWikipediaData response3 = fetchWikiv2(wikiImageFinal);
        ImageWikipediaQuery imageTestv2 = response3.getQuery();
        for (String key2 : imageTestv2.getPages().keySet()) {
            ImageWikipediaPages page2 = imageTestv2.getPages().get(key2);
            List<ImageWikipediaImages> imagev2 = page2.getImageInfo();

            for (ImageWikipediaImages imagez : imagev2) {
                finalImages.add(imagez.getUrl());
            }
            TilePane finalImageThankJesus = new TilePane();
            finalImageThankJesus.setPrefRows(1);
            finalImageThankJesus.setPrefRows(1);
            String finalImagez = finalImages.get(0);
            Image finalImageImage = new Image(finalImagez);
            ImageView imageView = new ImageView(finalImageImage);
            imageView.setFitHeight(320);
            imageView.setFitWidth(375);
            botHBox.getChildren().clear();
            finalImageThankJesus.getChildren().add(imageView);
            botHBox.getChildren().add(finalImageThankJesus);
        }
    }

    public void bottomTextChange() {
        if (wikiType.equalsIgnoreCase("flying")) {
            botTextEnd.setText("Since " + userInput + "'s main type is flying, you would probably" +
                " find \nit in the sky with some airplanes!");
        }
        if (wikiType.equalsIgnoreCase("fire")) {
            botTextEnd.setText("Since " + userInput + "'s main type is fire, you would probably" +
                " find \nit at/near a volcano!");
        }
        if (wikiType.equalsIgnoreCase("bug")) {
            botTextEnd.setText("Since " + userInput + "'s main type is bug, you would probably" +
                " find \nit on/inside some plants!");
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
                " find \nit in on/around some glaciers!");
        }
        if (wikiType.equalsIgnoreCase("normal")) {
            botTextEnd.setText("Since " + userInput + "'s main type is normal, you would probably" +
                " find \nit in a city!");
        }
        if (wikiType.equalsIgnoreCase("poison")) {
            botTextEnd.setText("Since " + userInput + "'s main type is poison, you would probably" +
                " find \nit at/around a landfill!");
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

        //  System.out.println(json);
        PokemonDataTypeHigh pkmn =
            GSON.<PokemonDataTypeHigh>fromJson(json, PokemonDataTypeHigh.class);
        // ^^^^ Converts the JSON data into an ItunesReponse using GSON
        return pkmn; // returns the reponse
    } // ItunesResponse




} // GalleryApp
