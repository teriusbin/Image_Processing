package com.samsung.ip;

import java.nio.ByteBuffer;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import uk.co.senab.photoview.PhotoViewAttacher;

@SuppressLint("NewApi")
public class Main_Result extends Fragment {

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

		inputBitmap = ((JniIPActivity) getActivity()).scaledBitmap;
		int bytes = inputBitmap.getByteCount();
		
		ByteBuffer inputBuffer = ByteBuffer.allocate(bytes);
		inputBitmap.copyPixelsToBuffer(inputBuffer);
		
		inputPixel = inputBuffer.array(); 
		outputPixel = new byte[bytes];

		int length =JniIPActivity.nativeGetOutputPixel(inputPixel, outputPixel, inputBitmap.getWidth(), inputBitmap.getHeight());
		
		ByteBuffer outBuffer = ByteBuffer.allocate(length);
		outBuffer.put(outputPixel, 0, length);
		outBuffer.rewind();
		
		outputBitmap = Bitmap.createBitmap(inputBitmap.getWidth(),inputBitmap.getHeight(),Bitmap.Config.ARGB_8888);
		outputBitmap.copyPixelsFromBuffer(outBuffer);
		
		image2 = (ImageView) getView().findViewById(R.id.imageview2);
		mAttacher = new PhotoViewAttacher(image2);
	
		image2.setImageBitmap(outputBitmap);
	
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}
}
