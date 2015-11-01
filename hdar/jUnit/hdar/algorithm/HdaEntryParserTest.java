package hdar.algorithm;

import static org.assertj.core.api.Assertions.*;

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

  @Test
  public void getIdFromWebsiteStringTest() {
    final String website = "http://www.hd-area.org/index.php?id=92334";
    final int id = HdaEntryParser.getIdFromWebsiteString(website);
    assertThat(id).isEqualTo(92334);
  }

  @Test
  public void getIdFromWebsiteStringTest2() {
    final String website = "http://www.hd-area.org/index.php?id=9";
    final int id = HdaEntryParser.getIdFromWebsiteString(website);
    assertThat(id).isEqualTo(9);
  }

  @Test(expected = NumberFormatException.class)
  public void getIdFromWebsiteStringTest3() {
    final String website = "http://www.hd-area.org/index.php?id=9a";
    HdaEntryParser.getIdFromWebsiteString(website);
  }
}
