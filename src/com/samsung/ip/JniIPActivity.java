package com.samsung.ip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import com.samsung.ip.algorithm.AlgorithmData;
import com.samsung.ip.algorithm.AlgorithmEnableData;
import com.samsung.ip.algorithm.AlgorithmMultiAdapter;
import com.samsung.ip.algorithm.AlgorithmMultiItem;
import com.samsung.ip.algorithm.AppPreference;
import com.samsung.ip.filelist.Main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class JniIPActivity extends Activity implements SensorEventListener {

	final int REQ_CODE_SELECT_IMAGE = 100;
	final int REQ_CODE_ALGO_SETTING = 101;
	final int REQ_CODE_FILE_SETTING = 102;
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

	private int saveCount=0;
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

		// string.xml 파일에 알고리즘 이름과 파라미터 이름을 참조하여 자동으로 클래스와 해쉬맵 생성
		String[] algorithmList = getResources().getStringArray(R.array.algorithmlist);
		ArrayList<String[]> parameterArrayList = new ArrayList<String[]>();

		for (String algoname : algorithmList) {
			int resStringID = getResources().getIdentifier(algoname + "_parameterlist", "array", "com.samsung.ip");
			String[] paraArray = getResources().getStringArray(resStringID);
			parameterArrayList.add(paraArray);
		}

		// algorithm class 생성
		algorithmData = new AlgorithmData(algorithmList, parameterArrayList);
		algorithmEnableData = new AlgorithmEnableData(algorithmList, parameterArrayList);

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
				algorithmEnableData = (AlgorithmEnableData) data.getSerializableExtra("algorithmEnableData");
				resultBitmap = null;
			}

		}
		if (requestCode == REQ_CODE_FILE_SETTING) {
			if (resultCode == Activity.RESULT_OK) {
		
				String fileName = data.getStringExtra("fileName");
				try {
					readToFile(fileName);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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

	private void readToFile(String inputString) throws IOException {

		FileInputStream iStream = null;
		BufferedReader reader = null;

		try {

			File dir = Environment.getExternalStorageDirectory();
			
			iStream = new FileInputStream(
					Environment.getExternalStorageDirectory().getAbsolutePath() + "/mDNIe/"+ inputString);
			reader = new BufferedReader(new InputStreamReader(iStream));
			
			algorithmData.setParameterFromRegister(reader);

		} catch (FileNotFoundException ex) {

		} catch (IOException ex) {

		} finally {
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
		
			if (resultBitmap != null) {
				saveBitmaptoJpeg(resultBitmap, "mDNIe", "save_test");
			}
			mDrawerLayout.closeDrawer(mDrawerList);

			break;

		case MENU_ALGO_PARA_SETTING:
		
			Intent intentSubActivity = new Intent(JniIPActivity.this, AlgorithmSettingListView.class);
			intentSubActivity.putExtra("algorithmData", algorithmData);
			intentSubActivity.putExtra("algorithmEnableData", algorithmEnableData);
			startActivityForResult(intentSubActivity, REQ_CODE_ALGO_SETTING);
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_REGISTER_LOAD:
			
			Intent intentFileActivity = new Intent(JniIPActivity.this, Main.class);
			startActivityForResult(intentFileActivity, REQ_CODE_FILE_SETTING);

			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_REGISTER_SAVE:
			
			AppPreference ap = new AppPreference(this);
			//ap.remove("saveCount");
			saveCount = ap.getValue("saveCount", 0);
			saveCount++;
			ap.put("saveCount", saveCount);
			algorithmData.saveRegisterFromParameter(saveCount);
		
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

		ArrayList<AlgorithmMultiItem> arItem;

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

	public class Main_Result extends Fragment implements OnClickListener {

		public final static String TAG = "Main_Result";

		public ImageView resultImage;
		private ImageView mRealtimeHandle;
		public Button algorithmSLCE;
		public Button algorithmNR;
		public Button algorithmFA_DE;
		public Button algorithmCS;
		public Button algorithmCC;
		public Button algorithmASCR;

		public LinearLayout inflatedLayout;
		public LayoutInflater inflater;

		public final int ALGORITHM_SLCE = 0;
		public final int ALGORITHM_NR = 1;
		public final int ALGORITHM_DE_FA = 2;
		public final int ALGORITHM_CS = 3;
		public final int ALGORITHM_CC = 4;
		public final int ALGORITHM_ASCR = 5;

		ArrayList<AlgorithmMultiItem> arItem;
		AlgorithmMultiAdapter MyAdapter;

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

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			int resStringID;
			String[] paraArray;
			ListView MyList;

			switch (v.getId()) {
			case R.id.button_SLCE:
				// ㄴ
				Log.d(TAG, "SLCE page load");

				// LinearLayout 객체 할당
				inflatedLayout = (LinearLayout) getView().findViewById(R.id.dynamicArea);
				// LayoutInflater 객체 생성
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
				inflater.inflate(R.layout.inflated_layout, inflatedLayout);
				arItem = new ArrayList<AlgorithmMultiItem>();
				// Enable 정보대로 리스트뷰 생성하기
				arItem.add(new AlgorithmMultiItem(0, "SLCE", 0, 100, 50));
				resStringID = getResources().getIdentifier("SLCE_parameterlist", "array", "com.samsung.ip");
				paraArray = getResources().getStringArray(resStringID);

				for (String str : paraArray) {

					if ((boolean) algorithmEnableData.algorithmEableList.get(0).get(str)) {
						arItem.add(new AlgorithmMultiItem(1, str, 0, 200, 100));
					}
				}

				MyAdapter = new AlgorithmMultiAdapter(getActivity(), arItem);
				MyList = (ListView) getView().findViewById(R.id.result_list);
				MyList.setAdapter(MyAdapter);

				break;
			case R.id.button_NR:
				Log.d(TAG, "NR page load");
				// LinearLayout 객체 할당
				inflatedLayout = (LinearLayout) getView().findViewById(R.id.dynamicArea);
				// LayoutInflater 객체 생성
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
				inflater.inflate(R.layout.inflated_layout, inflatedLayout);
				arItem = new ArrayList<AlgorithmMultiItem>();
				// Enable 정보대로 리스트뷰 생성하기
				arItem.add(new AlgorithmMultiItem(0, "NR", 0, 100, 50));
				resStringID = getResources().getIdentifier("NR_parameterlist", "array", "com.samsung.ip");
				paraArray = getResources().getStringArray(resStringID);

				for (String str : paraArray) {

					if ((boolean) algorithmEnableData.algorithmEableList.get(1).get(str)) {
						arItem.add(new AlgorithmMultiItem(1, str, 0, 200, 100));
					}
				}

				MyAdapter = new AlgorithmMultiAdapter(getActivity(), arItem);
				MyList = (ListView) getView().findViewById(R.id.result_list);
				MyList.setAdapter(MyAdapter);
				break;
			case R.id.button_FA_DE:
				Log.d(TAG, "button test 3");
				// LinearLayout 객체 할당
				inflatedLayout = (LinearLayout) getView().findViewById(R.id.dynamicArea);
				// LayoutInflater 객체 생성
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
				inflater.inflate(R.layout.inflated_layout, inflatedLayout);
				arItem = new ArrayList<AlgorithmMultiItem>();
				// Enable 정보대로 리스트뷰 생성하기
				arItem.add(new AlgorithmMultiItem(0, "DE/FA", 0, 100, 50));
				resStringID = getResources().getIdentifier("DE_FA_parameterlist", "array", "com.samsung.ip");
				paraArray = getResources().getStringArray(resStringID);

				for (String str : paraArray) {

					if ((boolean) algorithmEnableData.algorithmEableList.get(2).get(str)) {
						arItem.add(new AlgorithmMultiItem(1, str, 0, 200, 100));
					}
				}

				MyAdapter = new AlgorithmMultiAdapter(getActivity(), arItem);
				MyList = (ListView) getView().findViewById(R.id.result_list);
				MyList.setAdapter(MyAdapter);
				break;
			case R.id.button_CS:
				Log.d(TAG, "button test 4");
				// LinearLayout 객체 할당
				inflatedLayout = (LinearLayout) getView().findViewById(R.id.dynamicArea);
				// LayoutInflater 객체 생성
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
				inflater.inflate(R.layout.inflated_layout, inflatedLayout);
				arItem = new ArrayList<AlgorithmMultiItem>();
				// Enable 정보대로 리스트뷰 생성하기
				arItem.add(new AlgorithmMultiItem(0, "CS", 0, 100, 50));
				resStringID = getResources().getIdentifier("CS_parameterlist", "array", "com.samsung.ip");
				paraArray = getResources().getStringArray(resStringID);

				for (String str : paraArray) {

					if ((boolean) algorithmEnableData.algorithmEableList.get(3).get(str)) {
						arItem.add(new AlgorithmMultiItem(1, str, 0, 200, 100));
					}
				}

				MyAdapter = new AlgorithmMultiAdapter(getActivity(), arItem);
				MyList = (ListView) getView().findViewById(R.id.result_list);
				MyList.setAdapter(MyAdapter);
				break;
			case R.id.button_CC:
				Log.d(TAG, "button test 5");
				// LinearLayout 객체 할당
				inflatedLayout = (LinearLayout) getView().findViewById(R.id.dynamicArea);
				// LayoutInflater 객체 생성
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
				inflater.inflate(R.layout.inflated_layout, inflatedLayout);
				arItem = new ArrayList<AlgorithmMultiItem>();
				// Enable 정보대로 리스트뷰 생성하기
				arItem.add(new AlgorithmMultiItem(0, "CC", 0, 100, 50));
				resStringID = getResources().getIdentifier("CC_parameterlist", "array", "com.samsung.ip");
				paraArray = getResources().getStringArray(resStringID);

				for (String str : paraArray) {

					if ((boolean) algorithmEnableData.algorithmEableList.get(4).get(str)) {
						arItem.add(new AlgorithmMultiItem(1, str, 0, 200, 100));
					}
				}

				MyAdapter = new AlgorithmMultiAdapter(getActivity(), arItem);
				MyList = (ListView) getView().findViewById(R.id.result_list);
				MyList.setAdapter(MyAdapter);
				break;

			case R.id.button_ASCR:
				Log.d(TAG, "button test 6");
				// LinearLayout 객체 할당
				inflatedLayout = (LinearLayout) getView().findViewById(R.id.dynamicArea);
				// LayoutInflater 객체 생성
				inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				// Inflated_Layout.xml로 구성한 레이아웃을 inflatedLayout 영역으로 확장
				inflater.inflate(R.layout.inflated_layout, inflatedLayout);
				arItem = new ArrayList<AlgorithmMultiItem>();
				// Enable 정보대로 리스트뷰 생성하기
				arItem.add(new AlgorithmMultiItem(0, "ASCR", 0, 100, 50));
				resStringID = getResources().getIdentifier("ASCR_parameterlist", "array", "com.samsung.ip");
				paraArray = getResources().getStringArray(resStringID);

				for (String str : paraArray) {

					if ((boolean) algorithmEnableData.algorithmEableList.get(5).get(str)) {
						arItem.add(new AlgorithmMultiItem(1, str, 0, 200, 100));
					}
				}

				MyAdapter = new AlgorithmMultiAdapter(getActivity(), arItem);
				MyList = (ListView) getView().findViewById(R.id.result_list);
				MyList.setAdapter(MyAdapter);
				break;

			default:
				break;
			}

		}

		public Bitmap getOutputBitmap(int length, byte[] outputPixel) {

			Bitmap resultBitmap;
			ByteBuffer outBuffer = ByteBuffer.allocate(length);

			outBuffer.put(outputPixel, 0, length);
			outBuffer.rewind();

			resultBitmap = Bitmap.createBitmap(rawBitmap.getWidth(), rawBitmap.getHeight(), Bitmap.Config.ARGB_8888);
			resultBitmap.copyPixelsFromBuffer(outBuffer);

			return resultBitmap;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			mRealtimeHandle = (ImageView) getView().findViewById(R.id.result_handle);

			byte[] inputPixel = getInputPixel(((JniIPActivity) getActivity()).rawBitmap);
			byte[] outputPixel = new byte[rawBitmap.getByteCount()];

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

			algorithmSLCE = (Button) getView().findViewById(R.id.button_SLCE);
			algorithmNR = (Button) getView().findViewById(R.id.button_NR);
			algorithmFA_DE = (Button) getView().findViewById(R.id.button_FA_DE);
			algorithmCS = (Button) getView().findViewById(R.id.button_CS);
			algorithmCC = (Button) getView().findViewById(R.id.button_CC);
			algorithmASCR = (Button) getView().findViewById(R.id.button_ASCR);

			algorithmSLCE.setOnClickListener(this);
			algorithmNR.setOnClickListener(this);
			algorithmFA_DE.setOnClickListener(this);
			algorithmCS.setOnClickListener(this);
			algorithmCC.setOnClickListener(this);
			algorithmASCR.setOnClickListener(this);

			for (int algoIndex = 0; algoIndex < algorithmEnableData.algorithmEableList.size(); algoIndex++) {
				if ((boolean) algorithmEnableData.algorithmEableList.get(algoIndex).get("algoEnable")) {
					switch (algoIndex) {
					case ALGORITHM_SLCE:
						algorithmSLCE.setVisibility(View.VISIBLE);
						break;
					case ALGORITHM_NR:
						algorithmNR.setVisibility(View.VISIBLE);
						break;
					case ALGORITHM_DE_FA:
						algorithmFA_DE.setVisibility(View.VISIBLE);
						break;
					case ALGORITHM_CS:
						algorithmCS.setVisibility(View.VISIBLE);
						break;
					case ALGORITHM_CC:
						algorithmCC.setVisibility(View.VISIBLE);
						break;
					case ALGORITHM_ASCR:
						algorithmASCR.setVisibility(View.VISIBLE);
						break;

					default:
						break;
					}
				} else {
					switch (algoIndex) {
					case ALGORITHM_SLCE:
						algorithmSLCE.setVisibility(View.GONE);
						break;
					case ALGORITHM_NR:
						algorithmNR.setVisibility(View.GONE);
						break;
					case ALGORITHM_DE_FA:
						algorithmFA_DE.setVisibility(View.GONE);
						break;
					case ALGORITHM_CS:
						algorithmCS.setVisibility(View.GONE);
						break;
					case ALGORITHM_CC:
						algorithmCC.setVisibility(View.GONE);
						break;
					case ALGORITHM_ASCR:
						algorithmASCR.setVisibility(View.GONE);
						break;

					default:
						break;
					}
				}

			}

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
