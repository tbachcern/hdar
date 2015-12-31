package hdar.algorithm.imdb;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import hdar.model.ImdbEntry;
import hdar.util.json.JsonProvider;

/**
 * Uses and handles <a href="http://www.omdbapi.com">http://www.omdbapi.com</a>.
 *
 * @author tbach
 */
public class ImdbProviderOmdb implements ImdbProvider {
  private static final Gson gson = new GsonBuilder().setFieldNamingStrategy(GsonCustomOmdbFieldNamingStrategy.INSTANCE).create();
  @SuppressWarnings("unused")
  private static final Gson gsonWithCustomInteger = new GsonBuilder().registerTypeAdapter(Integer.class, new GsonCustomIntegerDeserializer())
      .setFieldNamingStrategy(GsonCustomOmdbFieldNamingStrategy.INSTANCE).create();

  private final JsonProvider jsonProvider;

  /** Uses the default JsonProvider {@link JsonProvider#DEFAULT_PROVIDER} */
  public ImdbProviderOmdb() {
    jsonProvider = JsonProvider.DEFAULT_PROVIDER;
  }

  public ImdbProviderOmdb(final JsonProvider jsonProvider) {
    this.jsonProvider = jsonProvider;
  }

  // example: http://www.omdbapi.com/?s=star+wars&r=json -tbach
  private static final String NAME_REPLACE_STRING = "NAME_REPLACE_STRING";
  private static final String API_TEMPLATE_SEARCH_BY_NAME = "http://www.omdbapi.com/?s=" + NAME_REPLACE_STRING + "&r=json";

  @Override
  public List<ImdbEntry> searchByName(final String name) {
    final String apiString = getApiStringForName(name);
    final String jsonText = jsonProvider.getJsonFrom(apiString);
    final List<ImdbEntry> result = getImdbSearchResultsFromJsonText(jsonText);
    for (final ImdbEntry imdbEntry : result) {
      imdbEntry.setImdbProvider(this);
    }
    return result;
  }

  static List<ImdbEntry> getImdbSearchResultsFromJsonText(final String jsonText) {
    /*
     * Example: http://www.omdbapi.com/?s=star+wars&r=json
     * {"Search":[
     * {"Title":"Star Wars: Episode IV - A New Hope","Year":"1977","imdbID":"tt0076759","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BMTU4NTczODkwM15BMl5BanBnXkFtZTcwMzEyMTIyMw@@._V1_SX300.jpg"},
     * {"Title":"Star Wars: Episode V - The Empire Strikes Back","Year":"1980","imdbID":"tt0080684","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BMjE2MzQwMTgxN15BMl5BanBnXkFtZTcwMDQzNjk2OQ@@._V1_SX300.jpg"},
     * {"Title":"Star Wars: Episode VI - Return of the Jedi","Year":"1983","imdbID":"tt0086190","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BMTQ0MzI1NjYwOF5BMl5BanBnXkFtZTgwODU3NDU2MTE@._V1._CR93,97,1209,1861_SX89_AL_.jpg_V1_SX300.jpg"},
     * {"Title":"Star Wars: Episode I - The Phantom Menace","Year":"1999","imdbID":"tt0120915","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BMTQ4NjEwNDA2Nl5BMl5BanBnXkFtZTcwNDUyNDQzNw@@._V1_SX300.jpg"},
     * {"Title":"Star Wars: Episode III - Revenge of the Sith","Year":"2005","imdbID":"tt0121766","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BNTc4MTc3NTQ5OF5BMl5BanBnXkFtZTcwOTg0NjI4NA@@._V1_SX300.jpg"},
     * {"Title":"Star Wars: Episode II - Attack of the Clones","Year":"2002","imdbID":"tt0121765","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BMTY5MjI5NTIwNl5BMl5BanBnXkFtZTYwMTM1Njg2._V1_SX300.jpg"},
     * {"Title":"Star Wars: The Clone Wars","Year":"2008","imdbID":"tt1185834","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BMTI1MDIwMTczOV5BMl5BanBnXkFtZTcwNTI4MDE3MQ@@._V1_SX300.jpg"},
     * {"Title":"Star Wars: The Clone Wars","Year":"2008–2015","imdbID":"tt0458290","Type":"series","Poster":"http://ia.media-imdb.com/images/M/MV5BMTM0NjQ2Mjk0OV5BMl5BanBnXkFtZTcwODQ3Njc3Mg@@._V1_SX300.jpg"},
     * {"Title":"Star Wars: Clone Wars","Year":"2003–2005","imdbID":"tt0361243","Type":"series","Poster":"http://ia.media-imdb.com/images/M/MV5BMjE2Mjk5Mzk3M15BMl5BanBnXkFtZTcwMDkzMTIzMQ@@._V1_SX300.jpg"},
     * {"Title":"The Star Wars Holiday Special","Year":"1978","imdbID":"tt0193524","Type":"movie","Poster":"http://ia.media-imdb.com/images/M/MV5BNTU2ODA2MjkwNV5BMl5BanBnXkFtZTcwOTkyMzQ0MQ@@._V1_SX300.jpg"}]}
     *
     * -tbach
     */
    final OmdbSearchWrapper omdbSearchWrapper = gson.fromJson(jsonText, OmdbSearchWrapper.class);
    return omdbSearchWrapper.getSearch();
  }

