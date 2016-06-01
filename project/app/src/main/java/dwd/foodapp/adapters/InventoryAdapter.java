package dwd.foodapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dwd.foodapp.R;
import dwd.foodapp.objs.Food;
import dwd.foodapp.statics.Constants;

public class InventoryAdapter extends ArrayAdapter{


	public InventoryAdapter(Context context, Food[] foods) {
		super(context, R.layout.layout_inventory_row, foods);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater theInflater = LayoutInflater.from(getContext());

		View theView = theInflater.inflate(R.layout.layout_inventory_row, parent, false);

		Food thisFood = (Food) getItem(position);

		String S = thisFood.getName();
		boolean stock = thisFood.getStock();


		TextView tv = (TextView) theView.findViewById(R.id.TextView_InventoryListLayout);

		if (stock) {
			theView.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_IN_STOCK_COLOR));
		}else{
			theView.setBackgroundColor(Color.parseColor(Constants.INVENTORY_LIST_OUT_STOCK_COLOR));
		}

		tv.setText(S);

		return theView;

	}
}
