package com.samsung.ip.algorithm;

import java.util.ArrayList;
import java.util.List;

import com.samsung.ip.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ParameterAdapter extends ArrayAdapter<AlgorithmItem> {

	private ArrayList<AlgorithmItem> items;
	private Context mContext;
	private List<String> mList;
	public static final String TAG = "AlgrParameterAdapterithmAdapter";

	public ParameterAdapter(Context context, int textViewResourceId, ArrayList<AlgorithmItem> items) {
		super(context, textViewResourceId, items);
		mContext = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final int pos = position;
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.setting_parameter, null);
		}
		CheckBox mcheckbox = (CheckBox) v.findViewById(R.id.checkbox_parameter);
		AlgorithmItem p = items.get(position);
		if (p != null) {
			TextView tt = (TextView) v.findViewById(R.id.text_parameter);
			if (p.isEnable()) {
				mcheckbox.setChecked(true);
			} else {
				mcheckbox.setChecked(false);
			}

			if (tt != null) {
				tt.setText(p.getName());
			}
		}

		mcheckbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					Log.d(TAG, "poisition checked :" + pos);
					AlgorithmItem _p = items.get(pos);
					_p.setEnable(true);
					// perform logic
				} else if (!isChecked) {
					Log.d(TAG, "poisition checked :" + pos);
					AlgorithmItem _p = items.get(pos);
					_p.setEnable(false);
				}

			}
		});
		return v;
	}

}
