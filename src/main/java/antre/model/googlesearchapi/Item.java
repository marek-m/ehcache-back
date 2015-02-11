package antre.model.googlesearchapi;

public class Item {
	private String link;

	public Item() {
		super();
	}

	public Item(String link) {
		super();
		this.link = link;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Item [link=" + link + "]";
	}
	
	
}
