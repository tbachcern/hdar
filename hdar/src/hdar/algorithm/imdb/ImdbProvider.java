package hdar.algorithm.imdb;

import java.util.List;

import hdar.model.ImdbEntry;

public interface ImdbProvider {
  public static ImdbProvider DEFAULT_PROVIDER = new ImdbProviderOmdb();

  public List<ImdbEntry> searchByName(String name);

  public ImdbEntry searchById(String Id);
}
