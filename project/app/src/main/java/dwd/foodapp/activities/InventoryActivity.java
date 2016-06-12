package dwd.foodapp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	String catName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		//------------Deal With Bundles---------------
		Bundle bundle = getIntent().getExtras();
		foods = (Food[]) bundle.get("FoodArr");
		catName = (String) bundle.get("categoryName");


		//-----------Set Up Toolbar----------------------
		Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
		myToolbar.setTitle(catName);
		setSupportActionBar(myToolbar);

		//---------Deal with list view--------------------------------
		dealWithList();
	}

	private void dealWithList(){
		ListAdapter AA = new InventoryAdapter(this, foods);

		ListView LV = (ListView) findViewById(R.id.ListView_inventory);
		LV.setAdapter(AA);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				onFoodPress(view, position);
			}
		});

		LV.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

				Intent editIntent = new Intent(InventoryActivity.this, EditFoodActivity.class);
				editIntent.putExtra("Food", foods[position]);
				editIntent.putExtra("Cat", catName);
				startActivity(editIntent);

				return true;
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.inventory_menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()){
			case R.id.menuBtn_New_Food:

				Intent newFoodIntent = new Intent(this, NewFoodActivity.class);
				newFoodIntent.putExtra("catName", catName);
				startActivity(newFoodIntent);

				return true;
		}

		return false;
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