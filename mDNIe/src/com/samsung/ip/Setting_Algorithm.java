package com.samsung.ip;

import java.util.ArrayList;

import com.samsung.ip.algorithm.AppPreference;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Setting_Algorithm extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.setting_algorithm, container, false);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		ArrayList<String> arrGeneral = new ArrayList<String>();
		arrGeneral.add("Algorithm1");
		arrGeneral.add("Algorithm2");
		arrGeneral.add("Algorithm3");		
		arrGeneral.add("Algorithm4");

		ArrayAdapter<CharSequence> Adapter2;
		Adapter2 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.algorithm, android.R.layout.simple_list_item_checked);
		// ¾î´ðÅÍ ÁØºñ
		ArrayAdapter<String> Adapter;
		//Adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),	android.R.layout.simple_list_item_checked, arrGeneral);

		ListView list = (ListView) getView().findViewById(R.id.algo_list);
		list.setAdapter(Adapter2);
		
		AppPreference pref = new AppPreference(getActivity().getApplicationContext());
		//pref.put(AppPreference.TEST, 10);
		
	}

}
