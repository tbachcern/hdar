package hdar.util.json;

import hdar.util.IoUtil;

/**
 * Connects to a website and downloads the plain json content as string.
 *
 * @author tbach
 */
public enum JsonProviderInternet implements JsonProvider {
  INSTANCE;

  /**
   * Expects a website.
   */
  @Override
  public String getJsonFrom(final String string) {
    return IoUtil.getStringFromWebsite(string);
  }
}
