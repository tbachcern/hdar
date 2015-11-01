package hdar;

import hdar.algorithm.HdaEntryParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main {
  public static void main(final String[] args) {
    final String website = "http://www.hd-area.org/index.php?id=92334";
    // printHTML(website);
    // parseJSoupFull(website);
    parseJSoup(website);
  }

  private static void parseJSoup(final String website) {
    HdaEntryParser.parseHdaWebsiteStringToHdaEntry(website);
  }

  static void parseJSoupFull(final String website) {
    try {
      final Document document = Jsoup.connect(website).get();
      System.out.println(document.title()); // title only
      System.out.println(document.data()); // empty
      System.out.println(document.html()); // all
      System.out.println(document.text()); // text only w/o tags
    }
    catch (final IOException exception) {
      exception.printStackTrace();
    }
  }

  static void printHTML(final String website) {
    URL url;
    try {
      url = new URL(website);
      final URLConnection spoof = url.openConnection();
      spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0;    H010818)");
      final BufferedReader in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
      String strLine = "";
      String finalHTML = "";
      // Loop through every line in the source
      while ((strLine = in.readLine()) != null) {
        System.out.println(strLine);
        finalHTML += strLine;
      }
      System.out.println(finalHTML);
    }
    catch (final IOException exception) {
      exception.printStackTrace();
    }
  }

  static void showSwing() {
    final JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final JEditorPane editorPane = new JEditorPane();

    try {
      editorPane.setPage(new URL("http://google.de"));
    }
    catch (final MalformedURLException exception) {
      exception.printStackTrace();
    }
    catch (final IOException exception) {
      exception.printStackTrace();
    }

    frame.add(new JScrollPane(editorPane));

    frame.setSize(300, 200);
    frame.setVisible(true);
  }
}
