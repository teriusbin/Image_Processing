package com.samsung.ip;

import java.util.ArrayList;
import java.util.HashMap;

import com.samsung.ip.algorithm.AlgorithmData;
import com.samsung.ip.algorithm.AlgorithmEnableData;
import com.samsung.ip.algorithm.AlgorithmEnableData.AlgoSequence;
import com.samsung.ip.algorithm.AlgorithmItem;
import com.samsung.ip.algorithm.AlgrithmAdapter;
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

public class AlgorithmSettingListView extends ListActivity {

	final int REQ_CODE_PARA_SETTING = 102;
	private AlgrithmAdapter m_adapter;
	private DragSortListView list;
	private AlgorithmData algorithmData;
	private AlgorithmEnableData algorithmEnableData;

	public static final String TAG = "AlgorithmSettingListView";
	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
		@Override
		public void drop(int from, int to) {
			if (from != to) {
				DragSortListView list = getListView();
				Log.d(TAG, "from " + from);

				AlgorithmItem from_item = m_adapter.getItem(from);
				m_adapter.remove(from_item);
				m_adapter.insert(from_item, to);

				// 알고리즘 순서도 변경
				
				AlgoSequence temp = algorithmEnableData.algoSequence.get(from);				
				algorithmEnableData.algoSequence.remove(from);
				algorithmEnableData.algoSequence.add(to,temp);


				list.moveCheckState(from, to);
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.checkable_main);

		// action 바 설정
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setBackgroundDrawable(new ColorDrawable(0xFF0a84ad));
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);

		// 인텐트 설정
		Intent intent = getIntent();
		algorithmData = (AlgorithmData) intent.getSerializableExtra("algorithmData");
		algorithmEnableData = (AlgorithmEnableData) intent.getSerializableExtra("algorithmEnableData");

		// 알고리즘 listview 생성을 위한 아이템 삽입
		ArrayList<AlgorithmItem> mAlgoList = new ArrayList<AlgorithmItem>();
		String[] array = getResources().getStringArray(R.array.algorithmlist);

		/*
		 * 알고리즘 설정 페이지 생성
		 */
		// 알고리즘 순서 ArrayList가 없을 시에는 새로 생성
		if (algorithmEnableData.algoSequence == null) {
			algorithmEnableData.algoSequence = new ArrayList<AlgoSequence>();
			int algorithmIndex = 0;
			for (String str : array) {

				// String array에서 Algorithm 이름으로 PameterList array를 찾는다.
				int resStringID = getResources().getIdentifier(str + "_parameterlist", "array", "com.samsung.ip");
				String[] paraArray = getResources().getStringArray(resStringID);

				AlgorithmItem p = new AlgorithmItem(str,
						(boolean) algorithmEnableData.algorithmEableList.get(algorithmIndex).get("algoEnable"),
						algorithmIndex);

				// 알고리즘 Arraylist에 Item Add
				mAlgoList.add(p);
				algorithmEnableData.addAlgoSequce(str, algorithmIndex, algorithmIndex++);

			}
			for (int i = 0; i < algorithmEnableData.algoSequence.size(); i++) {
				Log.d(TAG, "sequence :" + algorithmEnableData.algoSequence.get(i).getAlgoName());
			}

		} else { // 알고리즘 순서 ArrayList가 있을 시에는 순서에 맞게 정렬
			for (int algorithmindex = 0; algorithmindex < algorithmEnableData.algoSequence.size(); algorithmindex++) {

				// algorithmEnableData 의 algoSequence Arraylist로 리스트뷰를 생성
				AlgorithmItem p = new AlgorithmItem(algorithmEnableData.algoSequence.get(algorithmindex).getAlgoName(),
						(boolean) algorithmEnableData.algorithmEableList.get(algorithmindex).get("algoEnable"),
						algorithmEnableData.algoSequence.get(algorithmindex).getIndex());
				mAlgoList.add(p);
			}
			
			for (int i = 0; i < algorithmEnableData.algoSequence.size(); i++) {
				Log.d(TAG, "enable data :" +  algorithmEnableData.algorithmEableList.get(i).get("algoEnable"));
			}
		}

		m_adapter = new AlgrithmAdapter(this, R.layout.setting_algorithm, mAlgoList);

		setListAdapter(m_adapter);
		list = getListView();
		list.setDropListener(onDrop);

	}

	public AlgorithmData getAlgorithmData() {

		return algorithmData;
	}

	public AlgorithmEnableData getAlgorithmEnableData() {

		return algorithmEnableData;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.setting_menu_algo, menu);
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
		case R.id.action_save_algo:
			// algorithm enable 정보 저장

			HashMap<String, Object> paraMap;
			algorithmData.algorithmList.clear(); // 영상처리 algorithm list 초기화

			AlgorithmItem _p;
			for (int itemCnt = 0; itemCnt < algorithmEnableData.algoList.length; itemCnt++) {
				_p = m_adapter.getItem(itemCnt);
				if (_p.isEnable()) {
					algorithmEnableData.algorithmEableList.get(itemCnt).put("algoEnable", true);
				} else {
					algorithmEnableData.algorithmEableList.get(itemCnt).put("algoEnable", false);
				}

				// enable정보, algorithm 순서 정보대로 영상처리 위한 algorithm list 생성
				if (_p.isEnable()) {
					Log.d(TAG, "algorithmname :" + _p.getName());
					paraMap = algorithmData.InsertHashMapToAlgorithmList(_p.getName());

					Log.d(TAG, "" + paraMap.get("algorithmName"));
				}
			}
			Log.d(TAG, "===========================================");
			for (int i = 0; i < algorithmEnableData.algoSequence.size(); i++) {
				Log.d(TAG, "sequence :" + algorithmEnableData.algoSequence.get(i).getAlgoName());
			}
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public DragSortListView getListView() {
		return (DragSortListView) super.getListView();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQ_CODE_PARA_SETTING) {
			if (resultCode == Activity.RESULT_OK) {
				Log.d(TAG, "catch from parametersettingListView");
				algorithmData = (AlgorithmData) data.getSerializableExtra("algorithmData");
				algorithmEnableData = (AlgorithmEnableData) data.getSerializableExtra("algorithmEnableData");

			}
		}
	}

}
