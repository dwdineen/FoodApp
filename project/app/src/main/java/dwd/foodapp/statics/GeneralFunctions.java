package dwd.foodapp.statics;

import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


import dwd.foodapp.objs.Food;

public class GeneralFunctions {

	public static Food[] makeFoodArray(String s){

		ArrayList <Food> foods = new ArrayList<>();

		try {

			JSONObject J = new JSONObject(s);
			JSONArray jsonFoods = J.getJSONArray("Foods");

			for (int i = 0; i < jsonFoods.length(); i++){
				String name = jsonFoods.getJSONObject(i).getString("Name");
				int stock = jsonFoods.getJSONObject(i).getInt("Stock");
				int id = jsonFoods.getJSONObject(i).getInt("FoodNum");
				int cart = jsonFoods.getJSONObject(i).getInt("InCart");

				//----Deal with category string----
				String cats = jsonFoods.getJSONObject(i).getString("Categories");
				cats = cats.trim();
				String[] catsArr = cats.split(" ");
				ArrayList <String> catsAL = new ArrayList<String>(Arrays.asList(catsArr));


				Food temp = new Food();

				temp.setId(id);
				temp.setName(dealWithApostrophe(name));
				temp.setStock(intToBool(stock));
				temp.setInCart(intToBool(cart));
				temp.setCategories(catsAL);

				foods.add(temp);

			}



		}catch(Exception e){
			e.printStackTrace();
		}

		return foods.toArray(new Food[foods.size()]);
	}

	public static boolean intToBool(int x){
		if (x == 0)
			return false;
		else
			return true;
	}

	public static int boolToInt(boolean x){
		if (x)
			return 1;
		else
			return 0;
	}

	public static String dealWithApostrophe(String s){
		return s.replace("'", "\\'");
	}

	public static boolean isEmpty(EditText etText) {
		if (etText.getText().toString().trim().length() > 0)
			return false;

		return true;
	}

	public static String makeStringInternetWorthy(String s){
		s = s.trim();
		s = s.replace(" ","%20");
		return s;

	}
}

