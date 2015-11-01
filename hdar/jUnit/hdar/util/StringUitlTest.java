package hdar.util;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;

public class StringUitlTest {
  @Test
  public void getIdFromWebsiteStringTest() {
    final String website = "http://www.hd-area.org/index.php?id=92334";
    final int id = StringUitl.getIdFromWebsiteStringString(website);
    assertThat(id).isEqualTo(92334);
  }

  @Test
  public void getIdFromWebsiteStringTest2() {
    final String website = "http://www.hd-area.org/index.php?id=9";
    final int id = StringUitl.getIdFromWebsiteStringString(website);
    assertThat(id).isEqualTo(9);
  }

  @Test(expected = NumberFormatException.class)
  public void getIdFromWebsiteStringTest3() {
    final String website = "http://www.hd-area.org/index.php?id=9a";
    StringUitl.getIdFromWebsiteStringString(website);
  }
}
