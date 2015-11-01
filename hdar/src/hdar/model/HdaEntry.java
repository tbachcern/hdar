package hdar.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HdaEntry {
	private int id;
	private String url;
	private String title;
	private float imdb;
	private String imdbFromHDA;
	private String imdbUrl;
	private Instant lastImdbAccessTime;
	private Instant lastHdaAccessTime;
	private String uploader;
	private LocalDate uploadDate;

	private String content;
	private List<HdaLink> listHdaLink = new ArrayList<>();
	private List<HdaCategory> listHdaCategories = new ArrayList<>();


	public List<HdaLink> getListHdaLinks() {
		return Collections.unmodifiableList(listHdaLink);
	}

	public void setListHdaLinks(List<HdaLink> listHdaLinks) {
		this.listHdaLink = listHdaLinks;
	}
	public void addHdaLink(final HdaLink hdaLink){
		this.listHdaLink.add(hdaLink);
	}

	public List<HdaCategory> getListHdaCategories() {
		return Collections.unmodifiableList(listHdaCategories);
	}

	public void setListHdaCategories(final List<HdaCategory> listHdaCategories) {
		this.listHdaCategories = listHdaCategories;
	}

	public void addHdaCategory(final HdaCategory hdaCategory) {
		this.listHdaCategories.add(hdaCategory);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getImdbFromHDA() {
		return imdbFromHDA;
	}

	public void setImdbFromHDA(final String imdbFromHDA) {
		this.imdbFromHDA = imdbFromHDA;
	}

	public float getImdb() {
		return imdb;
	}

	public void setImdb(final float imdb) {
		this.imdb = imdb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getImdbUrl() {
		return imdbUrl;
	}

	public void setImdbUrl(final String imdbUrl) {
		this.imdbUrl = imdbUrl;
	}

	public Instant getLastImdbAccessTime() {
		return lastImdbAccessTime;
	}

	public void setLastImdbAccessTime(final Instant lastImdbAccessTime) {
		this.lastImdbAccessTime = lastImdbAccessTime;
	}

	public Instant getLastHdaAccessTime() {
		return lastHdaAccessTime;
	}

	public void setLastHdaAccessTime(final Instant lastHdaAccessTime) {
		this.lastHdaAccessTime = lastHdaAccessTime;
	}

	public String getUploader() {
		return uploader;
	}

	public void setUploader(final String uploader) {
		this.uploader = uploader;
	}

	public LocalDate getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(final LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HdaEntry [\nid=");
		builder.append(id);
		builder.append(", \nurl=");
		builder.append(url);
		builder.append(", \ntitle=");
		builder.append(title);
		builder.append(", \nimdb=");
		builder.append(imdb);
		builder.append(", \nimdbFromHDA=");
		builder.append(imdbFromHDA);
		builder.append(", \nimdbUrl=");
		builder.append(imdbUrl);
		builder.append(", \nlastImdbAccessTime=");
		builder.append(lastImdbAccessTime);
		builder.append(", \nlastHdaAccessTime=");
		builder.append(lastHdaAccessTime);
		builder.append(", \nuploader=");
		builder.append(uploader);
		builder.append(", \nuploadDate=");
		builder.append(uploadDate);
		builder.append(", \ncontent=");
		builder.append(content);
		builder.append(", \nlistHdaLink=");
		builder.append(listHdaLink);
		builder.append(", \nlistHdaCategories=");
		builder.append(listHdaCategories);
		builder.append("]");
		return builder.toString();
	}

}
