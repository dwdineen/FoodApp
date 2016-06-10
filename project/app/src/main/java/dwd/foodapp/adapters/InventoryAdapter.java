package dwd.foodapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.net.URL;
import java.net.URLConnection;


import dwd.foodapp.R;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;
import dwd.foodapp.statics.GeneralFunctions;

public class InventoryAdapter extends ArrayAdapter{

	Food[] FoodsCopy;

	public InventoryAdapter(Context context, Food[] foods) {
		super(context, R.layout.layout_inventory_row, foods);
		FoodsCopy = foods;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater theInflater = LayoutInflater.from(getContext());

		View theView = theInflater.inflate(R.layout.layout_inventory_row, parent, false);

		Food thisFood = (Food) getItem(position);

		String S = thisFood.getName();
		boolean stock = thisFood.getStock();


		TextView tv = (TextView) theView.findViewById(R.id.TextView_InventoryListLayout);
		tv.setText(S);

		if (stock) {
			theView.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_IN_STOCK_COLOR));
		}else{
			theView.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_OUT_STOCK_COLOR));
		}



		//----------------------Cart Button------------------------------------------
		ImageButton cartBtn = (ImageButton) theView.findViewById(R.id.btnReorder_Inventory_Layout);
		if (FoodsCopy[position].isInCart())
			cartBtn.setBackgroundColor(Color.parseColor("#80445566"));
		else
			cartBtn.setBackgroundColor(Color.TRANSPARENT);
		cartBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				FoodsCopy[position].setInCart(!FoodsCopy[position].isInCart());


				if (FoodsCopy[position].isInCart())
					v.setBackgroundColor(Color.parseColor("#80445566"));
				else
					v.setBackgroundColor(Color.TRANSPARENT);


				changeCartInDB CC = new changeCartInDB();
				CC.execute(position);
			}
		});
		//-----------------------------------------------------------------------------------

		return theView;

	}


	private class changeCartInDB extends AsyncTask<Integer, Void, Void> {


		@Override
		protected Void doInBackground(Integer pos[]) {

			int id = FoodsCopy[pos[0]].getId();
			int cart = GeneralFunctions.boolToInt(FoodsCopy[pos[0]].isInCart());

			try {


				String sql = Constants.PHP_URL + "?fun=upCart&cart=" + cart + "&id=" + id;
				URL url = new URL(sql);
				URLConnection urlConnection = url.openConnection();
				urlConnection.getInputStream();


			}catch (Exception e){
				e.printStackTrace();
			}

			return null;
		}
	}

}
