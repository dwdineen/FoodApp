package dwd.foodapp.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import dwd.foodapp.R;
import dwd.foodapp.statics.Constants;

public class NewFoodCategoryAdapter extends ArrayAdapter{

	private boolean[] selectedCats;

	public NewFoodCategoryAdapter(Context context, String[] categoryNames, boolean[] selectedCatsIn) {
		super(context, R.layout.layout_inv_menu_row, categoryNames);

		selectedCats = selectedCatsIn;
	}

	@Override
	public View getView(final int pos, View convertView, ViewGroup parent) {

		System.out.println(".....................Im Getting: " + pos);

		//Creates the view
		LayoutInflater theInflater = LayoutInflater.from(getContext());
		View theView = theInflater.inflate(R.layout.layout_new_food_category_row, parent, false);

		//Deals with the names
		String S = getItem(pos).toString();
		TextView tv = (TextView) theView.findViewById(R.id.TextView_NewFoodLayout);
		tv.setText(S);

		if (selectedCats[pos]){
			System.out.println(".......Selected: " + pos);
			theView.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_SELECTED));
		//	theView.setDrawingCacheBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_SELECTED));
		}
		else {
			System.out.println(".....Not Selected: " + pos);
			theView.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_NOT_SELECTED));

		}




		return theView;

	}
}
