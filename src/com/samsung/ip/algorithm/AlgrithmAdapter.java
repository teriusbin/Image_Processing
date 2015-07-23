package com.samsung.ip.algorithm;

import java.util.ArrayList;

import com.samsung.ip.AlgorithmSettingListView;
import com.samsung.ip.ParameterSettingListView;
import com.samsung.ip.R;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class AlgrithmAdapter extends ArrayAdapter<AlgorithmItem> {

	final int REQ_CODE_PARA_SETTING = 102;
	private ArrayList<AlgorithmItem> items;
	private Context mContext;
	public static final String TAG = "AlgrithmAdapter";

	public AlgrithmAdapter(Context context, int textViewResourceId, ArrayList<AlgorithmItem> items) {
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
			v = vi.inflate(R.layout.setting_algorithm, null);
		}
		

		CheckBox mcheckbox = (CheckBox) v.findViewById(R.id.checkbox_algorithm);
		AlgorithmItem p = items.get(pos);
		if (p != null) {
			TextView tt = (TextView) v.findViewById(R.id.text_algorithm);
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
		Button mbutton = (Button) v.findViewById(R.id.click_remove);
		mbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlgorithmItem _p = items.get(pos);
				Log.d(TAG, "poisition clicked :" + pos + " name :" + _p.getName() + " index :" + _p.getmIndex());
				Intent intentSubActivity = new Intent(mContext, ParameterSettingListView.class);
				intentSubActivity.putExtra("AlgorithmIndex", _p.getmIndex());
				intentSubActivity.putExtra("AlgorithmName",_p.getName());
				intentSubActivity.putExtra("algorithmData",((AlgorithmSettingListView)mContext).getAlgorithmData());
				intentSubActivity.putExtra("algorithmEnableData",((AlgorithmSettingListView)mContext).getAlgorithmEnableData());
				((AlgorithmSettingListView)mContext).startActivityForResult(intentSubActivity,REQ_CODE_PARA_SETTING);
				
			}
		});
		return v;
	}

	@Override
	public AlgorithmItem getItem(int position) {
		// TODO Auto-generated method stub

		return super.getItem(position);
	}

}
