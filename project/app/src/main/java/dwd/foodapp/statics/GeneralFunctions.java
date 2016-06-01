package dwd.foodapp.statics;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


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



				Food temp = new Food();

				temp.setId(id);
				temp.setName(dealWithApo(name));
				temp.setStock(intToBool(stock));

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

	public static String dealWithApo(String s){
		return s.replace("'", "\\'");
	}

}

