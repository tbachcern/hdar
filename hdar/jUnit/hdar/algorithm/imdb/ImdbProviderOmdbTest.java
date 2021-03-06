package hdar.algorithm.imdb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import hdar.model.ImdbEntry;
import hdar.util.MathUtil;
import hdar.util.json.JsonProvider;
import hdar.util.json.JsonProviderFixed;

import org.junit.Test;

public class ImdbProviderOmdbTest {

  private static final String JSON_EXAMPLE_TEXT = "{\"Title\":\"Event Horizon\",\"Year\":\"1997\",\"Rated\":\"R\",\"Released\":\"15 Aug 1997\",\"Runtime\":\"96 min\",\"Genre\":\"Horror, Sci-Fi\",\"Director\":\"Paul W.S. Anderson\",\"Writer\":\"Philip Eisner\",\"Actors\":\"Laurence Fishburne, Sam Neill, Kathleen Quinlan, Joely Richardson\",\"Plot\":\"A rescue crew investigates a spaceship that disappeared into a black hole and has now returned...with someone or something new on-board.\",\"Language\":\"English, Latin\",\"Country\":\"UK, USA\",\"Awards\":\"1 win & 1 nomination.\",\"Poster\":\"http://ia.media-imdb.com/images/M/MV5BMTYxNzY0MjczNV5BMl5BanBnXkFtZTgwOTIxNzQxMTE@._V1_SX300.jpg\",\"Metascore\":\"35\",\"imdbRating\":\"6.7\",\"imdbVotes\":\"110,247\",\"imdbID\":\"tt0119081\",\"Type\":\"movie\",\"Response\":\"True\"}";
  private static final JsonProvider jsonProviderFixed = new JsonProviderFixed(JSON_EXAMPLE_TEXT);

  @Test
  public void constructorTest() {
    assertThat(new ImdbProviderOmdb()).isNotNull();
  }

  @Test
  public void getByIdTest() {
    final ImdbProviderOmdb omdb = new ImdbProviderOmdb(jsonProviderFixed);
    final ImdbEntry imdbEntry = omdb.searchById("tt0119081");
    assertThat(imdbEntry.getTitle()).isEqualTo("Event Horizon");
    assertThat(imdbEntry.getYear()).isEqualTo("1997");
    assertThat(imdbEntry.getYearAsInt()).isEqualTo(1997);
    assertThat(imdbEntry.getRated()).isEqualTo("R");
    assertThat(imdbEntry.getReleased()).isEqualTo("15 Aug 1997");
    assertThat(imdbEntry.getRuntime()).isEqualTo("96 min");
    assertThat(imdbEntry.getRuntimeInMinutes()).isEqualTo(96);
    assertThat(imdbEntry.getGenre()).contains("Horror").contains("Sci-Fi");
    assertThat(imdbEntry.getDirector()).isEqualTo("Paul W.S. Anderson");
    assertThat(imdbEntry.getWriter()).isEqualTo("Philip Eisner");
    assertThat(imdbEntry.getActors()).contains("Sam Neill").contains("Joely Richardson").contains("Laurence Fishburne").contains("Kathleen Quinlan");
    assertThat(imdbEntry.getPlot()).isEqualTo(
        "A rescue crew investigates a spaceship that disappeared into a black hole and has now returned...with someone or something new on-board.");
    assertThat(imdbEntry.getPoster()).isEqualTo("http://ia.media-imdb.com/images/M/MV5BMTYxNzY0MjczNV5BMl5BanBnXkFtZTgwOTIxNzQxMTE@._V1_SX300.jpg");
    assertThat(imdbEntry.getMetascore()).isEqualTo("35");
    assertThat(imdbEntry.getMetascoreAsInt()).isEqualTo(35);
    assertThat(imdbEntry.getImdbRating()).isEqualTo("6.7");
    assertThat(imdbEntry.getImdbRatingAsFloat()).isEqualTo(6.7f, within(MathUtil.EPSILON_FLOAT));
    assertThat(imdbEntry.getImdbVotes()).isEqualTo("110,247");
    assertThat(imdbEntry.getImdbVotesAsInt()).isEqualTo(110247);
    assertThat(imdbEntry.getImdbId()).isEqualTo("tt0119081");
    assertThat(imdbEntry.getType()).isEqualTo("movie");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getByIdTestFailId() {
    final ImdbProviderOmdb omdb = new ImdbProviderOmdb(jsonProviderFixed);
    omdb.searchById("t0119081");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getByIdTestFailId2() {
    final ImdbProviderOmdb omdb = new ImdbProviderOmdb(jsonProviderFixed);
    omdb.searchById("0119081");
  }

  @Test(expected = IllegalArgumentException.class)
  public void getByIdTestFailId3() {
    final ImdbProviderOmdb omdb = new ImdbProviderOmdb(jsonProviderFixed);
    omdb.searchById("tt0119081t");
  }

  @Test
  public void getApiStringFromId() {
    final String id = "tt0119081";
    final String expected = "http://www.omdbapi.com/?i=tt0119081&plot=short&r=json";
    final String result = ImdbProviderOmdb.getApiStringForId(id);
    assertThat(result).isEqualTo(expected);
  }
}
