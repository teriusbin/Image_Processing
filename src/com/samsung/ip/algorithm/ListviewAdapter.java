package com.samsung.ip.algorithm;

import java.util.ArrayList;

import com.samsung.ip.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListviewAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private ArrayList<Algorithm_ListView> data;
	private int layout;

	public ListviewAdapter(Context context, int layout, ArrayList<Algorithm_ListView> data) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.data = data;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public String getItem(int position) {
		return data.get(position).getName();
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(layout, parent, false);
		}
		Algorithm_ListView listviewitem = data.get(position);
	//	TextView name = (TextView) convertView.findViewById(R.id.textview);
	//	name.setText(listviewitem.getName());
		return convertView;
	}
}