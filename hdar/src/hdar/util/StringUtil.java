package hdar.util;

public class StringUtil {

  private static final String ID_KEYWORD = "id=";

  /**
   * Returns the number in the string after {@value #ID_KEYWORD}.<br>
   * e.g. input "http://www.example.com/whatever.php?id=1234<br>
   * return: 1234<br>
   * <br>
   * Expectations: The relevant part is a number. Invalid: "xyz-id=123a"
   */
  public static int getIdFromWebsiteStringString(final String website) {
    final int indexOfIdKeyword = website.indexOf(ID_KEYWORD);
    return Integer.parseInt(website.substring(indexOfIdKeyword + ID_KEYWORD.length()));
  }
}
