package dwd.foodapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InvMenuAdapter;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;
import dwd.foodapp.statics.GeneralFunctions;

public class InvMenuActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inv_menu);

		makeCategoryButtons();
	}

	private void makeCategoryButtons() {

		ListAdapter AA = new InvMenuAdapter(this, Constants.INV_CATEGORY_NAMES_WITH_ALL);

		ListView LV = (ListView) findViewById(R.id.ListView_InvMenu);
		LV.setAdapter(AA);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				String catName = String.valueOf(parent.getItemAtPosition(position));

				Intent temp = new Intent(InvMenuActivity.this, InventoryActivity.class);
				temp.putExtra("categoryName", catName);

				GetJsonForInv G = new GetJsonForInv(temp);
				G.execute(catName);


			}
		});

	}


	private class GetJsonForInv extends AsyncTask<String, String, String> {

		Intent intent;
		GetJsonForInv(Intent i){
			intent = i;
		}

		@Override
		protected String doInBackground(String... params) {

			String cat = params[0];
			String result = "";

			try {

				URL url;
				if (cat == "All")
					url = new URL(Constants.PHP_URL + "?fun=getAll");
				else
					url = new URL(Constants.PHP_URL + "?fun=getByCat&cat=" + cat);

				URLConnection urlConnection = url.openConnection();
				InputStream in = new BufferedInputStream(urlConnection.getInputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				StringBuffer buffer = new StringBuffer();

				String line;
				while ((line = reader.readLine()) != null){
					buffer.append(line);
				}

				result = buffer.toString();

			} catch (Exception e) {
				e.printStackTrace();
				result = "Error";

			}

			return result;
		}

		@Override
		protected void onPostExecute(String s) {

			if (s.equals("Error")){

				Toast.makeText(InvMenuActivity.this, "Error Connecting", Toast.LENGTH_SHORT).show();

			}else {
				Food[] foods = GeneralFunctions.makeFoodArray(s);

				intent.putExtra("FoodArr", foods);
				startActivity(intent);
			}
		}
	}


}
