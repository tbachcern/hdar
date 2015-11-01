package hdar.algorithm.imdb;

import java.util.Map;

import hdar.model.ImdbEntry;
import hdar.util.IoUtil;
import hdar.util.JsonUtil;

public class Omdb implements ImdbProvider {
  @Override
  public ImdbEntry searchByName(final String name) {
    // TODO Auto-generated method stub
    return null;
  }

  private static final String ID_REPLACE_STRING = "ID_REPLACE_STRING";
  private static final String API_TEMPLATE_SEARCH_BY_ID = "http://www.omdbapi.com/?i=" + ID_REPLACE_STRING + "&plot=short&r=json";

  public static void main(final String[] args) {
    final ImdbEntry searchById = new Omdb().searchById("tt0119081");
    System.out.println(searchById);
  }

  /**
   * Searches the movie details by id.<br>
   * Imdb id is of the format "tt<number>. Example: "tt0119081"
   */
  @Override
  public ImdbEntry searchById(final String Id) {
    if (!Id.contains("tt"))
      throw new IllegalArgumentException("id does not contain tt: " + Id);
    if (!Character.isDigit(Id.charAt(Id.length() - 1)))
      throw new IllegalArgumentException("last char is not a digit: " + Id);

    final String apiString = getApiStringFromId(Id);
    final String jsonText = IoUtil.getStringFromWebsite(apiString);
    return getImdbEntryFromJsonText(jsonText);
  }

  static String getApiStringFromId(final String Id) {
    // example for event horizon:
    // http://www.omdbapi.com/?i=tt0119081&plot=short&r=json
    // -tbach
    final String apiString = API_TEMPLATE_SEARCH_BY_ID.replace(ID_REPLACE_STRING, Id);
    return apiString;
  }

  static ImdbEntry getImdbEntryFromJsonText(final String jsonText) {
    final Map<String, String> jsonMap = JsonUtil.getMapKeyValueFromJsonText(jsonText);
    /* Example:
     * http://www.omdbapi.com/?i=tt0119081&plot=short&r=json
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
    final ImdbEntry imdbEntry = new ImdbEntry();
    imdbEntry.setTitle(jsonMap.get("Title"));
    imdbEntry.setYear(Integer.parseInt(jsonMap.get("Year")));
    imdbEntry.setRated(jsonMap.get("Rated"));
    imdbEntry.setReleased(jsonMap.get("Released"));
    imdbEntry.setRuntime(Integer.parseInt(jsonMap.get("Runtime").split(" ")[0]));
    imdbEntry.setDirector(jsonMap.get("Director"));
    imdbEntry.setWriter(jsonMap.get("Writer"));
    imdbEntry.setPlot(jsonMap.get("Plot"));
    imdbEntry.setPoster(jsonMap.get("Poster"));
    imdbEntry.setMetaScore(Integer.parseInt(jsonMap.get("Metascore")));
    imdbEntry.setImdbRating(Float.parseFloat(jsonMap.get("imdbRating")));
    imdbEntry.setImdbVotes(Integer.parseInt(jsonMap.get("imdbVotes").replaceAll(",", "")));
    imdbEntry.setImdbId(jsonMap.get("imdbID"));
    imdbEntry.setType(jsonMap.get("Type"));

    final String actors = jsonMap.get("Actors");
    for (final String actor : actors.split(", ")) {
      imdbEntry.addActor(actor);
    }
    final String genres = jsonMap.get("Genre");
    for (final String genre : genres.split(", ")) {
      imdbEntry.addGenre(genre);
    }

    return imdbEntry;
  }
}
