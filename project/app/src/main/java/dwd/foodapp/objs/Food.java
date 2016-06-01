package dwd.foodapp.objs;


public class Food implements java.io.Serializable{

	private String name;
	private boolean stock;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStock(boolean stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public boolean getStock() {
		return stock;
	}


}
