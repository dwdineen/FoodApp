package dwd.foodapp.objs;


import java.util.ArrayList;

public class Food implements java.io.Serializable{

	private String name;
	private boolean stock;
	private int id;
	private boolean inCart;
	private ArrayList <String> categories;

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

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

	public boolean isInCart() {
		return inCart;
	}

	public void setInCart(boolean inCart) {
		this.inCart = inCart;
	}
}
