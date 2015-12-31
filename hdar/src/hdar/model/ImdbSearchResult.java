package hdar.model;

import hdar.algorithm.imdb.ImdbProvider;

public class ImdbSearchResult {
  private String title;
  private String year;
  private String imdbID;
  private String type;
  private String poster;

  private ImdbProvider imdbProvider;

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getYear() {
    return year;
  }

  public void setYear(final String year) {
    this.year = year;
  }

  public String getImdbID() {
    return imdbID;
  }

  public void setImdbID(final String imdbID) {
    this.imdbID = imdbID;
  }

  public String getType() {
    return type;
  }

  public void setType(final String type) {
    this.type = type;
  }

  public String getPoster() {
    return poster;
  }

  public void setPoster(final String poster) {
    this.poster = poster;
  }

  public ImdbProvider getImdbProvider() {
    return imdbProvider;
  }

  public void setImdbProvider(final ImdbProvider imdbProvider) {
    this.imdbProvider = imdbProvider;
  }

}
