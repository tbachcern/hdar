package hdar.model;

public class HdaCategory {
  private String url;
  private String name;

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public String getName() {
    return name;
  }

  public void setName(final String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    final StringBuilder builder = new StringBuilder();
    builder.append("HdaCategory [url=");
    builder.append(url);
    builder.append(", name=");
    builder.append(name);
    builder.append("]");
    return builder.toString();
  }
}
