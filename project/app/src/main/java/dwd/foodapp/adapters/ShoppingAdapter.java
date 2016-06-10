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

public class ShoppingAdapter extends ArrayAdapter{

	ArrayList <Boolean> foodsSelected;

	public ShoppingAdapter(Context context, ArrayList <Food> foods, ArrayList <Boolean> selected) {
		super(context, R.layout.layout_shopping_row, foods);
		foodsSelected = selected;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {


		//Creates the view
		LayoutInflater theInflater = LayoutInflater.from(getContext());
		View theView = theInflater.inflate(R.layout.layout_shopping_row, parent, false);

		//Deals with the names
		String S = ((Food) getItem(position)).getName();
		TextView tv = (TextView) theView.findViewById(R.id.TextView_ShoppingList);
		tv.setText(S);

		if (foodsSelected.get(position))
			theView.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_SELECTED));
		else
			theView.setBackgroundColor(Color.parseColor(Constants.NEW_FOOD_CAT_NOT_SELECTED));

		return theView;

	}
}
