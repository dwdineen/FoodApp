package dwd.foodapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InventoryAdapter;
import dwd.foodapp.statics.Constants;

public class InventoryActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		ListAdapter AA = new InventoryAdapter(this,
				Constants.TEST_FOOD_NAMES) {
		};

		ListView LV = (ListView) findViewById(R.id.ListView_inventory);
		LV.setAdapter(AA);

	}
}
