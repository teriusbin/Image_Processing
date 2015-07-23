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

	public static final String TAG = "ParameterSettingListView";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.checkable_main);

		// actionbar ����
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setBackgroundDrawable(new ColorDrawable(0xFF0a84ad));
		getActionBar().setDisplayShowTitleEnabled(false);
		getActionBar().setHomeButtonEnabled(true);

		Intent intent = getIntent();
		int index = intent.getExtras().getInt("AlgorithmIndex");
		algoName = intent.getExtras().getString("AlgorithmName");
		algorithmData = (AlgorithmData) intent.getExtras().getSerializable("algorithmData");
		algorithmEnableData = (AlgorithmEnableData) intent.getExtras().getSerializable("algorithmEnableData");
		if(algorithmEnableData == null){
			Log.d(TAG, "endable data ");
		}

		String algoname = algoName;
		int resStringID = getResources().getIdentifier(algoname + "_parameterlist", "array", "com.samsung.ip");
		String[] algoArray = getResources().getStringArray(R.array.algorithmlist);
		String[] parameterArray = getResources().getStringArray(resStringID);
		ArrayList<AlgorithmItem> mParaList = new ArrayList<AlgorithmItem>();

		
		int itemCnt = 0;
		String paraName;
		// �˰��� Name���� ArrayList �˻� �� �ش� �˰��� �Ķ���͵�� ����
		//Log.d(TAG, "algo length " +algorithmEnableData.algoList.le);
		for (int algoindex = 0; algoindex < algorithmEnableData.algoList.length; algoindex++) {

			if (algoName.equals(algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"))) {
				AlgorithmItem _p;
				// Log.d(TAG, ""
				// +algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"));
				for (String str : parameterArray) {

					paraName = str;
					// Log.d(TAG, "" +
					// algorithmEnableData.algorithmEableList.get(algoindex).get(paraName));
					AlgorithmItem p = new AlgorithmItem(str,
							(boolean) algorithmEnableData.algorithmEableList.get(algoindex).get(paraName), itemCnt++);
					mParaList.add(p);
				}
			}
		}

		// ����� ���
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

			String paraName;
			// �˰��� Name���� ArrayList �˻� �� �ش� �˰��� �Ķ���͵�� ����
			// �˰��� ��ü �˻�
			for (int algoindex = 0; algoindex < algorithmEnableData.algoList.length; algoindex++) {

				// �̸��� ���� ��
				if (algoName.equals(algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"))) {
					AlgorithmItem _p;
					Log.d(TAG, "" + algorithmEnableData.algorithmEableList.get(algoindex).get("algorithmName"));

					// �Ķ���� ������ŭ enable üũ
					for (int paraindex = 0; paraindex < algorithmEnableData.parameterArrayList
							.get(algoindex).length; paraindex++) {
						// ����Ϳ��� �������� �ϳ��� ������ Ȯ��
						// Ȯ�� �� enable ���� ����
						_p = m_adapter.getItem(paraindex);

						if (_p.isEnable()) {
							algorithmEnableData.algorithmEableList.get(algoindex).put(_p.getName(), true);
						} else {
							algorithmEnableData.algorithmEableList.get(algoindex).put(_p.getName(), false);
						}
					}
				}

			}
			break;

		}
		return super.onOptionsItemSelected(item);

	}

	// Back key ó��
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
