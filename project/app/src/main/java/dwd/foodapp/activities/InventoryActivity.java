package dwd.foodapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.SQLException;

import dwd.foodapp.R;
import dwd.foodapp.adapters.InventoryAdapter_old;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;
import dwd.foodapp.statics.GeneralFunctions;

public class InventoryActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		Food[] foods = GeneralFunctions.makeFoodArray();


		ListAdapter AA = new InventoryAdapter_old(this, foods);

		ListView LV = (ListView) findViewById(R.id.ListView_inventory);
		LV.setAdapter(AA);

	}
}
