package dwd.foodapp.statics;


import android.util.JsonReader;

public class Constants {

	final public static String PHP_URL = "http://192.168.1.106:80";
	final public static String[] INV_CATEGORY_NAMES =
			{"Breakfast", "Lunch", "Dinner", "Snack", "Beverage", "Dessert", "Produce", "Meat", "Canned", "Frozen", "Condiment", "Spice", "Other"};

	final public static String[] INV_CATEGORY_NAMES_WITH_ALL =
			{"All", "Breakfast", "Lunch", "Dinner", "Snack", "Beverage", "Dessert", "Produce", "Meat", "Canned", "Frozen", "Condiment", "Spice", "Other"};


	final public static String INVENTORY_LIST_IN_STOCK_COLOR = "#80b3ffb3";
	final public static String INVENTORY_LIST_OUT_STOCK_COLOR = "#80ff9999";

	final public static String NEW_FOOD_CAT_SELECTED = "#80daffb3";
	final public static String NEW_FOOD_CAT_NOT_SELECTED = "#00000000";
}


