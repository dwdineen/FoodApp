package dwd.foodapp.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import dwd.foodapp.R;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;
import dwd.foodapp.statics.GeneralFunctions;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


		Button InventoryButton = (Button)findViewById(R.id.btnInventory);
		InventoryButton.setOnClickListener(invPress);

		Button AddNew = (Button)findViewById(R.id.btn_MainMenu_AddNewFood);
		AddNew.setOnClickListener(newPress);

		Button Shop = (Button) findViewById(R.id.btn_MainMenuShopping);
		Shop.setOnClickListener(shopPress);

    }


	private View.OnClickListener invPress = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MainMenuActivity.this, InvMenuActivity.class);
			startActivity(intent);

		}
	};

	private View.OnClickListener newPress = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MainMenuActivity.this, NewFoodActivity.class);
			startActivity(intent);

		}
	};

	private View.OnClickListener shopPress = new View.OnClickListener() {
		@Override
		public void onClick(View v) {

			Intent intent = new Intent(MainMenuActivity.this, ShoppingActivity.class);
			GetJsonForShopping GJFS = new GetJsonForShopping(intent);
			GJFS.execute();

		}
	};


	private class GetJsonForShopping extends AsyncTask<String, String, String> {

		Intent intent;

		GetJsonForShopping(Intent i){
			intent = i;
		}

		@Override
		protected String doInBackground(String... params) {

			String result = "";

			try {
				URL url = new URL(Constants.PHP_URL +"?fun=getByCart");
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
			}

			return result;
		}

		@Override
		protected void onPostExecute(String s) {

			Food[] foods = GeneralFunctions.makeFoodArray(s);


			intent.putExtra("FoodArr", foods);

			startActivity(intent);

		}
	}

}
