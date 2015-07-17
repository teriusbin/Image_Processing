package com.samsung.ip;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class Main_Realtime extends Fragment {
	public final static String TAG = "Main_Realtime";
	public ImageView image1;
	private ImageView handle;
	static Bitmap RealtimeBitmap;
	public SeekBar seekbar1_1;
	public SeekBar seekbar1_2;
	public SeekBar seekbar2_1;
	public SeekBar seekbar2_2;
	public TextView algo1_para1;
	public TextView algo1_para2;
	public TextView algo2_para1;
	public TextView algo2_para2;
	PhotoViewAttacher RealtimeAttacher;

	
	public Main_Realtime newInstance(){
		Main_Realtime fragment = new Main_Realtime();
		return fragment;
	}
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.main_realtime_frag, container, false);

		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		image1 = (ImageView) getView().findViewById(R.id.image_realtime);

		RealtimeAttacher = new PhotoViewAttacher(image1);
		//image1.setImageBitmap(((JniIPActivity) getActivity()).scaledBitmap);
		handle = (ImageView) getView().findViewById(R.id.handle);
		algo1_para1 = (TextView) getView().findViewById(R.id.algo1_para1_value);
		algo1_para2 = (TextView) getView().findViewById(R.id.algo1_para2_value);
		algo2_para1 = (TextView) getView().findViewById(R.id.algo2_para1_value);
		algo2_para2 = (TextView) getView().findViewById(R.id.algo2_para2_value);
		seekbar1_1 = (SeekBar) getView().findViewById(R.id.algo1_seekbar1);
		seekbar1_2 = (SeekBar) getView().findViewById(R.id.algo1_seekbar2);
		seekbar2_1 = (SeekBar) getView().findViewById(R.id.algo2_seekbar1);
		seekbar2_2 = (SeekBar) getView().findViewById(R.id.algo2_seekbar2);

		seekbar1_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				algo1_para1.setText(" 현재 값 : " + progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		
		seekbar1_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				algo1_para2.setText(" 현재 값 : " + progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		
		seekbar2_1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				algo2_para1.setText(" 현재 값 : " + progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
		
		seekbar2_2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				algo2_para2.setText(" 현재 값 : " + progress);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

}
