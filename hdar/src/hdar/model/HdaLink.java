package hdar.model;

public class HdaLink {

	private String linkText;
	private String link;

	public HdaLink() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HdaLink(String linkText, String link) {
		super();
		this.linkText = linkText;
		this.link = link;
	}

	public String getLinkText() {
		return linkText;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "HdaLinks [linkText=" + linkText + ", link=" + link + "]";
	}

}
