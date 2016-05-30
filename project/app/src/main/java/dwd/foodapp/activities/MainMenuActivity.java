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
		InventoryButton.setOnClickListener(A);


    }


	View.OnClickListener A = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Button btn = (Button) v;
			btn.setText("Test");

			Intent intent = new Intent(MainMenuActivity.this, InvMenuActivity.class);
			startActivity(intent);

		}
	};



}
