package com.samsung.ip;

import java.util.ArrayList;

import com.samsung.ip.algorithm.AlgorithmData;
import com.samsung.ip.algorithm.AlgorithmEnableData;
import com.samsung.ip.algorithm.AlgorithmItem;
import com.samsung.ip.algorithm.ParameterAdapter;
import com.samsung.ip.draglist.DragSortListView;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.WindowManager;

public class ParameterSettingListView extends ListActivity {
	private ParameterAdapter m_adapter;
	private DragSortListView list;
	private AlgorithmData algorithmData;
	private AlgorithmEnableData algorithmEnableData;
	private String algoName;
	private int parameterCnt;
	private int algoCnt;
	public static final String TAG = "ParameterSettingListView";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.checkable_main);

		// actionbar 설정
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setBackgroundDrawable(new ColorDrawable(0xFF0a84ad));
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);

		Intent intent = getIntent();
		int index = intent.getExtras().getInt("AlgorithmIndex");
		algoName = intent.getExtras().getString("AlgorithmName");
		//Log.d(TAG, "algoname"+algoName);
		algorithmData = (AlgorithmData) intent.getExtras().getSerializable("algorithmData");
		algorithmEnableData = (AlgorithmEnableData) intent.getExtras().getSerializable("algorithmEnableData");

		Log.d(TAG, "index :" + index);
		String[] algoArray = getResources().getStringArray(R.array.algorithmlist);
		String[] parameterArray = null;
		ArrayList<AlgorithmItem> mParaList = new ArrayList<AlgorithmItem>();
		switch (index) {

		case 0:
			parameterArray = getResources().getStringArray(R.array.algo1_parameterlist);
			break;

		case 1:
			parameterArray = getResources().getStringArray(R.array.algo2_parameterlist);
			break;

		case 2:
			parameterArray = getResources().getStringArray(R.array.algo3_parameterlist);
			break;

		case 3:
			parameterArray = getResources().getStringArray(R.array.algo4_parameterlist);
			break;

		case 4:
			parameterArray = getResources().getStringArray(R.array.algo5_parameterlist);
			break;

		case 5:
			parameterArray = getResources().getStringArray(R.array.algo6_parameterlist);
			break;

		case 6:
			parameterArray = getResources().getStringArray(R.array.algo7_parameterlist);
			break;
		}

		parameterCnt = parameterArray.length;
		algoCnt = algoArray.length;
		
		int itemCnt = 0;
		String paraName ;
		// 알고리즘 Name으로 ArrayList 검색 후 해당 알고리즘 파라미터들로 셋팅
		for (int algoindex = 0; algoindex < algoCnt; algoindex++) {
			
			if (algoName.equals(algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"))) {
				AlgorithmItem _p;
			//	Log.d(TAG, "" +algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"));
				for (String str : parameterArray) {
					
					paraName = "param"+(itemCnt+1);
					//Log.d(TAG, "" + algorithmEnableData.algorithmEableList.get(algoindex).get(paraName));
					AlgorithmItem p = new AlgorithmItem(str,
							(boolean) algorithmEnableData.algorithmEableList.get(algoindex).get(paraName), itemCnt++);
					mParaList.add(p);
				}
			}

		}

		// 어댑터 등록
		m_adapter = new ParameterAdapter(this, R.layout.setting_parameter, mParaList);

		setListAdapter(m_adapter);
		list = getListView();
	}

	@Override
	public DragSortListView getListView() {
		return (DragSortListView) super.getListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.setting_menu_para, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.

		// Handle action buttons
		switch (item.getItemId()) {

		case android.R.id.home:
			Intent intent = new Intent();
			intent.putExtra("algorithmData", algorithmData);
			intent.putExtra("algorithmEnableData", algorithmEnableData);
			setResult(Activity.RESULT_OK, intent);
			finish();

			break;

		case R.id.action_save_para:
			//
	
			String paraName ;
			// 알고리즘 Name으로 ArrayList 검색 후 해당 알고리즘 파라미터들로 셋팅
			// 알고리즘 전체 검색
			for (int algoindex = 0; algoindex < algoCnt; algoindex++) {
				
				//이름이 같을 때 
				if (algoName.equals(algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"))) {
					AlgorithmItem _p;
					Log.d(TAG, "" +algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"));
					
					//파라미터 갯수만큼 enable 체크 
					for(int paraindex = 0 ; paraindex < parameterCnt ; paraindex++){
						//어댑터에서 아이템을 하나씩 꺼내서 확인
						_p = m_adapter.getItem(paraindex);
						paraName = "param"+ (paraindex+1);
						if (_p.isEnable()) {
							algorithmEnableData.algorithmEableList.get(algoindex).put(paraName, true);
						} else {
							algorithmEnableData.algorithmEableList.get(algoindex).put(paraName, false);
						}
					}				
				}

			}
			break;

		}
		return super.onOptionsItemSelected(item);

	}

	// Back key 처리
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			Intent intent = new Intent();
			intent.putExtra("algorithmData", algorithmData);
			intent.putExtra("algorithmEnableData", algorithmEnableData);
			setResult(Activity.RESULT_OK, intent);
			finish();
			break;
		}
		return true;
	}
}
