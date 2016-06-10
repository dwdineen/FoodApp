package dwd.foodapp.activities;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InventoryAdapter;
import dwd.foodapp.adapters.ShoppingAdapter;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;

public class ShoppingActivity extends AppCompatActivity {

	ArrayList <Food> foods;
	ArrayList <Boolean> foodsSelected = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping);

		Bundle bundle = getIntent().getExtras();
		foods = new ArrayList<>(Arrays.asList(((Food[]) bundle.get("FoodArr"))));

		for (int i = 0; i < foods.size(); i++){
			foodsSelected.add(false);
		}

		System.out.println(foodsSelected.size());


		//---------Deal with list view--------------------------------
		final ShoppingAdapter AA = new ShoppingAdapter(this, foods, foodsSelected);

		final ListView LV = (ListView) findViewById(R.id.ListView_Shopping);
		LV.setAdapter(AA);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

				foodsSelected.set(pos, !foodsSelected.get(pos));

				if (foodsSelected.get(pos))
					view.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_SELECTED));
				else
					view.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_NOT_SELECTED));

			}
		});


		//-----------------------Deal With "Made Purchase" Button-------------------
		Button btnMadePurchase = (Button) findViewById(R.id.btn_shoppingHasPurchased);
		btnMadePurchase.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_IN_STOCK_COLOR));
		btnMadePurchase.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				ArrayList <Food> tempFoods= new ArrayList<>();

				for (int i = 0; i < foods.size(); i++){
					if (foodsSelected.get(i) == false){

						tempFoods.add(foods.get(i));

					}else{

						FromCartToShelf x = new FromCartToShelf();
						x.execute(foods.get(i));

					}
				}

				foods = tempFoods;
				foodsSelected = new ArrayList<>();
				for (int i = 0; i < foods.size(); i++){
					foodsSelected.add(false);
				}

				ShoppingAdapter AB = new ShoppingAdapter(ShoppingActivity.this, foods, foodsSelected);
				LV.setAdapter(AB);

			}
		});
	}

	private class FromCartToShelf extends AsyncTask<Food, Void, Void> {


		@Override
		protected Void doInBackground(Food foodItem[]) {

			try {


				String sql = Constants.PHP_URL + "?fun=cartToShelf&id=" + foodItem[0].getId();
				URL url = new URL(sql);
				URLConnection urlConnection = url.openConnection();
				urlConnection.getInputStream();


			}catch (Exception e){
				e.printStackTrace();
			}

			return null;
		}
	}

}