  static String getApiStringForName(final String name) {
    // example for star wars: http://www.omdbapi.com/?s=star+wars&r=json -tbach
    String nameEncoded = "";
    try {
      nameEncoded = URLEncoder.encode(name, "UTF-8");
    }
    catch (final UnsupportedEncodingException exception) {
      exception.printStackTrace(); // TODO Auto-generated catch block
    }
    if (nameEncoded.isEmpty())
      return "";
    final String apiString = API_TEMPLATE_SEARCH_BY_NAME.replace(NAME_REPLACE_STRING, nameEncoded);
    return apiString;
  }

  public static void main(final String[] args) {
    // final ImdbEntry searchById = new Omdb().searchById("tt0119081");
    // System.out.println(searchById);
    new ImdbProviderOmdb().searchByName("star wars");
  }

  /* ###################### by id part ############################################################################# */

  // example: http://www.omdbapi.com/?i=tt0119081&plot=short&r=json -tbach
  private static final String ID_REPLACE_STRING = "ID_REPLACE_STRING";
  private static final String API_TEMPLATE_SEARCH_BY_ID = "http://www.omdbapi.com/?i=" + ID_REPLACE_STRING + "&plot=short&r=json";

  /**
   * Searches the movie details by id.<br>
   * Imdb id is of the format "tt<number>. Example: "tt0119081"
   */
  @Override
  public ImdbEntry searchById(final String id) {
    if (!id.contains("tt"))
      throw new IllegalArgumentException("id does not contain tt: " + id);
    if (!Character.isDigit(id.charAt(id.length() - 1)))
      throw new IllegalArgumentException("last char is not a digit: " + id);

    final String apiString = getApiStringForId(id);
    final String jsonText = jsonProvider.getJsonFrom(apiString);
    final ImdbEntry result = gson.fromJson(jsonText, ImdbEntry.class);
    result.setImdbProvider(this);
    return result;
  }

  static String getApiStringForId(final String id) {
    // example for event horizon: http://www.omdbapi.com/?i=tt0119081&plot=short&r=json -tbach
    final String apiString = API_TEMPLATE_SEARCH_BY_ID.replace(ID_REPLACE_STRING, id);
    return apiString;
  }

  // ######################## custom internal helper classes

  /**
   * Just a wrapper class for the json search result for name from omdb.
   *
   * @author tbach
   */
  private static class OmdbSearchWrapper {
    private List<ImdbEntry> search;

    public List<ImdbEntry> getSearch() {
      if (search == null)
        return Collections.emptyList();
      return search;
    }
  }

  /**
   * Makes it possible to do changes to the values before the parsing kicks in.
   *
   * @author tbach
   */
  private static class GsonCustomIntegerDeserializer implements JsonDeserializer<Integer> { // if we want to drop the all-string concept -tbach

    @Override
    public Integer deserialize(final JsonElement paramJsonElement, final Type paramType, final JsonDeserializationContext paramJsonDeserializationContext)
        throws JsonParseException {
      final String jsonElemmentAsString = paramJsonElement.getAsString();
      // input: "111,602" -tbach
      final int result = Integer.parseInt(jsonElemmentAsString.replaceAll(",", ""));
      return result;
    }
  }

  /**
   * Omdb is bad in naming. This translates java naming to the specific omdb json naming.
   *
   * @author tbach
   */
  private static enum GsonCustomOmdbFieldNamingStrategy implements FieldNamingStrategy {
    INSTANCE;

    @Override
    public String translateName(final Field paramField) {
      final String fieldName = paramField.getName();
      switch (fieldName) {
      case "imdbVotes":
      case "imdbRating":
        return fieldName;
      case "imdbId":
        return "imdbID";
      default:
        return FieldNamingPolicy.UPPER_CAMEL_CASE.translateName(paramField);
      }
    }
  }
}

/*
 * Examples
 *
 *
 * Example: http://www.omdbapi.com/?i=tt0119081&plot=short&r=json
 *
 *{"Title":"Event Horizon",
 * "Year":"1997",
 * "Rated":"R",
 * "Released":"15 Aug 1997",
 * "Runtime":"96 min",
 * "Genre":"Horror, Sci-Fi",
 * "Director":"Paul W.S. Anderson",
 * "Writer":"Philip Eisner",
 * "Actors":"Laurence Fishburne, Sam Neill, Kathleen Quinlan, Joely Richardson",
 * "Plot":"A rescue crew investigates a spaceship that disappeared into a black hole and has now returned...with someone or something new on-board.",
 * "Language":"English, Latin","Country":"UK, USA",
 * "Awards":"1 win & 1 nomination.",
 * "Poster":"http://ia.media-imdb.com/images/M/MV5BMTYxNzY0MjczNV5BMl5BanBnXkFtZTgwOTIxNzQxMTE@._V1_SX300.jpg",
 * "Metascore":"35",
 * "imdbRating":"6.7",
 * "imdbVotes":"110,247",
 * "imdbID":"tt0119081",
 * "Type":"movie",
 * "Response":"True"}
 *
 * -tbach
 */