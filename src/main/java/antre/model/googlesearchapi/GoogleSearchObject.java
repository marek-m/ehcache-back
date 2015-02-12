package antre.model.googlesearchapi;

import java.util.List;

public class GoogleSearchObject {

	private List<Item> items;

	
	public GoogleSearchObject() {
		super();
	}

	public GoogleSearchObject(List<Item> items) {
		super();
		this.items = items;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "GoogleSearchObject [items=" + items + "]";
	}
	
	
}
