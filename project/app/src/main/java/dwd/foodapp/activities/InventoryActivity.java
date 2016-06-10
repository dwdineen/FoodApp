package dwd.foodapp.activities;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.net.URL;
import java.net.URLConnection;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InventoryAdapter;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;
import dwd.foodapp.statics.GeneralFunctions;

public class InventoryActivity extends AppCompatActivity {

	Food[] foods;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		Bundle bundle = getIntent().getExtras();
		foods = (Food[]) bundle.get("FoodArr");


		//---------Deal with list view--------------------------------
		ListAdapter AA = new InventoryAdapter(this, foods);

		ListView LV = (ListView) findViewById(R.id.ListView_inventory);
		LV.setAdapter(AA);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onFoodPress(view, position);
			}
		});




	}



	private void onFoodPress(View view, int pos){
		foods[pos].setStock(!foods[pos].getStock());

		if (foods[pos].getStock())
			view.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_IN_STOCK_COLOR));
		else
			view.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_OUT_STOCK_COLOR));


		Integer temp = GeneralFunctions.boolToInt(foods[pos].getStock());
		changeStockInDB cs = new changeStockInDB();
		cs.execute(temp, foods[pos].getId());

	}

	private class changeStockInDB extends AsyncTask<Integer, Void, Void> {


		@Override
		protected Void doInBackground(Integer stock[]) {

			try {

				
				String sql = Constants.PHP_URL + "?fun=upStock&stock=" + stock[0] + "&id=" + stock[1];
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