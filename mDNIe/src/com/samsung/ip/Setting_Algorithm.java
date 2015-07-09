package com.samsung.ip;

import java.util.ArrayList;
import java.util.List;

import com.samsung.ip.algorithm.AppPreference;
import com.samsung.ip.algorithm.Parameter;

import android.os.Bundle;
import android.support.v4.app.Fragment; 
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Setting_Algorithm extends Fragment {

	public final static String TAG = "Setting_Algorithm";

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
		Adapter2 = ArrayAdapter.createFromResource(getActivity().getApplicationContext(), R.array.algorithm,
				android.R.layout.simple_list_item_checked);
		// 어댑터 준비
		ArrayAdapter<String> Adapter;
		// Adapter = new
		// ArrayAdapter<String>(getActivity().getApplicationContext(),
		// android.R.layout.simple_list_item_checked, arrGeneral);

		ListView list = (ListView) getView().findViewById(R.id.algo_list);
		list.setAdapter(Adapter2);

	//	AppPreference pref = new AppPreference(getActivity().getApplicationContext());
		// pref.put(AppPreference.TEST, 10);

		/* 
		 * 파라미터 셋팅 테스트 
		 */
		List<Parameter> algo1_parameter = new ArrayList<>();
	
		algo1_parameter.add(new Parameter("para1", true, 1));
		algo1_parameter.add(new Parameter("para2", true, 2));
		algo1_parameter.add(new Parameter("para3", true, 5));

		for (Parameter para : algo1_parameter) {
			//Log.d(TAG, "test");
			
				Log.d(TAG, "[parameter] paramter =" + para.getParameter() + " enable =" + para.isEnable()
						+ " para =" + para.getData());			

		}
	
		List<Parameter> algo2_parameter = new ArrayList<>();
		algo2_parameter.add(new Parameter("para1", true, 10));
		algo2_parameter.add(new Parameter("para2", true, 20));
		algo2_parameter.add(new Parameter("para3", true, 50));

		for (Parameter para : algo2_parameter) {
			//Log.d(TAG, "test");
			
				Log.d(TAG, "[parameter] paramter =" + para.getParameter() + " enable =" + para.isEnable()
						+ " para =" + para.getData());			

		}
		List<Parameter> algo3_parameter = new ArrayList<>();
		algo3_parameter.add(new Parameter("para1", true, 100));
		algo3_parameter.add(new Parameter("para2", true, 200));
		algo3_parameter.add(new Parameter("para3", true, 500));
		
	}
}
