package hdar.util.json;

/**
 * Always returns a fixed string.
 *
 * @author tbach
 */
public class JsonProviderFixed implements JsonProvider {
  private final String fixedString;

  public JsonProviderFixed(final String fixedString) {
    this.fixedString = fixedString;
  }

  @Override
  public String getJsonFrom(final String string) {
    return fixedString;
  }
}
