

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
	
	private int id;
	private String name;
	private List<StoreItem> storeItems;
	
	public Item(){}
	public Item(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<StoreItem> getStoreItems() {
		return storeItems;
	}
	public void setStoreItems(List<StoreItem> storeItems) {
		this.storeItems = storeItems;
	}
	
	
}
