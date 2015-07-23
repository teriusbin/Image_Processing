package com.samsung.ip.algorithm;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AppPreference {
	private final String PREF_NAME = "com.semo.jnigl";
	public final static String TAG = "AppPreference";
	public final static String PREF_INTRO_USER_AGREEMENT = "PREF_USER_AGREEMENT";
	public final static String PREF_MAIN_VALUE = "PREF_MAIN_VALUE";
	public final static String TEST = "test";

	static Context mContext;

	public AppPreference(Context c) {
		mContext = c;
	}
	public AppPreference() {
	
	}

	public void put(String key, String value) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		Log.d(TAG, "preference String put");
		editor.putString(key, value);
		editor.commit();
	}

	public void put(String key, boolean value) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		Log.d(TAG, "preference boolean put");
		editor.putBoolean(key, value);
		editor.commit();
	}

	public void put(String key, int value) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		Log.d(TAG, "preference int put");
		editor.putInt(key, value);
		editor.commit();
	}

	public String getValue(String key, String dftValue) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

		try {
			return pref.getString(key, dftValue);
		} catch (Exception e) {
			return dftValue;
		}

	}

	public int getValue(String key, int dftValue) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

		try {
			return pref.getInt(key, dftValue);
		} catch (Exception e) {
			return dftValue;
		}

	}

	public boolean getValue(String key, boolean dftValue) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);

		try {
			return pref.getBoolean(key, dftValue);
		} catch (Exception e) {
			Log.e(TAG, "Preference get boolean exception", e);
			return dftValue;
		}
	}
	
	public void remove(String key) {
		SharedPreferences pref = mContext.getSharedPreferences(PREF_NAME, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = pref.edit();
		Log.d(TAG, "preference String put");
		editor.remove(key);
		//editor.putString(key, value);
		editor.commit();
	}
}
