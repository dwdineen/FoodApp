package dwd.foodapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.widget.Button;
import android.widget.ListView;

import dwd.foodapp.R;

public class InventoryActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inventory);

		ListView L = (ListView) findViewById(R.id.invList);
		for (int i = 0; i < 10; i++){

			Button tempButton = new Button(this);
			tempButton.setText("Hello");
			L.addView(tempButton);


		}



	}
}
