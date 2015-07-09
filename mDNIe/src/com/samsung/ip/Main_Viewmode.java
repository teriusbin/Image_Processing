package com.samsung.ip;

import com.samsung.ip.R;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Main_Viewmode extends Fragment {

	public final static String TAG = "Main_Viewmode";
	public ImageView image1;
	public ImageView image2;

	static Bitmap PrevBitmap;
	static Bitmap ResultBitmap;
	PhotoViewAttacher prevAttacher;
	PhotoViewAttacher resultAttacher;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.main_viemode_frag, container, false);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		Log.d(TAG, "bitmap = rawBitmap");
		image1 = (ImageView) getView().findViewById(R.id.viewmode_prev);
		image2 = (ImageView) getView().findViewById(R.id.viewmode_result);
		prevAttacher = new PhotoViewAttacher(image1);
		resultAttacher = new PhotoViewAttacher(image2);
		image1.setImageBitmap(((JniIPActivity) getActivity()).scaledBitmap);
		image2.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);

	}
}
