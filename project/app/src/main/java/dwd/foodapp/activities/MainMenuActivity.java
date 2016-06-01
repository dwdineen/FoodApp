package dwd.foodapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import dwd.foodapp.R;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


		Button InventoryButton = (Button)findViewById(R.id.btnInventory);
		InventoryButton.setOnClickListener(invPress);

		Button AddNew = (Button)findViewById(R.id.btn_MainMenu_AddNewFood);
		AddNew.setOnClickListener(newPress);

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


}
