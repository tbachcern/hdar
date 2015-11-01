package hdar.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class IoUtil {

  /** Gets the (plain) content from a website */
  public static String getStringFromWebsite(final String website) {
    URL url = null;
    try {
      url = new URL(website);
    }
    catch (final MalformedURLException exception) {
      exception.printStackTrace(); // TODO exception handling -tbach
    }
    if (url == null)
      return "";
    return getStringFromUrl(url);
  }

  /** Gets the (plain) content from an URL */
  public static String getStringFromUrl(final URL url) {
    String result = "";
    try {
      result = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
    }
    catch (final IOException exception) {
      exception.printStackTrace(); // TODO exception handling -tbach
    }
    return result;
  }
}
