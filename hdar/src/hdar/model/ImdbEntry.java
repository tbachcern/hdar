package hdar.model;

import hdar.algorithm.imdb.ImdbProvider;

public class ImdbEntry {
  private String title;
  private String year; // cannot be int, because sometimes we something like have 1997 - 1999 -tbach
  private String rated;
  private String released;
  private String runtime;
  private String genre;
  private String director;
  private String writer;
  private String actors;
  private String plot;
  private String poster;
  private String metascore;
  private String imdbRating;
  private String imdbVotes;
  private String imdbId; // example: "tt0119081" we have to use the whole part because of leading zeros -tbach
  private String type;

  private ImdbProvider imdbProvider; // we this, we can search for more information if any attribute is unknown -tbach

  public String getTitle() {
    return title;
  }

  public void setTitle(final String title) {
    this.title = title;
  }

  public String getYear() {
    return year;
  }

  /** Warning: Can fail if year is something like 1997 - 1999 */
  public int getYearAsInt() {
    return Integer.parseInt(year);
  }

  public void setYear(final String year) {
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

  public String getRuntime() {
    return runtime;
  }

  /** Warning: Assumes a format as 96 min */
  public int getRuntimeInMinutes() {
    if (runtime.contains("min"))
      return Integer.parseInt(runtime.split(" ")[0]);
    throw new IllegalStateException("unknown format: " + runtime);
  }

  public void setRuntime(final String runtime) {
    this.runtime = runtime;
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

  public String getMetascore() {
    return metascore;
  }

  public int getMetascoreAsInt() {
    return Integer.parseInt(metascore);
  }

  public void setMetascore(final String metascore) {
    this.metascore = metascore;
  }

  public String getImdbRating() {
    return imdbRating;
  }

  public float getImdbRatingAsFloat() {
    return Float.parseFloat(imdbRating);
  }

  public void setImdbRating(final String imdbRating) {
    this.imdbRating = imdbRating;
  }

  public String getImdbVotes() {
    return imdbVotes;
  }

  public int getImdbVotesAsInt() {
    return Integer.parseInt(imdbVotes.replaceAll(",", ""));
  }

  public void setImdbVotes(final String imdbVotes) {
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
    if ((imdbId == null) || imdbId.isEmpty())
      throw new IllegalArgumentException("imdbId is not valid: " + imdbId);
    this.imdbId = imdbId;
  }

  public String getGenre() {
    return genre;
  }

  public void setGenre(final String genre) {
    this.genre = genre;
  }

  public String getActors() {
    return actors;
  }

  public void setActors(final String actors) {
    this.actors = actors;
  }

  public void setImdbProvider(final ImdbProvider imdbProvider) {
    this.imdbProvider = imdbProvider;
  }

  /**
   * Returns an updated version of this entry.<br>
   * This can be used to use newer values or to include missing values.<br>
   * <br>
   * Default update provider is {@link ImdbProvider#DEFAULT_PROVIDER}, this can be changed with {@link #setImdbProvider(ImdbProvider)}.<br>
   * <br>
   * Requirements: Valid imdb id must be set.
   *
   */
  public ImdbEntry getUpdatedVersion() {
    if ((imdbId == null) || imdbId.isEmpty())
      throw new IllegalStateException("imdbId is not valid: " + imdbId);
    final ImdbProvider myImdbProvider = imdbProvider;
    if (myImdbProvider == null)
      return ImdbProvider.DEFAULT_PROVIDER.searchById(imdbId);
    final ImdbEntry newImdbEntry = imdbProvider.searchById(imdbId);
    newImdbEntry.setImdbProvider(myImdbProvider);
    return newImdbEntry;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (prime * result) + ((actors == null) ? 0 : actors.hashCode());
    result = (prime * result) + ((director == null) ? 0 : director.hashCode());
    result = (prime * result) + ((genre == null) ? 0 : genre.hashCode());
    result = (prime * result) + ((imdbId == null) ? 0 : imdbId.hashCode());
    result = (prime * result) + ((imdbRating == null) ? 0 : imdbRating.hashCode());
    result = (prime * result) + ((imdbVotes == null) ? 0 : imdbVotes.hashCode());
    result = (prime * result) + ((metascore == null) ? 0 : metascore.hashCode());
    result = (prime * result) + ((plot == null) ? 0 : plot.hashCode());
    result = (prime * result) + ((poster == null) ? 0 : poster.hashCode());
    result = (prime * result) + ((rated == null) ? 0 : rated.hashCode());
    result = (prime * result) + ((released == null) ? 0 : released.hashCode());
    result = (prime * result) + ((runtime == null) ? 0 : runtime.hashCode());
    result = (prime * result) + ((title == null) ? 0 : title.hashCode());
    result = (prime * result) + ((type == null) ? 0 : type.hashCode());
    result = (prime * result) + ((writer == null) ? 0 : writer.hashCode());
    result = (prime * result) + ((year == null) ? 0 : year.hashCode());
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    final ImdbEntry other = (ImdbEntry) obj;
    if (actors == null) {
      if (other.actors != null)
        return false;
    }
    else if (!actors.equals(other.actors))
      return false;
    if (director == null) {
      if (other.director != null)
        return false;
    }
    else if (!director.equals(other.director))
      return false;
    if (genre == null) {
      if (other.genre != null)
        return false;
    }
    else if (!genre.equals(other.genre))
      return false;
    if (imdbId == null) {
      if (other.imdbId != null)
        return false;
    }
    else if (!imdbId.equals(other.imdbId))
      return false;
    if (imdbRating == null) {
      if (other.imdbRating != null)
        return false;
    }
    else if (!imdbRating.equals(other.imdbRating))
      return false;
    if (imdbVotes == null) {
      if (other.imdbVotes != null)
        return false;
    }
    else if (!imdbVotes.equals(other.imdbVotes))
      return false;
    if (metascore == null) {
      if (other.metascore != null)
        return false;
    }
    else if (!metascore.equals(other.metascore))
      return false;
    if (plot == null) {
      if (other.plot != null)
        return false;
    }
    else if (!plot.equals(other.plot))
      return false;
    if (poster == null) {
      if (other.poster != null)
        return false;
    }
    else if (!poster.equals(other.poster))
      return false;
    if (rated == null) {
      if (other.rated != null)
        return false;
    }
    else if (!rated.equals(other.rated))
      return false;
    if (released == null) {
      if (other.released != null)
        return false;
    }
    else if (!released.equals(other.released))
      return false;
    if (runtime == null) {
      if (other.runtime != null)
        return false;
    }
    else if (!runtime.equals(other.runtime))
      return false;
    if (title == null) {
      if (other.title != null)
        return false;
    }
    else if (!title.equals(other.title))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    }
    else if (!type.equals(other.type))
      return false;
    if (writer == null) {
      if (other.writer != null)
        return false;
    }
    else if (!writer.equals(other.writer))
      return false;
    if (year == null) {
      if (other.year != null)
        return false;
    }
    else if (!year.equals(other.year))
      return false;
    return true;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("ImdbEntry [title=");
    builder.append(title);
    builder.append(", year=");
    builder.append(", imdbId=");
    builder.append(imdbId);
    builder.append(year);
    builder.append(", rated=");
    builder.append(rated);
    builder.append(", released=");
    builder.append(released);
    builder.append(", runtime=");
    builder.append(runtime);
    builder.append(", genre=");
    builder.append(genre);
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
    builder.append(", metascore=");
    builder.append(metascore);
    builder.append(", imdbRating=");
    builder.append(imdbRating);
    builder.append(", imdbVotes=");
    builder.append(imdbVotes);
    builder.append(", type=");
    builder.append(type);
    builder.append("]");
    return builder.toString();
  }
}
