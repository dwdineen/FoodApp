package dwd.foodapp.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import dwd.foodapp.R;

public class InventoryAdapter extends CursorAdapter {


	public InventoryAdapter(Context context, Cursor c) {
		super(context, c, 0);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		return LayoutInflater.from(context).inflate(R.layout.layout_inventory_row, parent, false);
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		TextView tvName = (TextView) view.findViewById(R.id.TextView_InventoryListLayout);

		//Extracts from cursor
		String s = cursor.getString(cursor.getColumnIndexOrThrow("Name"));

		//Sets the things
		tvName.setText(s);
	}
}
