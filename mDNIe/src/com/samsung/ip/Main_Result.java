package com.samsung.ip;

import java.nio.ByteBuffer;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

@SuppressLint("NewApi")
public class Main_Result extends Fragment {

	public final static String TAG = "Main_Result";

	public ImageView image2;

	PhotoViewAttacher mAttacher;
	private byte[] inputPixel;
	private byte[] outputPixel;

	static Bitmap inputBitmap;
	static Bitmap outputBitmap;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.main_result_frag, container, false);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		Log.d(TAG, "image processing");
		inputBitmap = ((JniIPActivity) getActivity()).scaledBitmap;
		int bytes = inputBitmap.getByteCount();

		ByteBuffer inputBuffer = ByteBuffer.allocate(bytes);
		inputBitmap.copyPixelsToBuffer(inputBuffer);

		inputPixel = inputBuffer.array();
		outputPixel = new byte[bytes];

		int length = JniIPActivity.nativeGetOutputPixel(inputPixel, outputPixel, inputBitmap.getWidth(),
				inputBitmap.getHeight());

		ByteBuffer outBuffer = ByteBuffer.allocate(length);
		outBuffer.put(outputPixel, 0, length);
		outBuffer.rewind();

		outputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		outputBitmap.copyPixelsFromBuffer(outBuffer);

		image2 = (ImageView) getView().findViewById(R.id.imageview2);
		mAttacher = new PhotoViewAttacher(image2);

		((JniIPActivity) getActivity()).resultBitmap = outputBitmap;
		image2.setImageBitmap(outputBitmap);
		((JniIPActivity) getActivity()).image_load_flag = true;

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	public void test() {

		if (((JniIPActivity) getActivity()).image_result_flag == false) {
			Log.d(TAG, "bitmap = rawBitmap");
			image2 = (ImageView) getView().findViewById(R.id.imageview2);
			mAttacher = new PhotoViewAttacher(image2);
			image2.setImageBitmap(((JniIPActivity) getActivity()).rawBitmap);
			((JniIPActivity) getActivity()).image_result_flag = true;
		} else if (((JniIPActivity) getActivity()).image_result_flag == true) {

			Log.d(TAG, "bitmap = resultBitmap");
			image2 = (ImageView) getView().findViewById(R.id.imageview2);
			mAttacher = new PhotoViewAttacher(image2);
			image2.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);
			((JniIPActivity) getActivity()).image_result_flag = false;
		}
	}

}
