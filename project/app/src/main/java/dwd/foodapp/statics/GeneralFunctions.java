package dwd.foodapp.statics;

import android.os.AsyncTask;

import java.io.BufferedInputStream;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


import dwd.foodapp.objs.Food;

public class GeneralFunctions {

	public static Food[] makeFoodArray(){

		ArrayList<Food> foodList = new ArrayList<>();

		GetJsonGivenCats GJ = new GetJsonGivenCats();
		GJ.execute();

		return foodList.toArray(new Food[foodList.size()]);

	}

}

