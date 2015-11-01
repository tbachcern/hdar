package hdar.algorithm;

import org.junit.Test;

public class HdaEntryParserTest {

  @Test(expected = IllegalArgumentException.class)
  public void parseHdaWebsiteStringToHdaEntryTestWrongWebsite() {
    final String website = "http://www.google.de";
    HdaEntryParser.parseHdaWebsiteStringToHdaEntry(website);
  }

  //
  // @Test(expected = IllegalArgumentException.class)
  // public void parseHdaWebsiteStringToHdaEntryTestWrongWebsite2() {
  // final String website = "http://www.hd-area.org/index.php?id=92334";
  // HdaEntryParser.parseHdaWebsiteStringToHdaEntry(website);
  // }
}
