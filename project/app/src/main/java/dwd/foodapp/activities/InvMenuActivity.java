package dwd.foodapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import dwd.foodapp.R;
import dwd.foodapp.constants.Constants;

public class InvMenuActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inv_menu);

		makeCategoryButtons();
	}

	private void makeCategoryButtons() {

		ArrayAdapter <String> AA = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
									Constants.INV_CATEGORY_NAMES);

		ListView LV = (ListView) findViewById(R.id.invMenuListView);
		LV.setAdapter(AA);
		LV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

				Intent intent = new Intent(InvMenuActivity.this, InventoryActivity.class);
				intent.putExtra("categoryName", String.valueOf(parent.getItemAtPosition(position)));

				startActivity(intent);
			}
		});

	}



}
