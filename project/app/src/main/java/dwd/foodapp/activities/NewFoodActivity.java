package dwd.foodapp.activities;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InvMenuAdapter;
import dwd.foodapp.adapters.NewFoodCategoryAdapter;
import dwd.foodapp.statics.Constants;
import dwd.foodapp.statics.GeneralFunctions;

public class NewFoodActivity extends AppCompatActivity {

	public boolean[] selectedCats = new boolean[Constants.INV_CATEGORY_NAMES.length];
	private boolean stock = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_food);

		for (int i = 0; i < selectedCats.length; i++){
			selectedCats[i] = false;
		}


		ListAdapter AA = new NewFoodCategoryAdapter(this, Constants.INV_CATEGORY_NAMES, selectedCats);
		ListView LV = (ListView) findViewById(R.id.listView_newFood);
		assert LV != null;
		LV.setAdapter(AA);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

				selectedCats[pos] = !selectedCats[pos];


				if (selectedCats[pos])
					view.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_SELECTED));
				else
					view.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_NOT_SELECTED));


			}
		});


		final EditText ET = (EditText) findViewById(R.id.editText_NewFoodName);
		assert ET != null;

		Button submitButton = (Button) findViewById(R.id.btn_NewFoodSubmit);
		assert submitButton != null;
		submitButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (GeneralFunctions.isEmpty(ET))
					Toast.makeText(NewFoodActivity.this, "Enter name of food", Toast.LENGTH_SHORT).show();
				else if (isNoneSelected())
					Toast.makeText(NewFoodActivity.this, "Must select a category", Toast.LENGTH_SHORT).show();
				else{
					(new Submit()).execute(ET.getText().toString());
				}
			}
		});

		final Button stockButton = (Button) findViewById(R.id.btn_newFoodStock);
		assert stockButton != null;
		stockButton.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_OUT_STOCK_COLOR));
		stockButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				stock = !stock;

				if (stock)
					stockButton.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_IN_STOCK_COLOR));
				else
					stockButton.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_OUT_STOCK_COLOR));
			}
		});

	}


	private class Submit extends AsyncTask <String, Void, Void>{

		@Override
		protected Void doInBackground(String... params) {

			try {

				String cats = "";
				for (int i = 0; i < selectedCats.length; i++){
					if (selectedCats[i])
						cats = cats + Constants.INV_CATEGORY_NAMES[i];
				}



				String name = GeneralFunctions.makeStringInternetWorthy(params[0]);
				String sql = Constants.PHP_URL + "?fun=addNewFood&name=" + name + "&stock=" + GeneralFunctions.boolToInt(stock) + "&cats=" + cats;
				URL url = new URL(sql);
				URLConnection urlConnection = url.openConnection();
				urlConnection.getInputStream();


			}catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			onBackPressed();
		}
	}

	private boolean isNoneSelected(){

		for (int i = 0; i < selectedCats.length; i++){
			if (selectedCats[i]) return false;
		}

		return true;
	}
}
