package dwd.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import dwd.foodapp.R;

public class InventoryAdapter extends ArrayAdapter{


	public InventoryAdapter(Context context, String[] values) {
		super(context, R.layout.layout_inventory_row, values);
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
