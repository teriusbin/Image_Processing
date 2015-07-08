package com.samsung.ip;

import com.samsung.ip.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Setting extends FragmentActivity {

	public final static int FRAGMENT_RESET = 0;
	public final static int FRAGMENT_ALGORITHM = 1;
	public final static int FRAGMENT_PARAMETER = 2;
	public final static String TAG = "Setting";
	public Fragment reset_frag;
	public Fragment algorithm_frag;
	public Fragment parameter_frag;
	int mCurrentFragmentIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting_page);

		Button btn_home = (Button) findViewById(R.id.btn_home);
		Button btn_reset = (Button) findViewById(R.id.btn_reset);
		Button btn_algorithm = (Button) findViewById(R.id.btn_algorithm);
		Button btn_parameter = (Button) findViewById(R.id.btn_parameter);

		btn_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		btn_reset.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_RESET;
				fragmentReplace(mCurrentFragmentIndex);
			}
		});

		btn_algorithm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_PARAMETER;
				fragmentReplace(mCurrentFragmentIndex);
			}
		});

		btn_parameter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_ALGORITHM;
				fragmentReplace(mCurrentFragmentIndex);
			}
		});

		algorithm_frag = new Setting_Algorithm();
		parameter_frag = new Setting_Parameter();
		reset_frag = new Setting_Reset();

		Log.d(TAG, "test");
		mCurrentFragmentIndex = FRAGMENT_ALGORITHM;
		fragmentReplace(mCurrentFragmentIndex);

		

	}

	/*
	 * fragment 전환 부분
	 */
	public void fragmentReplace(int reqNewFragmentIndex) {

		Fragment newFragment = null;
		Log.d(TAG, "fragmentReplace " + reqNewFragmentIndex);
		newFragment = getFragment(reqNewFragmentIndex);

		// replace fragment
		final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.ll_fragment_setting, newFragment);

		// Commit the transaction
		transaction.commit();
	}

	private Fragment getFragment(int idx) {
		Fragment newFragment = null;

		switch (idx) {
		case FRAGMENT_RESET:
			newFragment = reset_frag;
			break;
		case FRAGMENT_ALGORITHM:
			newFragment = algorithm_frag;
			break;
		case FRAGMENT_PARAMETER:
			newFragment = parameter_frag;
			break;

		default:
			Log.d(TAG, "Unhandle case");
			break;
		}

		return newFragment;
	}

}
