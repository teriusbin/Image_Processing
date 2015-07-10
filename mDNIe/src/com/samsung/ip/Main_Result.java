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


	public ImageView resultImage;


	PhotoViewAttacher mAttacher;

	

	static Bitmap inputBitmap;
	static Bitmap outputBitmap;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.main_result_frag, container, false);

		return v;
	}
	
	
	public byte[] getInputPixel(Bitmap input){
		
		byte[] resultPixel;
		
		int bytes = input.getByteCount();
		
		ByteBuffer inputBuffer = ByteBuffer.allocate(bytes);
		input.copyPixelsToBuffer(inputBuffer);
		
		resultPixel = inputBuffer.array();
		
		return resultPixel;
	}
	
	public Bitmap getOutputBitmap(int length, byte[] outputPixel){
		
		Bitmap resultBitmap;
		
		ByteBuffer outBuffer = ByteBuffer.allocate(length);
		
		outBuffer.put(outputPixel, 0, length);
		outBuffer.rewind();
		
		resultBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(), Bitmap.Config.ARGB_8888);
		resultBitmap.copyPixelsFromBuffer(outBuffer);

		return resultBitmap;
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		inputBitmap = ((JniIPActivity) getActivity()).scaledBitmap;
		
		byte[] inputPixel = getInputPixel(inputBitmap);
		byte[] outputPixel = new byte[inputBitmap.getByteCount()];

		

		int length = JniIPActivity.nativeGetOutputPixel(inputPixel, outputPixel, inputBitmap.getWidth(),
				inputBitmap.getHeight());

		
		outputBitmap = getOutputBitmap(length, outputPixel);
		
		resultImage = (ImageView) getView().findViewById(R.id.imageview2);
		mAttacher = new PhotoViewAttacher(resultImage);

		((JniIPActivity) getActivity()).resultBitmap = outputBitmap;
		resultImage.setImageBitmap(outputBitmap);
		((JniIPActivity) getActivity()).image_load_flag = true;

	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		resultImage.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);


	}

	public void Result_toggle() {

		if (((JniIPActivity) getActivity()).image_result_flag == false) {
			
			Log.d(TAG, "bitmap = rawBitmap");

			resultImage = (ImageView) getView().findViewById(R.id.imageview2);
			mAttacher = new PhotoViewAttacher(resultImage);
			resultImage.setImageBitmap(((JniIPActivity) getActivity()).scaledBitmap);

			((JniIPActivity) getActivity()).image_result_flag = true;
			
		} else if (((JniIPActivity) getActivity()).image_result_flag == true) {

			Log.d(TAG, "bitmap = resultBitmap");
			resultImage = (ImageView) getView().findViewById(R.id.imageview2);
			mAttacher = new PhotoViewAttacher(resultImage);
			resultImage.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);
			((JniIPActivity) getActivity()).image_result_flag = false;
			
		}
	}

}
