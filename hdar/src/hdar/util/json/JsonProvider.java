package hdar.util.json;

/**
 * Provider interface to disconnect the JSON provider from JSON parsing.
 *
 * @author tbach
 */
public interface JsonProvider {

  public static JsonProvider DEFAULT_PROVIDER = JsonProviderInternet.INSTANCE;

  /** Gets a JSON string from the source string. Implementing classes must interpret the string. */
  public String getJsonFrom(String string);
}
