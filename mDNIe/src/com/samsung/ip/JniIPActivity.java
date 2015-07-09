package com.samsung.ip;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.samsung.ip.R;

import android.app.Activity;
import android.content.Context;
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
import android.view.MotionEvent;
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
	public boolean buttonTouch;
	private Bitmap rawBitmap;
	
	PhotoViewAttacher mAttacher;


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
			}
		});
		btn_result.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_RESULT;
				fragmentReplace(mCurrentFragmentIndex);
			}
		});
		btn_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intentSubActivity = new Intent(JniIPActivity.this, Setting.class);
				startActivity(intentSubActivity);
			}
		});
		btn_realtime_setting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_SETTING;
				fragmentReplace(mCurrentFragmentIndex);
			}
		});
		btn_viewmode.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mCurrentFragmentIndex = FRAGMENT_VIEWMODE;
				fragmentReplace(mCurrentFragmentIndex);
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

	public String getImageNameToUri(Uri data) {
		String[] proj = { MediaStore.Images.Media.DATA };
		Cursor cursor = managedQuery(data, proj, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		cursor.moveToFirst();

		String imgPath = cursor.getString(column_index);
		String imgName = imgPath.substring(imgPath.lastIndexOf("/") + 1);

		return imgName;
	}
	
	public String getRealPathFromURI(Uri contentUri){
		String[] proj = {MediaStore.Images.Media.DATA};
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int colume_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(colume_index);
		
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == REQ_CODE_SELECT_IMAGE) {
			if (resultCode == Activity.RESULT_OK) {
				try {
					
					
					
					// 이미지 데이터를 비트맵으로 받아온다.
					rawBitmap = Images.Media.getBitmap(getContentResolver(), data.getData());
					
					ExifInterface exif = new ExifInterface(getRealPathFromURI(data.getData()));
					int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
					
					 Log.d("EXIF", "Exif: " + orientation);
		                Matrix matrix = new Matrix();
		                if (orientation == 6) {
		                    matrix.postRotate(90);
		                }
		                else if (orientation == 3) {
		                    matrix.postRotate(180);
		                }
		                else if (orientation == 8) {
		                    matrix.postRotate(270);
		                }
		                
		                int viewHeight = 500;   //height 값으로 해상도 조절
						
						float width = rawBitmap.getWidth();
			            float height =rawBitmap.getHeight();
			            
			            if(height > viewHeight)
			            {
			                  float percente = (float)(height/100);
			                  float scale = (float)(viewHeight/percente);
			                  width *= (scale/100);
			                  height *= (scale/100);                  
			             }
			            
			            //scaledBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.getWidth(),rawBitmap.getHeight(), matrix, true);
			           scaledBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, (int)width,(int)height, matrix, true);
		                
		                Log.d(TAG, "debug 1  "+scaledBitmap.getWidth());
						Log.d(TAG, "debug 1  "+scaledBitmap.getHeight());
						
			            image = (ImageView) findViewById(R.id.imageview1);
						mAttacher = new PhotoViewAttacher(image);
					        // 3.화면에 꽉차는 옵션 (선택사항)
					    mAttacher.setScaleType(ScaleType.FIT_CENTER);
					    
		            
					/*
					int rotatedWidth, rotatedHeight;
					int orientation = getOrientation(this, data.getData());
					
					
					Log.d(TAG, "debug 1  "+rawBitmap.getWidth());
					Log.d(TAG, "debug 1  "+rawBitmap.getHeight());
					
					if (orientation == 90 || orientation == 270) {
					        rotatedWidth = rawBitmap.getHeight();
					        rotatedHeight = rawBitmap.getWidth();
					    } else {
					        rotatedWidth = rawBitmap.getWidth();
					        rotatedHeight = rawBitmap.getHeight();
					    }
*/
					
					//Bitmap.createScaledBitmap(scaledBitmap, scaledBitmap.getWidth()/4, scaledBitmap.getHeight()/4, false);
					/*int width = rawBitmap.getWidth(); 
			        int height = rawBitmap.getHeight(); 
			        int newWidth = 200; 
			        int newHeight = 200; 
			        
			        float scaleWidth = ((float) newWidth) / width; 
			        float scaleHeight = ((float) newHeight) / height; 
			        
			        Matrix matrix = new Matrix(); 
			        // resize the bit map 
			        matrix.postScale(scaleWidth, scaleHeight); 
			        // rotate the Bitmap 
			        matrix.postRotate(45); 
			        
			        Bitmap resizedBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, 
	                          width, height, matrix, true); 
	    
			        // make a Drawable from Bitmap to allow to set the BitMap 
			        // to the ImageView, ImageButton or what ever 
			        BitmapDrawable bmd = new BitmapDrawable(resizedBitmap); 
			        
			        ImageView imageView = new ImageView(this); 
			        
			        // set the Drawable on the ImageView 
			        imageView.setImageDrawable(bmd); 
			      
			        // center the Image 
			        imageView.setScaleType(ImageView.ScaleType.CENTER); */
					/*if(rawBitmap.getHeight() < rawBitmap.getWidth()){
					    	
						rawBitmap = imgRotate(rawBitmap);
					    	
					 }
					   */
					   
					
				    
				 
				    
					image.setImageBitmap(scaledBitmap);

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
	
	private Bitmap imgRotate(Bitmap bmp){
		int width = bmp.getWidth(); 
		int height = bmp.getHeight(); 

		Matrix matrix = new Matrix(); 
		matrix.postRotate(90); 

		Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0, width, height, matrix, true); 
		bmp.recycle();

		return resizedBitmap;
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

