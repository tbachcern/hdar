package hdar.algorithm.imdb;

import hdar.model.ImdbEntry;

public interface ImdbProvider {
  public static ImdbProvider DEFAULT_PROVIDER = new Omdb();

  public ImdbEntry searchByName(String name);

  public ImdbEntry searchById(String Id);
}
