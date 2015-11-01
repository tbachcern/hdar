package hdar.algorithm;

import hdar.model.HdaCategory;
import hdar.model.HdaEntry;
import hdar.model.HdaLink;
import hdar.util.StringUitl;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HdaEntryParser {
	private static final String HDA_WEBSITE_BASE_ID_STRING = "http://www.hd-area.org/index.php?id=";

	/** requires internet connection */
	public static HdaEntry parseHdaWebsiteStringToHdaEntry(final String website) {
		if (!website.contains(HDA_WEBSITE_BASE_ID_STRING))
			throw new IllegalArgumentException(
					"String looks invalid, does not contain " + HDA_WEBSITE_BASE_ID_STRING + " String: " + website);

		final Instant start = Instant.now();
		final HdaEntry hdaEntry = new HdaEntry();
		hdaEntry.setUrl(website);
		hdaEntry.setLastHdaAccessTime(Instant.now());
		hdaEntry.setId(StringUitl.getIdFromWebsiteStringString(website));

		try {
			final Document document = Jsoup.connect(website).get();
			parseJsoupDocument(hdaEntry, document);
		} catch (final IOException exception) {
			exception.printStackTrace();
		}
		System.out.println();
		System.out.println(hdaEntry);
		final Instant end = Instant.now();
		System.out.println(Duration.between(start, end));
		return hdaEntry;
	}

	static void parseJsoupDocument(final HdaEntry hdaEntry, final Document document) {
		final Element divIdContent = document.getElementById("content");
		setTitleInformation(hdaEntry, divIdContent);
		setHdaInformation(hdaEntry, divIdContent);
		setContent(hdaEntry, divIdContent);
		setLinks(hdaEntry, divIdContent);
	}

	private static void setLinks(HdaEntry hdaEntry, Element divIdContent) {
		final Element divClassDownload = divIdContent.getElementsByClass("download").first();
		Elements span = divClassDownload.getElementsByTag("span");
		for (Element divClassSpan : span) {
			final HdaLink hdaLink = new HdaLink();
			Elements spanElements = divClassSpan.getElementsByTag("a");
			if (spanElements.size() <= 0) {
				continue;
			}
			String href = spanElements.attr("href");
			hdaLink.setLink(href);
			hdaLink.setLinkText(spanElements.text());
			hdaEntry.addHdaLink(hdaLink);
		}
	}

	private static final String ANONYM_TO_STRING = "http://anonym.to/?";

	private static void setTitleInformation(final HdaEntry hdaEntry, final Element divContent) {
		final Element divClassBoxrechts = divContent.getElementsByClass("boxrechts").first();
		final Element divClassBoxrechtsTagA = divClassBoxrechts.getElementsByTag("a").first();
		// example: -tbach
		// <a href="http://anonym.to/?http://www.imdb.com/title/tt3205802/"
		// target="_blank" rel="nofollow"
		// title="How.to.Get.Away.with.Murder.S01.GERMAN.720p.HDTV.x264-MDGP">IMDb
		// 8,3/10</a>
		hdaEntry.setImdbFromHDA(divClassBoxrechtsTagA.text());
		hdaEntry.setImdbUrl(divClassBoxrechtsTagA.attr("href").replace(ANONYM_TO_STRING, ""));
		hdaEntry.setTitle(divClassBoxrechtsTagA.attr("title"));
	}

	private static final DateTimeFormatter DATE_TIME_FORMATTER_HDA = DateTimeFormatter.ofPattern("d.M.yy"); // example:
																											// 7.05.15
																											// -tbach

	private static void setHdaInformation(final HdaEntry hdaEntry, final Element divIdContent) {
		final Element divClassTopinfo = divIdContent.getElementsByClass("topinfo").first();
		// example: -tbach
		// <div class="topinfo title">
		// Uploader:
		// <b>HDA</b> • Datum:
		// <b> 7.05.15</b> • Kategorie
		// <a
		// href="http://www.hd-area.org/index.php?s=movies&amp;c=720p">720p</a>
		// &gt;
		// <a
		// href="http://www.hd-area.org/index.php?s=movies&amp;c=Serien">Serien</a>
		// </div>

		final Elements divClassTopinfoTagB = divClassTopinfo.getElementsByTag("b");

		hdaEntry.setUploader(divClassTopinfoTagB.get(0).text());
		final LocalDate uploadDate = LocalDate.parse(divClassTopinfoTagB.get(1).text(), DATE_TIME_FORMATTER_HDA);
		hdaEntry.setUploadDate(uploadDate);
		final Elements divClassTopinfoTagA = divClassTopinfo.getElementsByTag("a");
		for (final Element divClassTopinfoTagAItem : divClassTopinfoTagA) {
			final HdaCategory hdaCategory = new HdaCategory();
			hdaCategory.setUrl(divClassTopinfoTagAItem.attr("href"));
			hdaCategory.setName(divClassTopinfoTagAItem.text());
			hdaEntry.addHdaCategory(hdaCategory);
		}
	}

	private static void setContent(final HdaEntry hdaEntry, final Element divIdContent) {
		final Element divClassDownload = divIdContent.getElementsByClass("download").first();
		hdaEntry.setContent(String.valueOf(divClassDownload));
		// System.out.println("div"+divClassDownload);
	}
}
