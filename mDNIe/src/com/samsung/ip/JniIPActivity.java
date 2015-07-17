package com.samsung.ip;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Locale;

import android.R.string;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class JniIPActivity extends Activity {
	
	
	final int REQ_CODE_SELECT_IMAGE = 100;
	final String TAG = "JniGLActivity";
	// fragment 변수
	int mCurrentFragmentIndex;

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

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] mMenuList;
	private boolean mViewModeFlag = false;
	private boolean mResultFlag = false;
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_jni_gl);

		// realtime_frag = new Main_Realtime();

		mCurrentFragmentIndex = FRAGMENT_IMAGELOAD;

		mTitle = mDrawerTitle = getTitle();
		mMenuList = getResources().getStringArray(R.array.menu_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
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
				R.drawable.menu, /*
												 * nav drawer image to replace
												 * 'Up' caret
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

		if (savedInstanceState == null) {
			// selectItem(0);
		}

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
	}

	public void saveBitmaptoJpeg(Bitmap bitmap, String folder, String name) {
		
		String saveImg = MediaStore.Images.Media.insertImage(JniIPActivity.this.getContentResolver(), bitmap, "test_Image", "bitmap");
		
		Uri uri = Uri.parse(saveImg);

		sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));

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

	public static native int nativeGetOutputPixel(byte[] inputpixels, byte[] outputPixel, Alogrithm tk, int width, int height);

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
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
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		Log.d(TAG, "position = " + position);
		// update the main content by replacing fragments
		switch (position) {

		case MENU_DEFAULT:
			/*
			 * default 어플 시작 시 기본 화면 띄우기 position = 5
			 */
			Fragment fragment = new PlanetFragment();
			Bundle args = new Bundle();
			args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
			fragment.setArguments(args);

			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

			// update selected item and title, then close the drawer
			// mDrawerList.setItemChecked(position, true);
			setTitle(mMenuList[position]);
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_IMAGE_LOAD:
			/*
			 * 갤러리에서 이미지 로드 position = 0
			 */
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
			Intent intentSubActivity = new Intent(JniIPActivity.this, MultipleChoiceListView.class);
			startActivity(intentSubActivity);
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_REGISTER_LOAD:
			/*
			 * Register 설정 로드 position = 3
			 */
			mDrawerLayout.closeDrawer(mDrawerList);
			break;

		case MENU_REGISTER_SAVE:
			/*
			 * Register 설정 저장 position = 4
			 */
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
	 * Fragment that appears in the "content_frame", shows a planet
	 */
	public static class PlanetFragment extends Fragment {
		public static final String ARG_PLANET_NUMBER = "planet_number";

		public PlanetFragment() {
			// Empty constructor required for fragment subclasses
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
			int i = getArguments().getInt(ARG_PLANET_NUMBER);
			String planet = getResources().getStringArray(R.array.menu_list)[i];

			int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()), "drawable",
					getActivity().getPackageName());
			((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
			getActivity().setTitle(planet);
			return rootView;
		}
	}

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
			// 3.화면에 꽉차는 옵션 (선택사항)
			loadedAttacher.setScaleType(ScaleType.FIT_CENTER);
			loaded_image.setImageBitmap(((JniIPActivity) getActivity()).rawBitmap);
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
			Log.d(TAG, "bitmap = rawBitmap");
			Previmage = (ImageView) getView().findViewById(R.id.viewmode_prev);
			Resultimage = (ImageView) getView().findViewById(R.id.viewmode_result);
			prevAttacher = new PhotoViewAttacher(Previmage);
			resultAttacher = new PhotoViewAttacher(Resultimage);
			Previmage.setImageBitmap(((JniIPActivity) getActivity()).rawBitmap);
			Resultimage.setImageBitmap(((JniIPActivity) getActivity()).resultBitmap);

		}
	}

	public class Main_Result extends Fragment {
		
		
		public final static String TAG = "Main_Result";

		public ImageView resultImage;
		private ImageView mRealtimeHandle;

		PhotoViewAttacher mAttacher;

		Bitmap inputBitmap;
		Bitmap outputBitmap;

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

			resultBitmap = Bitmap.createBitmap(inputBitmap.getWidth(), inputBitmap.getHeight(),
					Bitmap.Config.ARGB_8888);
			resultBitmap.copyPixelsFromBuffer(outBuffer);

			return resultBitmap;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onActivityCreated(savedInstanceState);
			mRealtimeHandle = (ImageView) findViewById(R.id.result_handle);
			inputBitmap = ((JniIPActivity) getActivity()).rawBitmap;

			byte[] inputPixel = getInputPixel(inputBitmap);
			byte[] outputPixel = new byte[inputBitmap.getByteCount()];

			Alogrithm algorithmClass = new Alogrithm();
			Log.d("message", "java"+ " " + algorithmClass.getNonce());
			
			int length = JniIPActivity.nativeGetOutputPixel(inputPixel, outputPixel, algorithmClass, inputBitmap.getWidth(), inputBitmap.getHeight());

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

		}

	}

}
