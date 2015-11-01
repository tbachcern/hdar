package hdar.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
  // TODO remove after unit test creation -tbach
  // public static void main(final String[] args) {
  // final String test =
  // "{\"Title\":\"Event Horizon\",\"Year\":\"1997\",\"Rated\":\"R\",\"Released\":\"15 Aug 1997\",\"Runtime\":\"96 min\",\"Genre\":\"Horror, Sci-Fi\",\"Director\":\"Paul W.S. Anderson\",\"Writer\":\"Philip Eisner\",\"Actors\":\"Laurence Fishburne, Sam Neill, Kathleen Quinlan, Joely Richardson\",\"Plot\":\"A rescue crew investigates a spaceship that disappeared into a black hole and has now returned...with someone or something new on-board.\",\"Language\":\"English, Latin\",\"Country\":\"UK, USA\",\"Awards\":\"1 win & 1 nomination.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTYxNzY0MjczNV5BMl5BanBnXkFtZTgwOTIxNzQxMTE@._V1_SX300.jpg\",\"Metascore\":\"35\",\"imdbRating\":\"6.7\",\"imdbVotes\":\"110,247\",\"imdbID\":\"tt0119081\",\"Type\":\"movie\",\"Response\":\"True\"}";
  // System.out.println(getMapKeyValueFromJsonText(test));
  // }

  private static final Type HASH_MAP_TYPE = new TypeToken<HashMap<String, String>>() {/**/
  }.getType();

  /**
   * Converts a JSON to map.<br>
   * E.g. input: "{a:b, c:d}"<br>
   * return: map{a=b, c=d}<br>
   * <br>
   * Expects a string consistent to a map-mapping.
   */
  public static Map<String, String> getMapKeyValueFromJsonText(final String jsonText) {
    return new Gson().fromJson(jsonText, HASH_MAP_TYPE);
  }
}
