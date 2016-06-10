package dwd.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import dwd.foodapp.R;
import dwd.foodapp.activities.InvMenuActivity;

public class InvMenuAdapter extends ArrayAdapter{


	public InvMenuAdapter(Context context, String[] values) {
		super(context, R.layout.layout_inv_menu_row, values);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		//Creates the view
		LayoutInflater theInflater = LayoutInflater.from(getContext());
		View theView = theInflater.inflate(R.layout.layout_inv_menu_row, parent, false);

		//Deals with the names
		String S = getItem(position).toString();
		TextView tv = (TextView) theView.findViewById(R.id.TextView_InvMenuLayout);
		tv.setText(S);



		return theView;

	}
}
