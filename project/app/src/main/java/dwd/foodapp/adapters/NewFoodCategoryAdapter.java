package dwd.foodapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import dwd.foodapp.R;

public class NewFoodCategoryAdapter extends ArrayAdapter{


	public NewFoodCategoryAdapter(Context context, String[] categoryNames) {
		super(context, R.layout.layout_inv_menu_row, categoryNames);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		//Creates the view
		LayoutInflater theInflater = LayoutInflater.from(getContext());
		View theView = theInflater.inflate(R.layout.layout_new_food_category_row, parent, false);

		//Deals with the names
		String S = getItem(position).toString();
		TextView tv = (TextView) theView.findViewById(R.id.TextView_NewFoodLayout);
		tv.setText(S);


		return theView;

	}
}
