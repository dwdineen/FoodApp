package dwd.foodapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InvMenuAdapter;
import dwd.foodapp.adapters.NewFoodCategoryAdapter;
import dwd.foodapp.statics.Constants;

public class NewFoodActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_food);


		ListAdapter AA = new NewFoodCategoryAdapter(this, Constants.INV_CATEGORY_NAMES);

		ListView LV = (ListView) findViewById(R.id.ListView_InvMenu);
		LV.setAdapter(AA);


	}
}
