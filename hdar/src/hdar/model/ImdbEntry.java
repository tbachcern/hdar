package hdar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImdbEntry {
  private String title;
  private int year;
  private String rated;
  private String released;
  private int runtime;
  private final List<String> genres = new ArrayList<>();
  private String director;
  private String writer;
  private final List<String> actors = new ArrayList<>();
  private String plot;
  private String poster;
  private int metaScore;
  private float imdbRating;
  private int imdbVotes;
  private String imdbId; // example: "tt0119081" we have to use the whole part because of leading zeros -tbach
  private String type;

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public int getYear() {
    return year;
  }

  public void setYear(final int year) {
    this.year = year;
  }

  public String getRated() {
    return rated;
  }

  public void setRated(final String rated) {
    this.rated = rated;
  }

  public String getReleased() {
    return released;
  }

  public void setReleased(final String released) {
    this.released = released;
  }

  public int getRuntime() {
    return runtime;
  }

  public void setRuntime(final int runtime) {
    this.runtime = runtime;
  }

  public List<String> getGenres() {
    return Collections.unmodifiableList(genres);
  }

  public boolean addGenre(final String genre) {
    return genres.add(genre);
  }

  public String getDirector() {
    return director;
  }

  public void setDirector(final String director) {
    this.director = director;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(final String writer) {
    this.writer = writer;
  }

  public String getPlot() {
    return plot;
  }

  public void setPlot(final String plot) {
    this.plot = plot;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(final String poster) {
    this.poster = poster;
  }

  public int getMetaScore() {
    return metaScore;
  }

  public void setMetaScore(final int metaScore) {
    this.metaScore = metaScore;
  }

  public float getImdbRating() {
    return imdbRating;
  }

  public void setImdbRating(final float imdbRating) {
    this.imdbRating = imdbRating;
  }

  public int getImdbVotes() {
    return imdbVotes;
  }

  public void setImdbVotes(final int imdbVotes) {
    this.imdbVotes = imdbVotes;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getImdbId() {
    return imdbId;
  }

  public void setImdbId(final String imdbId) {
    this.imdbId = imdbId;
  }

  public List<String> getActors() {
    return Collections.unmodifiableList(actors);
  }

  public boolean addActor(final String actor) {
    return actors.add(actor);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("ImdbEntry [title=");
    builder.append(title);
    builder.append(", year=");
    builder.append(year);
    builder.append(", rated=");
    builder.append(rated);
    builder.append(", released=");
    builder.append(released);
    builder.append(", runtime=");
    builder.append(runtime);
    builder.append(", genres=");
    builder.append(genres);
    builder.append(", director=");
    builder.append(director);
    builder.append(", writer=");
    builder.append(writer);
    builder.append(", actors=");
    builder.append(actors);
    builder.append(", plot=");
    builder.append(plot);
    builder.append(", poster=");
    builder.append(poster);
    builder.append(", metaScore=");
    builder.append(metaScore);
    builder.append(", imdbRating=");
    builder.append(imdbRating);
    builder.append(", imdbVotes=");
    builder.append(imdbVotes);
    builder.append(", imdbId=");
    builder.append(imdbId);
    builder.append(", type=");
    builder.append(type);
    builder.append("]");
    return builder.toString();
  }
}
