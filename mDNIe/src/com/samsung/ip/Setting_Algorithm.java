package com.samsung.ip;

import java.util.ArrayList;
import java.util.List;

import com.samsung.ip.algorithm.Algorithm_ListView;
import com.samsung.ip.algorithm.ListviewAdapter;
import com.samsung.ip.algorithm.Parameter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

		ListView listView = (ListView) getView().findViewById(R.id.algo_list);

		ArrayList<Algorithm_ListView> data = new ArrayList<>();

		Algorithm_ListView algo1 = new Algorithm_ListView(true, "algorithm1");
		Algorithm_ListView algo2 = new Algorithm_ListView(true, "algorithm2");
		Algorithm_ListView algo3 = new Algorithm_ListView(true, "algorithm3");
		Algorithm_ListView algo4 = new Algorithm_ListView(true, "algorithm4");
		Algorithm_ListView algo5 = new Algorithm_ListView(true, "algorithm5");
		Algorithm_ListView algo6 = new Algorithm_ListView(true, "algorithm6");
		Algorithm_ListView algo7 = new Algorithm_ListView(true, "algorithm7");
		Algorithm_ListView algo8 = new Algorithm_ListView(true, "algorithm8");
		Algorithm_ListView algo9 = new Algorithm_ListView(true, "algorithm9");
		Algorithm_ListView algo10 = new Algorithm_ListView(true, "algorithm10");
		Algorithm_ListView algo11 = new Algorithm_ListView(true, "algorithm11");

		data.add(algo1);
		data.add(algo2);
		data.add(algo3);
		data.add(algo4);
		data.add(algo5);
		data.add(algo6);
		data.add(algo7);
		data.add(algo8);
		data.add(algo9);
		data.add(algo10);
		data.add(algo11);

		ListviewAdapter adapter = new ListviewAdapter(getActivity(), R.layout.item, data);
		listView.setAdapter(adapter);

		// AppPreference pref = new
		// AppPreference(getActivity().getApplicationContext());
		// pref.put(AppPreference.TEST, 10);

		/*
		 * 파라미터 셋팅 테스트
		 */
		List<Parameter> algo1_parameter = new ArrayList<>();

		algo1_parameter.add(new Parameter("para1", true, 1));
		algo1_parameter.add(new Parameter("para2", true, 2));
		algo1_parameter.add(new Parameter("para3", true, 5));

		for (Parameter para : algo1_parameter) {
			// Log.d(TAG, "test");

			Log.d(TAG, "[parameter] paramter =" + para.getParameter() + " enable =" + para.isEnable() + " para ="
					+ para.getData());

		}

		List<Parameter> algo2_parameter = new ArrayList<>();
		algo2_parameter.add(new Parameter("para1", true, 10));
		algo2_parameter.add(new Parameter("para2", true, 20));
		algo2_parameter.add(new Parameter("para3", true, 50));

		for (Parameter para : algo2_parameter) {
			// Log.d(TAG, "test");

			Log.d(TAG, "[parameter] paramter =" + para.getParameter() + " enable =" + para.isEnable() + " para ="
					+ para.getData());

		}
		List<Parameter> algo3_parameter = new ArrayList<>();
		algo3_parameter.add(new Parameter("para1", true, 100));
		algo3_parameter.add(new Parameter("para2", true, 200));
		algo3_parameter.add(new Parameter("para3", true, 500));

	}
}
