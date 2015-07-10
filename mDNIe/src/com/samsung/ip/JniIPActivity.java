package com.samsung.ip;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.media.ExifInterface;

import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import uk.co.senab.photoview.PhotoViewAttacher;

public class JniIPActivity extends FragmentActivity {

	final int REQ_CODE_SELECT_IMAGE = 100;
	final String TAG = "JniGLActivity";
	// fragment 변수
	int mCurrentFragmentIndex;

	public final static int FRAGMENT_IMAGELOAD = 0;
	public final static int FRAGMENT_RESULT = 1;
	public final static int FRAGMENT_SETTING = 2;
	public final static int FRAGMENT_VIEWMODE = 3;

	public Fragment imageload_frag;
	public Fragment result_frag;
	public Fragment realtime_frag;
	public Fragment viewmode_frag;

	public ImageView image;

	public Bitmap scaledBitmap;

	public Bitmap rawBitmap;
	public Bitmap resultBitmap;

	PhotoViewAttacher mAttacher;
	public boolean image_result_flag = false;
	public boolean image_load_flag = false;
	public boolean frag_changed_flag = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_jni_gl);

		// 버튼 리스너에 등록
		Button btn_imageload = (Button) findViewById(R.id.btn_imageload);
		Button btn_result = (Button) findViewById(R.id.btn_result);
		Button btn_setting = (Button) findViewById(R.id.btn_setting);
		Button btn_realtime_setting = (Button) findViewById(R.id.btn_realtime_setting);
		Button btn_viewmode = (Button) findViewById(R.id.btn_viewmode);

		btn_imageload.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
				intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

				mCurrentFragmentIndex = FRAGMENT_IMAGELOAD;
				fragmentReplace(mCurrentFragmentIndex);
				frag_changed_flag = false;
			}
		});
		btn_result.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "button clicked");

				if (rawBitmap != null) {
					if (frag_changed_flag) {
						Main_Result main_result = (Main_Result) getSupportFragmentManager()
								.findFragmentById(R.id.ll_fragment_main);
						main_result.Result_toggle();
					} else {
						mCurrentFragmentIndex = FRAGMENT_RESULT;
						fragmentReplace(mCurrentFragmentIndex);
						frag_changed_flag = true;
					}

				}

			}
		});
		btn_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intentSubActivity = new Intent(JniIPActivity.this, Setting.class);
//				startActivity(intentSubActivity);
//				frag_changed_flag = false;
				
				Intent intentSubActivity = new Intent(JniIPActivity.this, MultipleChoiceListView.class);
				startActivity(intentSubActivity);
				//Intent intent = new Intent();
			    //intent.setClassName(this, "com.samsung.ip.MultipleChoiceListView");
			//    startActivity(intent);
			}
		});
		btn_realtime_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_SETTING;
				fragmentReplace(mCurrentFragmentIndex);
				frag_changed_flag = false;
			}
		});
		btn_viewmode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_VIEWMODE;
				fragmentReplace(mCurrentFragmentIndex);
				frag_changed_flag = false;
			}
		});

		imageload_frag = new Main_ImageLoad();
		result_frag = new Main_Result();
		realtime_frag = new Main_Realtime();
		viewmode_frag = new Main_Viewmode();

		mCurrentFragmentIndex = FRAGMENT_IMAGELOAD;
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
		transaction.replace(R.id.ll_fragment_main, newFragment);

		// Commit the transaction
		transaction.commit();
	}

	private Fragment getFragment(int idx) {
		Fragment newFragment = null;

		switch (idx) {
		case FRAGMENT_IMAGELOAD:
			newFragment = imageload_frag;
			break;
		case FRAGMENT_RESULT:
			newFragment = result_frag;
			break;
		case FRAGMENT_SETTING:
			newFragment = realtime_frag;
			break;
		case FRAGMENT_VIEWMODE:
			newFragment = viewmode_frag;
			break;

		default:
			Log.d(TAG, "Unhandle case");
			break;
		}

		return newFragment;
	}


	private String getRealPathFromURI(Uri contentUri) {
		
		String[] proj = { MediaStore.Images.Media.DATA };
		
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		
		int colume_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		
		cursor.moveToFirst();
		

		return cursor.getString(colume_index);

	}

	
	private int[] getScaledWindthHeight(int width, int heigth) {
		
		int viewHeight = 500; 
		float scaledWidth = width;
		float scaledHeigth = heigth;


		if (scaledHeigth > viewHeight) {
			
			float percente = (float) (scaledHeigth / 100);
			
			float scale = (float) (viewHeight / percente);
			
			scaledWidth *= (scale / 100);
			
			scaledHeigth *= (scale / 100);
		}
		
		return new int[] {(int)scaledWidth, (int)scaledHeigth};

	}
	private Matrix getRotatinoRatio(Uri data) throws IOException{
	
		ExifInterface exif = new ExifInterface(getRealPathFromURI(data));
		int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);

		Log.d("EXIF", "Exif: " + orientation);
		Matrix matrix = new Matrix();
		
		if (orientation == 6) {
			
			matrix.postRotate(90);
			
		} else if (orientation == 3) {
			
			matrix.postRotate(180);
			
		} else if (orientation == 8) {
			
			matrix.postRotate(270);
			
		}
		
		return matrix;
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQ_CODE_SELECT_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {
				try {

					rawBitmap = Images.Media.getBitmap(getContentResolver(), data.getData());

					
					Matrix matrix = getRotatinoRatio(data.getData());
					int imageInformation[] = getScaledWindthHeight(rawBitmap.getWidth(),rawBitmap.getHeight());
			
					scaledBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.getWidth(), rawBitmap.getHeight(),matrix, true);
					scaledBitmap = Bitmap.createScaledBitmap(scaledBitmap, imageInformation[0], imageInformation[1], true);



					image = (ImageView) findViewById(R.id.imageview1);
					
					mAttacher = new PhotoViewAttacher(image);
					mAttacher.setScaleType(ScaleType.FIT_CENTER);

					image.setImageBitmap(scaledBitmap);
					
					image_load_flag = true;
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	protected void onResume() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onResume();

	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		super.onPause();

	}

	private GLSurfaceView mGLSurfaceView;

	/** load irrlicht.so */
	static {
		Log.i("jnigl", "try to load libjnigl.so");
		System.loadLibrary("jnigl");
	}

	public static native int nativeGetOutputPixel(byte[] inputpixels, byte[] outputPixel, int width, int height);

	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
