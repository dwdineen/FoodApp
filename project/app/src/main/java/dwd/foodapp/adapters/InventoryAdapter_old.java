package dwd.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dwd.foodapp.R;
import dwd.foodapp.objs.Food;

public class InventoryAdapter_old extends ArrayAdapter{


	public InventoryAdapter_old(Context context, Food[] foods) {
		super(context, R.layout.layout_inventory_row, foods);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater theInflater = LayoutInflater.from(getContext());

		View theView = theInflater.inflate(R.layout.layout_inventory_row, parent, false);

		String S = getItem(position).toString();
		TextView tv = (TextView) theView.findViewById(R.id.TextView_InventoryListLayout);

		tv.setText(S);

		return theView;

	}
}
