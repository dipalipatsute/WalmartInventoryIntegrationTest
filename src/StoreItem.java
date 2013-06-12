

public class StoreItem {

	private int storeId;
	private int itemId;
	private int quantity;
	private int price;

	public StoreItem() {}
	
	public StoreItem(int storeId, int itemId, int quantity, int price) {
		this.storeId = storeId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.price = price;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
}
