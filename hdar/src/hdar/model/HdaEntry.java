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
  private List<HdaCategory> listHdaCategories = new ArrayList<>();

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

  public List<HdaCategory> getListHdaCategories() {
    return Collections.unmodifiableList(listHdaCategories);
  }

  public void setListHdaCategories(final List<HdaCategory> listHdaCategories) {
    this.listHdaCategories = listHdaCategories;
  }

  public void addHdaCategory(final HdaCategory hdaCategory) {
    this.listHdaCategories.add(hdaCategory);
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("HdaEntry [id=");
    builder.append(id);
    builder.append("\n, url=");
    builder.append(url);
    builder.append("\n, title=");
    builder.append(title);
    builder.append("\n, imdb=");
    builder.append(imdb);
    builder.append("\n, imdbFromHDA=");
    builder.append(imdbFromHDA);
    builder.append("\n, imdbUrl=");
    builder.append(imdbUrl);
    builder.append("\n, lastImdbAccessTime=");
    builder.append(lastImdbAccessTime);
    builder.append("\n, lastHdaAccessTime=");
    builder.append(lastHdaAccessTime);
    builder.append("\n, uploader=");
    builder.append(uploader);
    builder.append("\n, uploadDate=");
    builder.append(uploadDate);
    builder.append("\n, listHdaCategories=");
    builder.append(listHdaCategories);
    builder.append("]");
    return builder.toString();
  }
}
