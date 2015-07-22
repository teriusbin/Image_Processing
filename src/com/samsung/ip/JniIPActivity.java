package com.samsung.ip;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;

import com.samsung.ip.algorithm.AlgorithmData;
import com.samsung.ip.algorithm.AlgorithmEnableData;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class JniIPActivity extends Activity implements SensorEventListener {
	
	public String strPath = null;
	
	final int REQ_CODE_SELECT_IMAGE = 100;
	final int REQ_CODE_ALGO_SETTING = 101;
	final String TAG = "JniGLActivity";

	public final static int FRAGMENT_IMAGELOAD = 0;
	public final static int FRAGMENT_RESULT = 1;
	public final static int FRAGMENT_SETTING = 2;
	public final static int FRAGMENT_VIEWMODE = 3;

	public final static int MENU_IMAGE_LOAD = 0;
	public final static int MENU_IMAGE_SAVE = 1;
	public final static int MENU_ALGO_PARA_SETTING = 2;
	public final static int MENU_REGISTER_LOAD = 3;
	public final static int MENU_REGISTER_SAVE = 4;
	public final static int MENU_DEFAULT = 5;

	String monitorPath = "/sys/devices/virtual/sensors/light_sensor/raw_data";
	public ImageView image;

	public Bitmap rawBitmap;
	public Bitmap resultBitmap;

	PhotoViewAttacher mAttacher;
	public boolean image_result_flag = false;
	public boolean image_load_flag = false;
	public boolean frag_changed_flag = false;

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	public static TextView mLuxSensorL;
	public static TextView mLuxSensorR;
	public static TextView mLuxSensorG;
	public static TextView mLuxSensorB;

	public static float mluxL;
	public static String mluxR;
	public static String mluxG;
	public static String mluxB;
	
	public AlgorithmData algorithmData;
	public AlgorithmEnableData algorithmEnableData;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMenuList;
	private boolean mViewModeFlag = false;
	private boolean mResultFlag = false;

	SensorViewThread thread;

	SensorManager m_sensor_manager;
	Sensor m_light_sensor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_jni_gl);

		// realtime_frag = new Main_Realtime();

		m_sensor_manager = (SensorManager) getSystemService(SENSOR_SERVICE);
		m_light_sensor = m_sensor_manager.getDefaultSensor(Sensor.TYPE_LIGHT);

		mTitle = mDrawerTitle = getTitle();
		mMenuList = getResources().getStringArray(R.array.menu_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mLuxSensorL = (TextView) findViewById(R.id.sensor_luxL);
		mLuxSensorR = (TextView) findViewById(R.id.sensor_luxR);
		mLuxSensorG = (TextView) findViewById(R.id.sensor_luxG);
		mLuxSensorB = (TextView) findViewById(R.id.sensor_luxB);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setBackgroundDrawable(new ColorDrawable(0xFF0a84ad));
		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayShowTitleEnabled(false);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(null, GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mMenuList));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
				mDrawerLayout, /* DrawerLayout object */
				R.drawable.menu_icon, /*
										 * nav drawer image to replace 'Up'
										 * caret
										 */
				R.string.drawer_open, 0) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		// algorithm class 생성 
		algorithmData = new AlgorithmData();	
		algorithmEnableData = new AlgorithmEnableData();	
	
		String state = Environment.getExternalStorageState();
		if(state.equals(Environment.MEDIA_MOUNTED)){
			strPath = Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		// thread start
		thread = new SensorViewThread();
		thread.setDaemon(true);
		thread.start();

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

		return new int[] { (int) scaledWidth, (int) scaledHeigth };

	}

	private Matrix getRotatinoRatio(Uri data) throws IOException {

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

	private String getRealPathFromURI(Uri contentUri) {

		String[] proj = { MediaStore.Images.Media.DATA };

		Cursor cursor = managedQuery(contentUri, proj, null, null, null);

		int colume_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

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

					rawBitmap = Images.Media.getBitmap(getContentResolver(), data.getData());

					Matrix matrix = getRotatinoRatio(data.getData());

					rawBitmap = Bitmap.createBitmap(rawBitmap, 0, 0, rawBitmap.getWidth(), rawBitmap.getHeight(),
							matrix, true);

					image = (ImageView) findViewById(R.id.imageview1);

					mAttacher = new PhotoViewAttacher(image);
					mAttacher.setScaleType(ScaleType.FIT_CENTER);

					image.setImageBitmap(rawBitmap);

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
		if (requestCode == REQ_CODE_ALGO_SETTING) {
			if (resultCode == Activity.RESULT_OK) {
				Log.d(TAG, "catch intent");
						
				algorithmData = (AlgorithmData) data.getSerializableExtra("algorithmData");
				algorithmEnableData = (AlgorithmEnableData)data.getSerializableExtra("algorithmEnableData");
			}
		}
	}

	public void saveBitmaptoJpeg(Bitmap bitmap, String folder, String name) {

		String saveImg = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "testImage", "bitmap saved");

		Uri uri = Uri.parse(saveImg);

		sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

	}

	@Override
	protected void onResume() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		m_sensor_manager.registerListener(this, m_light_sensor, SensorManager.SENSOR_DELAY_UI);
		super.onResume();

	}

	@Override
	protected void onPause() {
		// Ideally a game should implement onResume() and onPause()
		// to take appropriate action when the activity looses focus
		m_sensor_manager.unregisterListener(this);
		super.onPause();

	}

	private GLSurfaceView mGLSurfaceView;
	private float c_lx_r;
	private float c_lx_g;
	private float c_lx_b;
	private int c_red;
	private int c_green;
	private int c_blue;

	/** load irrlicht.so */
	static {
		Log.i("jnigl", "try to load libjnigl.so");
		System.loadLibrary("jnigl");
	}

	public static native int nativeGetOutputPixel(byte[] inputpixels, byte[] outputPixel, AlgorithmData algorithmClass,
			int width, int height);

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {

		case R.id.action_viewmode:

			if (rawBitmap != null) {

				if (mViewModeFlag || mResultFlag) {
					// 2분할 화면
					Fragment viewmode_fragment = new Main_SlideView();
					FragmentManager viewmode_fragmentManager = getFragmentManager();
					viewmode_fragmentManager.beginTransaction().replace(R.id.content_frame, viewmode_fragment).commit();
					mViewModeFlag = false;
					mResultFlag = false;
				} else {
					// 1분할 화면
					Fragment imageload_fragment = new Main_View();
					FragmentManager imageload_fragmentManager = getFragmentManager();
					imageload_fragmentManager.beginTransaction().replace(R.id.content_frame, imageload_fragment)
							.commit();
					mViewModeFlag = true;
				}
			}

			return true;

		case R.id.action_result:
			// 영상 처리 결과 보여주기
			if (rawBitmap != null) {
				if (mResultFlag) {
					// 원본 영상
					Fragment imageload_fragment = new Main_View();
					FragmentManager imageload_fragmentManager = getFragmentManager();
					imageload_fragmentManager.beginTransaction().replace(R.id.content_frame, imageload_fragment)
							.commit();
					mResultFlag = false;
				} else {
					// 영상 처리 영상
					Fragment result_fragment = new Main_Result();
					FragmentManager result_fragmentManager = getFragmentManager();
					result_fragmentManager.beginTransaction().replace(R.id.content_frame, result_fragment).commit();
					mResultFlag = true;
				}

			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			try {
				selectItem(position);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private byte[] intToHex(Integer input) throws IOException{
		String test =new String();
	
		byte[] bytes = ByteBuffer.allocate(4).putInt(input).array();
		
		for(byte b : bytes){
			
			//Log.d("TEST", "Iterator"+" " +String.format("0x%02x,", b));
			writeToFile(String.format("0x%02x,", b));
		}
		return bytes;
	}
	private byte[] floatToHex(Float input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(4).putFloat(input).array();
		for(byte b : bytes){
			//Log.d("TEST", "Iterator"+" " +String.format("0x%02x", b));
			writeToFile(String.format("0x%02x,", b));
		}
		return bytes;
	}
	
	private byte[] doubleToHex(Double input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(8).putDouble(input).array();
		for(byte b : bytes){
			//Log.d("TEST", "Iterator"+" " +String.format("0x%02x", b));
			writeToFile(String.format("0x%02x,", b));
		}
		return bytes;
	}
	
	private byte[] byteToHex(Byte input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(1).put(input).array();
		for(byte b : bytes){
			//Log.d("TEST", "Iterator"+" " +String.format("0x%02x", b));
			writeToFile(String.format("0x%02x,", b));
		}
		return bytes;
	}
	
	private String hexToBinary(String input){
		
		Integer temp = Integer.parseInt(input, 16);
	    StringBuilder binaryStringBuilder = new StringBuilder();
	    
	    for(int i = 0; i < 8; i++)
	        binaryStringBuilder.append(((0x80 >>> i) & temp) == 0? '0':'1');
	    
	    return binaryStringBuilder.toString();
	}

	private void writeToFile(String data) throws IOException {
		
		File patternDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/mDNIe/myfile.dat");
	    patternDirectory.getParentFile().mkdirs();
	    FileOutputStream outputStream;
	    
	    try {
	    	outputStream = new FileOutputStream (new File(patternDirectory.getAbsolutePath().toString()), true);
	        outputStream.write(data.getBytes());
	        outputStream.write(String.format("\r\n").getBytes());
	        outputStream.close();
	      
	      } catch (Exception e) {
	        e.printStackTrace();
	      }

	}
	
	private void getParameterFromRegister(BufferedReader reader) throws IOException{
		
		String line  =null;
		String nextLine  =null;
		String binary = null;
		
		/**********************1*/
		/*x1*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_1 = Integer.parseInt(binary,2);
		Log.d(TAG, "test"+ "gorithmData.x_1" + " "+ algorithmData.x_1);
		
		/**********************2*/
		/*secret_1*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_1 =  Integer.parseInt(binary.substring(0,7),2);
		Log.d(TAG, "test"+ "algorithmData.secret_1" + " "+algorithmData.secret_1);
		
		/*x2*/
		algorithmData.x_2=  (binary.substring(7,8).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.x_2" + " "+algorithmData.x_2);
		
		/**********************3*/
		
		/*secret_2*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_2=  Integer.parseInt(binary.substring(0,2),2);
		Log.d(TAG, "test"+ "algorithmData.secret_2" + " "+algorithmData.secret_2);
		
		/*x3*/
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_3=  Integer.parseInt(binary.substring(2,4),2);
		Log.d(TAG, "test"+ "algorithmData.x_3" + " "+algorithmData.x_3);
		
		/*ASCR_MASK*/
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_MASK=  (binary.substring(4,5).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.ASCR_MASK" + " "+algorithmData.ASCR_MASK);
		
		/*x4*/
		algorithmData.x_4=  (binary.substring(5,6).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.x_4" + " "+algorithmData.x_4);
	
     	algorithmData.SLCE_MASK=  (binary.substring(6,7).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.SLCE_MASK" + " "+algorithmData.SLCE_MASK);
		
		/*x5*/
		algorithmData.x_5=  (binary.substring(7,8).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.x_5" + " "+algorithmData.x_5);
		
		/**********************4*/
		
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_3 = Integer.parseInt(binary.substring(0,2),2);
		Log.d(TAG, "test"+ "algorithmData.secret_3" + " "+algorithmData.secret_3);
		
		
		algorithmData.ASCR_BT=  (binary.substring(2,3).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.ASCR_BT" + " "+algorithmData.ASCR_BT);
		
	
		algorithmData.ASCR_MO=  (binary.substring(3,4).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.ASCR_MO" + " "+algorithmData.ASCR_MO);
		
		
		algorithmData.x_6 = (binary.substring(4,5).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.x_6 " + " "+algorithmData.x_6);

		
		algorithmData.x_7 = (binary.substring(5,6).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.x_7 " + " "+algorithmData.x_7);
		
		
		algorithmData.SLCE_BT = (binary.substring(6,7).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.SLCE_BT " + " "+algorithmData.SLCE_BT);
		
		
		algorithmData.SLCE_MO = (binary.substring(7,8).equals("1"));
		Log.d(TAG, "test"+ "aalgorithmData.SLCE_MO " + " "+ algorithmData.SLCE_MO);
		/**********************5*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.SLCE_ON=  (binary.substring(0,1).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.SLCE_ON" + " "+algorithmData.SLCE_ON);
	
		algorithmData.secret_4=  (binary.substring(1,2).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.secret_4" + " "+algorithmData.secret_4);
		
		algorithmData.SLCE_G = Integer.parseInt(binary.substring(3,8), 2);
		Log.d(TAG, "test"+ "algorithmData.SLCE_G" + " "+algorithmData.SLCE_G);
		
		/**********************6*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_5=  Integer.parseInt(binary.substring(0,2), 2);
		Log.d(TAG, "test"+ "algorithmData.secret_5" + " "+algorithmData.secret_5);
	
		algorithmData.SLCE_CG = Integer.parseInt(binary.substring(2,8), 2);
		Log.d(TAG, "test"+ "algorithmData.SLCE_CG" + " "+algorithmData.SLCE_CG);
		
		/**********************6*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_6=  Integer.parseInt(binary.substring(0,3), 2);
		Log.d(TAG, "test"+ "algorithmData.secret_6" + " "+algorithmData.secret_6);
		
		algorithmData.SLCE_SO=  (binary.substring(3,3).equals("1"));
		Log.d(TAG, "test"+ "algorithmData.SLCE_SO" + " "+algorithmData.SLCE_SO);
		
		algorithmData.SLCE_SR=  Integer.parseInt(binary.substring(4,8), 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_SR" + " "+	algorithmData.SLCE_SR);
		
		/**********************7*/
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_7=  binary.substring(0,1).equals("1");
		Log.d(TAG, "test"+ "algorithmData.secret_7" + " "+algorithmData.secret_7);
		
		algorithmData.SLCE_SMD=  Integer.parseInt(binary.substring(1,8), 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_SMD" + " "+	algorithmData.SLCE_SMD);
		
		/**********************8*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.SLCE_IG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_IG" + " "+	algorithmData.SLCE_IG);
		
		/**********************9~10*/
		line = reader.readLine();
		nextLine = reader.readLine();
		StringBuilder stringBuilder = new StringBuilder();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_8=  Integer.parseInt(binary.substring(0, 7), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_8" + " "+	algorithmData.secret_8);
		
		stringBuilder.append(binary.substring(7,8));
		stringBuilder.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString = stringBuilder.toString();
		algorithmData.SLCE_RO=Integer.parseInt(finalString, 2);
		Log.d(TAG, "test"+ "algorithmData.SLCE_RO" + " "+algorithmData.SLCE_RO);
		
		/**********************11*/
		line = reader.readLine();
		//StringBuilder stringBuilder = new StringBuilder();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_9=  Integer.parseInt(binary.substring(0, 7), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_9" + " "+	algorithmData.secret_9);
		
		algorithmData.SLCE_RG=  binary.substring(7,8).equals("1");
		Log.d(TAG, "test"+ "algorithmData.SLCE_RG" + " "+algorithmData.SLCE_RG);
		
		/**********************11*/
		line = reader.readLine();
			
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.SLCE_REF_GAIN=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_REF_GAIN" + " "+	algorithmData.SLCE_REF_GAIN);
		
		/**********************12*/
		line = reader.readLine();
			
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_10= binary.substring(0,1).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.secret_10" + " "+	algorithmData.secret_10);
		
		algorithmData.SLCE_H=  Integer.parseInt(binary.substring(2, 5), 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_H" + " "+	algorithmData.SLCE_H);
		
		algorithmData.secret_11= binary.substring(4,5).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.secret_11" + " "+	algorithmData.secret_11);
		
		algorithmData.SLCE_V=  Integer.parseInt(binary.substring(4, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_V" + " "+	algorithmData.SLCE_V);
		
		/**********************13*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.SLCE_BTH=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_BTH" + " "+	algorithmData.SLCE_BTH);
		
		/**********************14*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_12 = binary.substring(0,1).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.secret_12" + " "+	algorithmData.secret_12);
		
		algorithmData.SLCE_BSR=  Integer.parseInt(binary.substring(1,8), 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_BSR" + " "+	algorithmData.SLCE_BSR);
		
		/**********************15*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_13=  Integer.parseInt(binary.substring(0,6), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_13" + " "+	algorithmData.secret_13);
		
		algorithmData.SLCE_DTH=  Integer.parseInt(binary.substring(6,8), 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_DTH" + " "+	algorithmData.SLCE_DTH);
		
		/**********************16*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.SLCE_MRO=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.SLCE_MRO" + " "+	algorithmData.SLCE_MRO);
		
		/**********************17*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_14=  Integer.parseInt(binary.substring(0, 3), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_14" + " "+	algorithmData.secret_14);
		
		algorithmData.NR_E = binary.substring(3,4).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.NR_E" + " "+	algorithmData.secret_12);
		
		algorithmData.FE_E = binary.substring(4,5).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.FE_E" + " "+	algorithmData.secret_12);
		
		algorithmData.DE_E = binary.substring(5,6).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.DE_E" + " "+	algorithmData.DE_E);
		
		algorithmData.CE_E = binary.substring(6,7).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.CE_E" + " "+	algorithmData.CE_E);
		
		
		algorithmData.GS_E= binary.substring(7,8).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.GS_E" + " "+	algorithmData.GS_E);
		
		/**********************17*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_8=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_8" + " "+	algorithmData.x_8);
		
		/**********************18~19*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_15=  Integer.parseInt(binary.substring(0, 6), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_15" + " "+	algorithmData.secret_15);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder2 = new StringBuilder();
	
		stringBuilder2.append(binary.substring(6,8));
		stringBuilder2.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString2 = stringBuilder2.toString();
		algorithmData.DE_G=Integer.parseInt(finalString2, 2);
	
		Log.d(TAG, "test"+ "algorithmData.DE_G" + " "+algorithmData.DE_G);
		
		/**********************20~21*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_16=  Integer.parseInt(binary.substring(0, 5), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_16" + " "+	algorithmData.secret_16);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder3 = new StringBuilder();
	
		stringBuilder3.append(binary.substring(5,8));
		stringBuilder3.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString3 = stringBuilder3.toString();
		algorithmData.DE_M1=Integer.parseInt(finalString3, 2);
	
		Log.d(TAG, "test"+ "algorithmData.DE_M1" + " "+algorithmData.DE_M1);
		
		/**********************22~23*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_17=  Integer.parseInt(binary.substring(0, 5), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_17" + " "+	algorithmData.secret_17);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder4 = new StringBuilder();
	
		stringBuilder4.append(binary.substring(5,8));
		stringBuilder4.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString4 = stringBuilder4.toString();
		algorithmData.DE_M2=Integer.parseInt(finalString4, 2);
	
		Log.d(TAG, "test"+ "algorithmData.DE_M2" + " "+algorithmData.DE_M2);
		
		/**********************24*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.FA_ET=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_ET" + " "+	algorithmData.FA_ET);
		
		/**********************25~26*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_18=  Integer.parseInt(binary.substring(0, 3), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_18" + " "+	algorithmData.secret_18);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder5 = new StringBuilder();
		stringBuilder5.append(binary.substring(3, 8));
		stringBuilder5.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString5 = stringBuilder5.toString();
		algorithmData.FA_SP=Integer.parseInt(finalString5, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_SP" + " "+algorithmData.FA_SP);
		
		/**********************27~28*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_19=  Integer.parseInt(binary.substring(0, 3), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_19" + " "+	algorithmData.secret_19);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder6 = new StringBuilder();
		stringBuilder6.append(binary.substring(3, 8));
		stringBuilder6.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString6 = stringBuilder6.toString();
		algorithmData.FA_SN=Integer.parseInt(finalString6, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_SN" + " "+algorithmData.FA_SN);
		
		/**********************29~30*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_20=  Integer.parseInt(binary.substring(0, 3), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_20" + " "+	algorithmData.secret_20);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder7 = new StringBuilder();
		stringBuilder7.append(binary.substring(3, 8));
		stringBuilder7.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString7 = stringBuilder7.toString();
		algorithmData.FA_MDG=Integer.parseInt(finalString7, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_MDG" + " "+algorithmData.FA_MDG);
		
		/**********************30~31*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_21=  Integer.parseInt(binary.substring(0, 2), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_21" + " "+	algorithmData.secret_21);
		
		nextLine = reader.readLine();
		StringBuilder stringBuilder8 = new StringBuilder();
		stringBuilder8.append(binary.substring(2, 8));
		stringBuilder8.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString8 = stringBuilder8.toString();
		algorithmData.FA_PP=Integer.parseInt(finalString8, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_PP" + " "+algorithmData.FA_PP);
		
		/**********************32~33*/
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		nextLine = reader.readLine();
		StringBuilder stringBuilder9 = new StringBuilder();
		stringBuilder9.append(binary);
		stringBuilder9.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString9 = stringBuilder9.toString();
		algorithmData.FA_SC=Integer.parseInt(finalString9, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_SC" + " "+algorithmData.FA_SC);
		
		/**********************34~37*/
		StringBuilder stringBuilder10 = new StringBuilder();
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder10.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder10.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder10.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder10.append(binary);
		String finalString10 = stringBuilder10.toString();
		algorithmData.FA_D=Integer.parseInt(finalString10, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_D" + " "+algorithmData.FA_D);
		
		/**********************38~45*/
		StringBuilder stringBuilder11 = new StringBuilder();
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		stringBuilder11.append(binary);
	
		String finalString11 = stringBuilder11.toString();
		algorithmData.FA_DD=Long.parseLong(finalString11, 2);
		Log.d(TAG, "test"+ "algorithmData.FA_DD" + " "+algorithmData.FA_DD);
		
		/**********************46*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_22=  Integer.parseInt(binary.substring(0, 2), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_22" + " "+	algorithmData.secret_22);
		
		algorithmData.FA_PMW=  Integer.parseInt(binary.substring(2, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_PMW" + " "+	algorithmData.FA_PMW);
		
		/**********************47*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_23=  Integer.parseInt(binary.substring(0, 2), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_23" + " "+	algorithmData.secret_23);
		
		algorithmData.FA_FMW=  Integer.parseInt(binary.substring(2, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_FMW" + " "+	algorithmData.FA_FMW);
		
		/**********************47*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_24=  Integer.parseInt(binary.substring(0, 4), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_24" + " "+	algorithmData.secret_24);
		
		algorithmData.FA_SZW=  Integer.parseInt(binary.substring(4, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_SZW" + " "+	algorithmData.FA_SZW);
		
		/**********************48*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_25=  Integer.parseInt(binary.substring(0, 4), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_25" + " "+	algorithmData.secret_25);
		
		algorithmData.FA_SZH=  Integer.parseInt(binary.substring(4, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_SZH" + " "+	algorithmData.FA_SZH);
		
		/**********************49*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_26=  Integer.parseInt(binary.substring(0, 2), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_25" + " "+	algorithmData.secret_25);
		
		algorithmData.FA_OC10C=  Integer.parseInt(binary.substring(2, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_OC10C" + " "+	algorithmData.FA_OC10C);
		
		/**********************50*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_27=  Integer.parseInt(binary.substring(0, 2), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_27" + " "+	algorithmData.secret_27);
		
		algorithmData.FA_OC20C=  Integer.parseInt(binary.substring(2, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.FA_OC20C" + " "+	algorithmData.FA_OC20C);
		
		/**********************51*/
		line = reader.readLine();
		
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_28=  Integer.parseInt(binary.substring(0, 6), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_28" + " "+	algorithmData.secret_28);
		
		algorithmData.x_9=  Integer.parseInt(binary.substring(6, 8), 2);
		Log.d(TAG, "test"+ "	algorithmData.x_9" + " "+	algorithmData.x_9);
		
		/**********************52~101*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_10=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_10" + " "+	algorithmData.x_10);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_11=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_11" + " "+	algorithmData.x_11);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_12=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_12" + " "+	algorithmData.x_12);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_13=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_13" + " "+	algorithmData.x_13);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_14=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_14" + " "+	algorithmData.x_14);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_15=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_15" + " "+	algorithmData.x_15);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_16=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_16" + " "+	algorithmData.x_16);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_17=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_17" + " "+	algorithmData.x_17);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_18=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_18" + " "+	algorithmData.x_18);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_19=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_19" + " "+	algorithmData.x_19);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_20=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_20" + " "+	algorithmData.x_20);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_21=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_21" + " "+	algorithmData.x_21);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_22=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_22" + " "+	algorithmData.x_22);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_23=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_23" + " "+	algorithmData.x_23);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_24=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_24" + " "+	algorithmData.x_24);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_25=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_25" + " "+	algorithmData.x_25);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_26=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_26" + " "+	algorithmData.x_26);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_27=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_27" + " "+	algorithmData.x_27);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_28=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_28" + " "+	algorithmData.x_28);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_29=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_29" + " "+	algorithmData.x_29);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_30=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_30" + " "+	algorithmData.x_30);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_31=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_31" + " "+	algorithmData.x_31);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_32=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_32" + " "+	algorithmData.x_32);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_33=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_33" + " "+	algorithmData.x_33);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_34=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_34" + " "+	algorithmData.x_34);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_35=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_35" + " "+	algorithmData.x_35);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_36=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_36" + " "+	algorithmData.x_36);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_10=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_10" + " "+	algorithmData.x_10);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_37=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_37" + " "+	algorithmData.x_37);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_38=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_38" + " "+	algorithmData.x_38);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_39=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_39" + " "+	algorithmData.x_39);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_40=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_40" + " "+	algorithmData.x_40);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_41=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_41" + " "+	algorithmData.x_41);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_42=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_42" + " "+	algorithmData.x_42);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_43=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_43" + " "+	algorithmData.x_43);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_44=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_44" + " "+	algorithmData.x_44);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_45=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_45" + " "+	algorithmData.x_45);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_46=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_46" + " "+	algorithmData.x_46);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_47=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_47" + " "+	algorithmData.x_47);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_48=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_48" + " "+	algorithmData.x_48);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_49=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_49" + " "+	algorithmData.x_49);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_50=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_50" + " "+	algorithmData.x_50);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_51=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_51" + " "+	algorithmData.x_51);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_52=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_52" + " "+	algorithmData.x_52);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_53=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_53" + " "+	algorithmData.x_53);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_54=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_54" + " "+	algorithmData.x_54);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_55=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_55" + " "+	algorithmData.x_55);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_56=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_56" + " "+	algorithmData.x_56);
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.x_57=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.x_57" + " "+	algorithmData.x_57);
		
		/**********************102*/
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
	
		algorithmData.secret_29  = binary.substring(0,1).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.secret_29" + " "+	algorithmData.secret_29);
		algorithmData.ASCR_AO  = binary.substring(1,2).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.ASCR_AO" + " "+	algorithmData.ASCR_AO);
		algorithmData.ASCR_LO  = binary.substring(2,3).equals("1");
		Log.d(TAG, "test"+ "	algorithmData.ASCR_LO" + " "+	algorithmData.ASCR_LO);
		algorithmData.ASCR_S=  Integer.parseInt(binary.substring(3,8), 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_S" + " "+	algorithmData.ASCR_S);
		
		/**********************103*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SCB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SCB" + " "+	algorithmData.ASCR_SCB);
		
		/**********************104*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SCR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SCR" + " "+	algorithmData.ASCR_SCR);
		
		/**********************105*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_DU=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DU" + " "+	algorithmData.ASCR_DU);
		
		/**********************106*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_DD=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DD" + " "+	algorithmData.ASCR_DD);
		
		/**********************107*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_DR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DR" + " "+	algorithmData.ASCR_DR);
		
		/**********************108*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_DL=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DL" + " "+	algorithmData.ASCR_DL);
		
		/**********************109*/
		StringBuilder stringBuilder12 = new StringBuilder();
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_30=  Integer.parseInt(binary.substring(0,4), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_30" + " "+	algorithmData.secret_30);
		
		stringBuilder12.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder12.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder12.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString12 = stringBuilder12.toString();
		algorithmData.ASCR_DDU=Integer.parseInt(finalString12, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DDU" + " "+	algorithmData.ASCR_DDU);
	
		/**********************110*/
		StringBuilder stringBuilder13 = new StringBuilder();
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_31=  Integer.parseInt(binary.substring(0,4), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_31" + " "+	algorithmData.secret_31);
		
		stringBuilder13.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder13.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder12.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString13 = stringBuilder13.toString();
		algorithmData.ASCR_DDD=Integer.parseInt(finalString13, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DDD" + " "+	algorithmData.ASCR_DDD);
		
		/**********************111*/
		StringBuilder stringBuilder14 = new StringBuilder();
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_32=  Integer.parseInt(binary.substring(0,4), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_32" + " "+	algorithmData.secret_32);
		
		stringBuilder14.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder14.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder14.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString14 = stringBuilder14.toString();
		algorithmData.ASCR_DDR=Integer.parseInt(finalString14, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DDR" + " "+	algorithmData.ASCR_DDR);
		
		/**********************112*/
		StringBuilder stringBuilder15 = new StringBuilder();
		
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.secret_33=  Integer.parseInt(binary.substring(0,4), 2);
		Log.d(TAG, "test"+ "	algorithmData.secret_33" + " "+	algorithmData.secret_33);
		
		stringBuilder15.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder15.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder15.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString15 = stringBuilder15.toString();
		algorithmData.ASCR_DDL=Integer.parseInt(finalString15, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_DDL" + " "+	algorithmData.ASCR_DDL);
		
		/**********************113*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SRR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SRR" + " "+	algorithmData.ASCR_SRR);
		
		/**********************114*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SRG" + " "+	binary);
		algorithmData.ASCR_SRG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SRG" + " "+	algorithmData.ASCR_SRG);
		
		/**********************115*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SRB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SRB" + " "+	algorithmData.ASCR_SRB);
		
		/**********************116*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SYR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SYR" + " "+	algorithmData.ASCR_SYR);
		
		/**********************117*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SYG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SYG" + " "+	algorithmData.ASCR_SYG);
		
		/**********************118*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SYB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SYB" + " "+	algorithmData.ASCR_SYB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SMR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SMR" + " "+	algorithmData.ASCR_SMR);
		
		/**********************120*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SMG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SMG" + " "+	algorithmData.ASCR_SMG);
		
		/**********************121*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SMB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SMB" + " "+	algorithmData.ASCR_SMB);
		
		/**********************122*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SWR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SWR" + " "+	algorithmData.ASCR_SWR);
		
		/**********************123*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SWG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SWG" + " "+	algorithmData.ASCR_SWG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_SWB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_SWG" + " "+	algorithmData.ASCR_SWG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WCR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WCR" + " "+	algorithmData.ASCR_WCR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WRR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WRR" + " "+	algorithmData.ASCR_WRR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WCG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WCG" + " "+	algorithmData.ASCR_WCG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WRG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WRG" + " "+	algorithmData.ASCR_WRG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WCB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WCB" + " "+	algorithmData.ASCR_WCB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WRB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WRB" + " "+	algorithmData.ASCR_WRB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WMR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WMR" + " "+	algorithmData.ASCR_WMR);
		
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WGR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WGR" + " "+	algorithmData.ASCR_WGR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WMG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WMG" + " "+	algorithmData.ASCR_WMG);
		
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WGG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WGG" + " "+	algorithmData.ASCR_WGG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WMB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WMB" + " "+	algorithmData.ASCR_WMB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WGB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WGB" + " "+	algorithmData.ASCR_WGB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WYR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WYR" + " "+	algorithmData.ASCR_WYR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WBR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WBR" + " "+	algorithmData.ASCR_WBR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WYG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WYG" + " "+	algorithmData.ASCR_WYG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WBG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WBG" + " "+	algorithmData.ASCR_WBG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WYB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WYB" + " "+	algorithmData.ASCR_WYB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WBB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WBB" + " "+	algorithmData.ASCR_WBB);
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WWR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WWR" + " "+	algorithmData.ASCR_WWR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WKR=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WKR" + " "+	algorithmData.ASCR_WKR);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WWG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WWG" + " "+	algorithmData.ASCR_WWG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WKG=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WKG" + " "+	algorithmData.ASCR_WKG);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WWB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WWB" + " "+	algorithmData.ASCR_WWB);
		
		/**********************119*/
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		algorithmData.ASCR_WKB=  Integer.parseInt(binary, 2);
		Log.d(TAG, "test"+ "	algorithmData.ASCR_WKB" + " "+	algorithmData.ASCR_WKB);
		
	
	}
	
	private void readToFile() throws IOException {
		
		FileInputStream iStream=null;
		BufferedReader reader=null;
		
	    try {
	    
		    	File dir = Environment.getExternalStorageDirectory();
		    	File yourFile = new File(dir, "mDNIe/register.dat");
		    	
		    	iStream =  new FileInputStream(Environment.getExternalStorageDirectory().getAbsolutePath()+"/mDNIe/RegisterData.dat");
				reader = new BufferedReader(new InputStreamReader(iStream));	
			
				getParameterFromRegister(reader);
				
	      } catch (FileNotFoundException ex) {
	          
	        } catch (IOException ex) {
	        
	          
	        }finally {
	            try {
	                reader.close();
	                iStream.close();
	            } catch (IOException ex) {
	              
	            }
	        }



	}
	private void selectItem(int position) throws IOException {
		Log.d(TAG, "position = " + position);
		// update the main content by replacing fragments
		switch (position) {

		case MENU_DEFAULT:
			/*
			 * default 어플 시작 시 기본 화면 띄우기 position = 5
			 */

			break;

		case MENU_IMAGE_LOAD:
			/*
			 * 갤러리에서 이미지 로드 position = 0
			 */
			resultBitmap = null;
			mResultFlag = false;
			mViewModeFlag = false;
			Fragment imageload_fragment = new Main_View();
			FragmentManager imageload_fragmentManager = getFragmentManager();
			imageload_fragmentManager.beginTransaction().replace(R.id.content_frame, imageload_fragment).commit();
			Intent intent = new Intent(Intent.ACTION_PICK);

			intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
			intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);

			// DrawerList.setItemChecked(position, true);
			setTitle(mMenuList[position]);
			mDrawerLayout.closeDrawer(mDrawerList);

			break;

		case MENU_IMAGE_SAVE:
			/*
			 * 갤러리로 영상처리 이미지 저장 position = 1
			 */
			if (resultBitmap != null) {
				saveBitmaptoJpeg(resultBitmap, "mDNIe", "save_test");
			}
			mDrawerLayout.closeDrawer(mDrawerList);

			break;

		case MENU_ALGO_PARA_SETTING:
			/*
			 * 알고리즘 파라미터 셋팅 창으로 이동 position = 2
			 */
			
			Intent intentSubActivity = new Intent(JniIPActivity.this, AlgorithmSettingListView.class);
		    intentSubActivity.putExtra("algorithmData", algorithmData);
		    intentSubActivity.putExtra("algorithmEnableData", algorithmEnableData);
			startActivityForResult(intentSubActivity, REQ_CODE_ALGO_SETTING);
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_REGISTER_LOAD:
			readToFile();
			/*
			 * Register 설정 로드 position = 3
			 */
			
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_REGISTER_SAVE:
			
			int listLength = algorithmData.algorithmList.size();
			
			for(int i = 0; i<listLength; i++){
				
				for(String keys:algorithmData.algorithmList.get(i).keySet()){
					
					//Log.d("TEST", "Iterator"+" " + String.format("키 : %s, 값 : %s", keys, algorithmData.algorithmList.get(i).get(keys)));
					if(algorithmData.algorithmList.get(i).get(keys) instanceof Integer){
						
						intToHex((Integer)algorithmData.algorithmList.get(i).get(keys));
						
					}else if(algorithmData.algorithmList.get(i).get(keys) instanceof Float){
						
					    floatToHex((Float)algorithmData.algorithmList.get(i).get(keys));
					    
					}else if(algorithmData.algorithmList.get(i).get(keys) instanceof Double){
						
						doubleToHex((Double)algorithmData.algorithmList.get(i).get(keys));
						
					}else if(algorithmData.algorithmList.get(i).get(keys) instanceof Byte){
					
						byteToHex((Byte)algorithmData.algorithmList.get(i).get(keys));
						
					}
	
			}
			//Log.d("TEST","-------------------------------");
			}
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		}

	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		// getActionBar().setTitle(mTitle);
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	/**
	 * Fragment
	 */

	public static class Main_View extends Fragment {

		public ImageView loaded_image;
		PhotoViewAttacher loadedAttacher;

		public static final String ARG_PLANET_NUMBER = "planet_number";

		public Main_View() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.main_imageload_frag, container, false);

			return rootView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);

			loaded_image = (ImageView) getView().findViewById(R.id.imageview1);
			loadedAttacher = new PhotoViewAttacher(loaded_image);

			loadedAttacher.setScaleType(ScaleType.FIT_CENTER);
			loaded_image.setImageBitmap(((JniIPActivity) getActivity()).rawBitmap);

			mLuxSensorL = (TextView) getView().findViewById(R.id.sensor_luxL);
			mLuxSensorR = (TextView) getView().findViewById(R.id.sensor_luxR);
			mLuxSensorG = (TextView) getView().findViewById(R.id.sensor_luxG);
			mLuxSensorB = (TextView) getView().findViewById(R.id.sensor_luxB);
		}

	}

	public static class Main_SlideView extends Fragment {

		public final static String TAG = "Main_Viewmode";
		public ImageView Previmage;
		public ImageView Resultimage;

		static Bitmap PrevBitmap;
		static Bitmap ResultBitmap;
		PhotoViewAttacher prevAttacher;
		PhotoViewAttacher resultAttacher;

		public Main_SlideView newInstance() {
			Main_SlideView fragment = new Main_SlideView();
			return fragment;
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			View v = inflater.inflate(R.layout.main_viemode_frag, container, false);

			return v;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);

			Previmage = (ImageView) getView().findViewById(R.id.viewmode_prev);
			Resultimage = (ImageView) getView().findViewById(R.id.viewmode_result);
			prevAttacher = new PhotoViewAttacher(Previmage);

			resultAttacher = new PhotoViewAttacher(Resultimage);
			Previmage.setImageBitmap(((JniIPActivity) getActivity()).rawBitmap);
			Resultimage.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);

			mLuxSensorL = (TextView) getView().findViewById(R.id.sensor_luxL);
			mLuxSensorR = (TextView) getView().findViewById(R.id.sensor_luxR);
			mLuxSensorG = (TextView) getView().findViewById(R.id.sensor_luxG);
			mLuxSensorB = (TextView) getView().findViewById(R.id.sensor_luxB);

		}
	}

	public class Main_Result extends Fragment {

		public final static String TAG = "Main_Result";

		public ImageView resultImage;
		private ImageView mRealtimeHandle;

		PhotoViewAttacher mAttacher;		

		public Main_Result newInstance() {
			Main_Result fragment = new Main_Result();
			return fragment;
		}

		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			View v = inflater.inflate(R.layout.main_result_frag, container, false);
			return v;
		}

		public byte[] getInputPixel(Bitmap input) {

			byte[] resultPixel;
			int bytes = input.getByteCount();

			ByteBuffer inputBuffer = ByteBuffer.allocate(bytes);
			input.copyPixelsToBuffer(inputBuffer);

			resultPixel = inputBuffer.array();

			return resultPixel;
		}

		public Bitmap getOutputBitmap(int length, byte[] outputPixel) {

			Bitmap resultBitmap;
			ByteBuffer outBuffer = ByteBuffer.allocate(length);

			outBuffer.put(outputPixel, 0, length);
			outBuffer.rewind();

			resultBitmap = Bitmap.createBitmap(rawBitmap.getWidth(), rawBitmap.getHeight(),
					Bitmap.Config.ARGB_8888);
			resultBitmap.copyPixelsFromBuffer(outBuffer);

			return resultBitmap;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			mRealtimeHandle = (ImageView) findViewById(R.id.result_handle);
			

			byte[] inputPixel = getInputPixel(((JniIPActivity) getActivity()).rawBitmap);
			byte[] outputPixel = new byte[rawBitmap.getByteCount()];

			//AlgorithmData algorithmClass = new AlgorithmData();
			
		   // HashMap<String, Object> modify=new HashMap<String , Object>();
		    //Integer temp = 90;
		    //algorithmClass.algorithmList.get(0).put("NR_param1", temp); //수정 하고자 할때
			
		     if (resultBitmap == null) {
				int length = JniIPActivity.nativeGetOutputPixel(inputPixel, outputPixel, algorithmData,
						rawBitmap.getWidth(), rawBitmap.getHeight());
				((JniIPActivity) getActivity()).resultBitmap = getOutputBitmap(length, outputPixel);
				
			}

			resultImage = (ImageView) getView().findViewById(R.id.imageview2);
			mAttacher = new PhotoViewAttacher(resultImage);

			resultImage.setImageBitmap(resultBitmap);
			((JniIPActivity) getActivity()).image_load_flag = true;

			mLuxSensorL = (TextView) getView().findViewById(R.id.sensor_luxL);
			mLuxSensorR = (TextView) getView().findViewById(R.id.sensor_luxR);
			mLuxSensorG = (TextView) getView().findViewById(R.id.sensor_luxG);
			mLuxSensorB = (TextView) getView().findViewById(R.id.sensor_luxB);

		}

		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			resultImage.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);

		}

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub

		if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE) {

		}

		if (event.sensor.getType() == Sensor.TYPE_LIGHT) {

			File data = new File(this.monitorPath);

			try {

				BufferedReader inputData = new BufferedReader(new FileReader(data));

				String rgnstr = inputData.readLine();
				inputData.close();

				String[] result = rgnstr.split(",");

				mluxL = event.values[0];
				mluxR = result[0];
				mluxG = result[1];
				mluxB = result[2];

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	/*
	 * Thread
	 */
	class SensorViewThread extends Thread {
		public void run() {
			while (true) {

				mHandler.post(new Runnable() {
					public void run() {
						mLuxSensorL.setText(mluxL + ": LuxSensorL ");
						mLuxSensorR.setText(mluxR + ": LuxSensorR ");
						mLuxSensorG.setText(mluxG + ": LuxSensorG ");
						mLuxSensorB.setText(mluxB + ": LuxSensorB ");
						// Log.d(TAG, "thread process");
					}

				});
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					;
				}
			}
		}
	}

	Handler mHandler = new Handler();	

}
