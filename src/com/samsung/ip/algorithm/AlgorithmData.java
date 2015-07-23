package com.samsung.ip.algorithm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.samsung.ip.JniIPActivity;

import android.os.Environment;
import android.util.Log;

public class AlgorithmData implements Serializable {

	public ArrayList<HashMap<String, Object>> algorithmList;
	private HashMap<String, Object> paramMap;
	private static final String TAG = "AlgorithmData";
	private Map<String, Object> filedInfo;
	public String[] algoList;
	public ArrayList<String[]> parameterArrayList;
	public String[] keyParmaSet;
	
	
	public Integer x_1 = 0;
	public Boolean x_2 = false;
	public Integer x_3 = (Integer) 0;
	public Boolean x_4 = true;
	public Boolean x_5 = true;
	public Boolean x_6 = false;
	public Boolean x_7 = false;
	public Integer x_8 = (Integer) 255;
	public Integer x_9 = (Integer) 1;
	public Integer x_10 = (Integer) 0;
	public Integer x_11 = (Integer) 0;
	public Integer x_12 = (Integer) 1;
	public Integer x_13 = (Integer) 0;
	public Integer x_14 = (Integer) 1;
	public Integer x_15 = (Integer) 0;
	public Integer x_16 = (Integer) 1;
	public Integer x_17 = (Integer) 0;
	public Integer x_18 = (Integer) 1;
	public Integer x_19 = (Integer) 0;
	public Integer x_20 = (Integer) 1;
	public Integer x_21 = (Integer) 0;
	public Integer x_22 = (Integer) 1;
	public Integer x_23 = (Integer) 0;
	public Integer x_24 = (Integer) 1;
	public Integer x_25 = (Integer) 0;
	public Integer x_26 = (Integer) 1;
	public Integer x_27 = (Integer) 0;
	public Integer x_28 = (Integer) 1;
	public Integer x_29 = (Integer) 0;
	public Integer x_30 = (Integer) 1;
	public Integer x_31 = (Integer) 0;
	public Integer x_32 = (Integer) 1;
	public Integer x_33 = (Integer) 0;
	public Integer x_34 = (Integer) 1;
	public Integer x_35 = (Integer) 0;
	public Integer x_36 = (Integer) 1;
	public Integer x_37 = (Integer) 0;
	public Integer x_38 = (Integer) 1;
	public Integer x_39 = (Integer) 0;
	public Integer x_40 = (Integer) 1;
	public Integer x_41 = (Integer) 0;
	public Integer x_42 = (Integer) 1;
	public Integer x_43 = (Integer) 0;
	public Integer x_44 = (Integer) 1;
	public Integer x_45 = (Integer) 0;
	public Integer x_46 = (Integer) 1;
	public Integer x_47 = (Integer) 0;
	public Integer x_48 = (Integer) 1;
	public Integer x_49 = (Integer) 0;
	public Integer x_50 = (Integer) 1;
	public Integer x_51 = (Integer) 0;
	public Integer x_52 = (Integer) 1;
	public Integer x_53 = (Integer) 0;
	public Integer x_54 = (Integer) 1;
	public Integer x_55 = (Integer) 0;
	public Integer x_56 = (Integer) 1;
	public Integer x_57 = (Integer) 0;
	public Integer x_58 = (Integer) 1;

	public Integer secret_1 = 0;
	public Integer secret_2 = 0;
	public Integer secret_3 = 0;
	public Boolean secret_4 = false;
	public Integer secret_5 = 0;
	public Integer secret_6 = 0;
	public Boolean secret_7 = false;
	public Integer secret_8 = 0;
	public Integer secret_9 = 0;
	public Boolean secret_10 = false;
	public Boolean secret_11 = false;
	public Boolean secret_12 = false;
	public Integer secret_13 = 0;
	public Integer secret_14 = 0;
	public Integer secret_15 = 0;
	public Integer secret_16 = 0;
	public Integer secret_17 = 0;
	public Integer secret_18 = 0;
	public Integer secret_19 = 0;
	public Integer secret_20 = 0;
	public Integer secret_21 = 0;
	public Integer secret_22 = 0;
	public Integer secret_23 = 0;
	public Integer secret_24 = 0;
	public Integer secret_25 = 0;
	public Integer secret_26 = 0;
	public Integer secret_27 = 0;
	public Integer secret_28 = 0;
	public Boolean secret_29 = false;
	public Integer secret_30 = 0;
	public Integer secret_31 = 0;
	public Integer secret_32 = 0;
	public Integer secret_33 = 0;

	public Boolean SLCE_MASK = true;
	public Boolean SLCE_BT = false;
	public Boolean SLCE_MO = false;
	public Boolean SLCE_ON = true;
	public Integer SLCE_G = (Integer) 24;   	public Integer SLCE_G_MAX = 64;
	public Integer SLCE_CG = (Integer) 36;  	public Integer SLCE_CG_MAX= 64;
	public Boolean SLCE_SO = true;
	public Integer SLCE_SR = (Integer) 0;   	public Integer SLCE_SR_MAX = 16;
	public Integer SLCE_SMD = (Integer) 20; 	public Integer SLCE_SMD_MAX = 128;
	public Integer SLCE_IG = (Integer) 179; 	public Integer SLCE_IG_MAX = 256;
	public Integer SLCE_RO = (Integer) 270; 	public Integer SLCE_RO_MAX = 512;
	public Boolean SLCE_RG = true;
	public Integer SLCE_REF_GAIN = (Integer)0;  public Integer SLCE_REF_GAIN_MAX = 256;
	public Integer SLCE_H = (Integer) 6;        public Integer SLCE_H_MAX = 8;
	public Integer SLCE_V = (Integer) 6;	    public Integer SLCE_V_MAX = 8;
	public Integer SLCE_BTH = (Integer) 250;    public Integer SLCE_BTH_MAX = 256;
	public Integer SLCE_BSR = (Integer) 45;     public Integer SLCE_BSR_MAX = 128;
	public Integer SLCE_DTH = (Integer) 3;	    public Integer SLCE_DTH_MAX = 8;
	public Integer SLCE_MRO = (Integer) 150;    public Integer SLCE_MRO_MAX = 256;

	public boolean NR_E = false;

	public Boolean DE_E = false;
	public Integer DE_G = (Integer) 0; 			public Integer DE_G_MAX = 1024;
	public Integer DE_M1 = (Integer) 2047;		public Integer DE_M1_MAX = 2048;
	public Integer DE_M2 = (Integer) 2047;		public Integer DE_M2_MAX = 2048;

	public Boolean FE_E = false;
	public Integer FA_ET = (Integer) 20;		public Integer FA_ET_MAX = 256;
	public Integer FA_SP = (Integer) 10;		public Integer FA_SP_MAX = 8192;
	public Integer FA_SN = (Integer) 50;		public Integer FA_SN_MAX = 8192;
	public Integer FA_MDG = (Integer) 500;		public Integer FA_MDG_MAX = 8192;
	public Integer FA_PP = (Integer) 2954;		public Integer FA_PP_MAX = 16384;
	public Integer FA_SC = (Integer) 28313;		public Integer FA_SC_MAX = 65536;
	public Integer FA_D = (Integer) 454497310;	public Long FA_D_MAX = (Long)4294967295L;
	public Long FA_DD = (Long) 170858368933757474L; public Long FA_DD_MAX = (Long)170858368933757474L;

	public Integer FA_PMW = (Integer) 16;		public Integer FA_PMW_MAX = 64;
	public Integer FA_FMW = (Integer) 16;		public Integer FA_FMW_MAX = 64;
	public Integer FA_SZW = (Integer) 7;		public Integer FA_SZW_MAX = 16;
	public Integer FA_SZH = (Integer) 7;		public Integer FA_SZH_MAX = 16;
	public Integer FA_OC10C = (Integer) 32;		public Integer FA_OC10C_MAX = 64;
	public Integer FA_OC20C = (Integer) 45;		public Integer FA_OC20C_MAX = 64;

	public Boolean CE_E = false;
	public Boolean CS_E  = false;

	public Integer  TestValue= 10;				public Integer TestValue_MAX = 256;
	
	public Boolean ASCR_MASK = true;
	public Boolean ASCR_BT = false;
	public Boolean ASCR_MO = false;
	public Boolean ASCR_AO = true;
	public Boolean ASCR_LO = true;
	public Integer ASCR_S = (Integer) 16;		public Integer ASCR_S_MAX = 256;
	public Integer ASCR_SCB = (Integer) 103;	public Integer ASCR_SCB_MAX = 256;
	public Integer ASCR_SCR = (Integer) 169;	public Integer ASCR_SCR_MAX = 256;
	public Integer ASCR_DU = (Integer) 12;		public Integer ASCR_DU_MAX = 256;
	public Integer ASCR_DD = (Integer) 12;		public Integer ASCR_DD_MAX = 256;
	public Integer ASCR_DR = (Integer) 12;		public Integer ASCR_DR_MAX = 256;
	public Integer ASCR_DL = (Integer) 12;		public Integer ASCR_DL_MAX = 256;
	public Integer ASCR_DDU = (Integer) 43691;	public Integer ASCR_DDU_MAX = 1048576;
	public Integer ASCR_DDD = (Integer) 43691;	public Integer ASCR_DDD_MAX = 1048576;
	public Integer ASCR_DDR = (Integer) 43691;	public Integer ASCR_DDR_MAX = 1048576;
	public Integer ASCR_DDL = (Integer) 43691;	public Integer ASCR_DDL_MAX = 1048576;
	public Integer ASCR_SRR = (Integer) 213;	public Integer ASCR_SRR_MAX = 256;
	public Integer ASCR_SRG = (Integer) 44;		public Integer ASCR_SRG_MAX = 256;
	public Integer ASCR_SRB = (Integer) 42;		public Integer ASCR_SRB_MAX = 256;
	public Integer ASCR_SYR = (Integer) 255;	public Integer ASCR_SYR_MAX = 256;
	public Integer ASCR_SYG = (Integer) 245;	public Integer ASCR_SYG_MAX = 256;
	public Integer ASCR_SYB = (Integer) 99;		public Integer ASCR_SYB_MAX = 256;
	public Integer ASCR_SMR = (Integer) 254;	public Integer ASCR_SMR_MAX = 256;
	public Integer ASCR_SMG = (Integer) 74;		public Integer ASCR_SMG_MAX = 256;
	public Integer ASCR_SMB = (Integer) 255;	public Integer ASCR_SMB_MAX = 256;
	public Integer ASCR_SWR = (Integer) 255;	public Integer ASCR_SWR_MAX = 256;
	public Integer ASCR_SWG = (Integer) 249;	public Integer ASCR_SWG_MAX = 256;
	public Integer ASCR_SWB = (Integer) 248;	public Integer ASCR_SWB_MAX = 256;
	public Integer ASCR_WCR = (Integer) 0;		public Integer ASCR_WCR_MAX = 256;
	public Integer ASCR_WRR = (Integer) 255;	public Integer ASCR_WRR_MAX = 256;
	public Integer ASCR_WCG = (Integer) 255;	public Integer ASCR_WCG_MAX = 256;
	public Integer ASCR_WRG = (Integer) 0;		public Integer ASCR_WRG_MAX = 256;
	public Integer ASCR_WCB = (Integer) 255;	public Integer ASCR_WCB_MAX = 256;
	public Integer ASCR_WRB = (Integer) 0;		public Integer ASCR_WRB_MAX = 256;
	public Integer ASCR_WMR = (Integer) 255;	public Integer ASCR_WMR_MAX = 256;
	public Integer ASCR_WGR = (Integer) 0;		public Integer ASCR_WGR_MAX = 256;
	public Integer ASCR_WMG = (Integer) 0;		public Integer ASCR_WMG_MAX = 256;
	public Integer ASCR_WGG = (Integer) 255;	public Integer ASCR_WGG_MAX = 256;
	public Integer ASCR_WMB = (Integer) 255;	public Integer ASCR_WMB_MAX = 256;
	public Integer ASCR_WGB = (Integer) 0;		public Integer ASCR_WGB_MAX = 256;
	public Integer ASCR_WYR = (Integer) 255;	public Integer ASCR_WYR_MAX = 256;
	public Integer ASCR_WBR = (Integer) 0;		public Integer ASCR_WBR_MAX = 256;
	public Integer ASCR_WYG = (Integer) 255;	public Integer ASCR_WYG_MAX = 256;
	public Integer ASCR_WBG = (Integer) 0;		public Integer ASCR_WBG_MAX = 256;
	public Integer ASCR_WYB = (Integer) 0;		public Integer ASCR_WYB_MAX = 256;
	public Integer ASCR_WBB = (Integer) 255;	public Integer ASCR_WBB_MAX = 256;
	public Integer ASCR_WWR = (Integer) 255;	public Integer ASCR_WWR_MAX = 256;
	public Integer ASCR_WKR = (Integer) 0;		public Integer ASCR_WKR_MAX = 256;
	public Integer ASCR_WWG = (Integer) 255;	public Integer ASCR_WWG_MAX = 256;
	public Integer ASCR_WKG = (Integer) 0;		public Integer ASCR_WKG_MAX = 256;
	public Integer ASCR_WWB = (Integer) 255;	public Integer ASCR_WWB_MAX = 256;
	public Integer ASCR_WKB = (Integer) 0;		public Integer ASCR_WKB_MAX = 256;

	public Map<String, Object> toMap(Object obj) {
		Field[] fields = obj.getClass().getDeclaredFields();
		Map<String, Object> result = new HashMap<String, Object>();

		for (int i = 0; i < fields.length; ++i) {
			try {
				// System.out.printf("name : %s, value : %s
				// \n",fields[i].getName(), fields[i].get(this) );
				result.put(fields[i].getName(), fields[i].get(obj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				return null;
			} catch (IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		return result;
	}

	// 생성자
	public AlgorithmData() {

	}

	/*
	 * 객체 생성시 먼저 AlgorithmData(new AlgorithmData(), null , null) 과 같이 생성해주면 멤버
	 * 변수를 맵핑 할 수 있음
	 */
	public AlgorithmData(String[] algoList, ArrayList<String[]> parameterArrayList) {
		this.algoList = algoList;
		this.parameterArrayList = parameterArrayList;

		// field Map 을 생성하기 위하여 AlgorithmData 객체를 받아야 한다.
		// 맵핑을 통하여 멤버변수에 접근할 수 있고, 자동으로 hashmap 생성 가능

		filedInfo = toMap(this);

		// 생성자를 통해서 알고리즘과 파라미터 해쉬맵 생성
		algorithmList = new ArrayList<HashMap<String, Object>>();

		int index = 0;
		for (String algo : algoList) {
			Log.d(TAG, "name :" + algo);
			paramMap = new HashMap<String, Object>();

			// 알고리즘 이름 해쉬맵에 삽입
			paramMap.put("algorithmName", algo);
			String[] parameterList = parameterArrayList.get(index++);
			for (String para : parameterList) {

				paramMap.put(para, filedInfo.get(para));
				Log.d(TAG, "name :" + para + " value:" + filedInfo.get(para));

			}
			algorithmList.add(paramMap);
		}
		// 자동으로 AlgorithmList 생성

	}

	public HashMap<String, Object> InsertHashMapToAlgorithmList(String AlgorithmName) {
		HashMap<String, Object> hash = new HashMap<String, Object>();
		filedInfo = toMap(this);
		int index = 0;
		for (String algoname : algoList) {

			if (AlgorithmName.equals(algoname)) {
				Log.d(TAG, "name :" + algoname);

				// 알고리즘 이름 해쉬맵에 삽입
				hash.put("algorithmName", algoname);
				String[] parameterList = parameterArrayList.get(index);
				for (String para : parameterList) {

					hash.put(para, filedInfo.get(para));
					 Log.d(TAG, "new insert name :" + para + " value:" +
					 filedInfo.get(para));

				}
				algorithmList.add(hash);
				break;
			}
			index++;
		}

		return hash;
	}

	public int getAlgorithmListSize() {

		return algorithmList.size();

	}

	public int getParameterMapSize(int i) {

		return algorithmList.get(i).size();

	}

	public Object getName(int i, String inputString) {

		return algorithmList.get(i).get(inputString);

	}

	public Object getParameter(int i, String inputString) {


		return algorithmList.get(i).get(inputString);

	}

	public void getKey(int i) {
		
		keyParmaSet = algorithmList.get(i).keySet().toArray(new String[algorithmList.get(i).keySet().size()]);
		// return keySet;
	}

	public int keyCount() {

		return keyParmaSet.length;

	}

	public Object getKeyString(int j) {

		return keyParmaSet[j];
	}

	private String hexToBinary(String input) {

		Integer temp = Integer.parseInt(input, 16);
		StringBuilder binaryStringBuilder = new StringBuilder();

		for (int i = 0; i < 8; i++)
			binaryStringBuilder.append(((0x80 >>> i) & temp) == 0 ? '0' : '1');

		return binaryStringBuilder.toString();
	}
	
	private String toBinary( byte[] bytes )
	{
		
	    StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
	    for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
	        sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
	    return sb.toString();
	    
	}
	
	private int boolToBinary(Boolean b) {
	    
		return b.compareTo(false);
		
	}
	private String floatToBinary(Float input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(4).putFloat(input).array();
		
		return toBinary(bytes);
	}
	
	private String longToBinary(Long input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(8).putLong(input).array();
	
		return toBinary(bytes);
	}
	private String intToBinary(Integer input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(4).putInt(input).array();

		return toBinary(bytes);
	}
	private String byteToBinary(Integer input) throws IOException{
		
		byte[] bytes = ByteBuffer.allocate(1).put(input.byteValue()).array();
		
		return toBinary(bytes);
	}
	
	private void writeToFile(String data, int saveCount) throws IOException {
		
		File patternDirectory = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/mDNIe/clientRegister"+saveCount+".dat");
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
	
	public void saveRegisterFromParameter(int saveCount) throws IOException{
	
		
		StringBuilder stringBuilder = new StringBuilder();
		String finalString;
		/********************** 1 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_1")).substring(0,8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/********************** 2 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_1")).substring(1,8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("x_2")));
		finalString= stringBuilder.toString();

		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/********************** 3 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_2")).substring(6,8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_3")).substring(6,8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("ASCR_MASK")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("x_4")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("SLCE_MASK")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("x_5")));
		finalString= stringBuilder.toString();

		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/********************** 4 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_3")).substring(6,8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("ASCR_BT")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("ASCR_MO")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("x_6")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("x_7")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("SLCE_BT")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("SLCE_MO")));
		finalString= stringBuilder.toString();

		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/********************** 5 */	
		stringBuilder.delete(0, 8);
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("SLCE_ON")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("secret_4")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_G")).substring(2, 8));
		finalString= stringBuilder.toString();

		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/********************** 6 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_5")).substring(6, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_CG")).substring(2, 8));
		finalString= stringBuilder.toString();

		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/********************** 7 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_6")).substring(5, 8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("SLCE_SO")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_SR")).substring(4, 8));
		finalString= stringBuilder.toString();

		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
	
		/**********************8 */
		stringBuilder.delete(0, 8);
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("secret_7")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_SMD")).substring(1, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************9 */
		
		stringBuilder.delete(0, 8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_IG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));

		
		/**********************10 */
		stringBuilder.delete(0, 32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_8")).substring(1, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("SLCE_RO")).substring(22, 24));
		finalString= stringBuilder.toString();
	
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************11 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("SLCE_RO")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************12 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_9")).substring(1, 8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("SLCE_RG")));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************13 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_REF_GAIN")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************14 */
		stringBuilder.delete(0,8);
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("secret_10")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_H")).substring(5, 8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("secret_11")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_V")).substring(5, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************15 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_BTH")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************16 */
		stringBuilder.delete(0,8);
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("secret_12")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_BSR")).substring(1, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************17 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_13")).substring(3, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_DTH")).substring(5, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************18 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("SLCE_MRO")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************19 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_14")).substring(5, 8));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("NR_E")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("FE_E")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("DE_E")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("CE_E")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("CS_E")));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
	
		
		/**********************20 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_8")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************21 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_15")).substring(2, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("DE_G")).substring(22, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************22 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("DE_G")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************23 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_16")).substring(3, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("DE_M1")).substring(21, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************24 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("DE_M1")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************25 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_17")).substring(3, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("DE_M2")).substring(21, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************26 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("DE_M2")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************27 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_ET")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************28 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_18")).substring(5, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_SP")).substring(19, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************29 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_SP")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************30 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_19")).substring(5, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_SN")).substring(19, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************31 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_SN")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************32 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_20")).substring(5, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_MDG")).substring(19, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************33 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_MDG")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************34 */
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_21")).substring(6, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_PP")).substring(18, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************35 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_PP")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************36 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_SC")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************37 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_SC")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************38 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_D")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************39 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_D")).substring(8, 16));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************40 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_D")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************40 */
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("FA_D")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************41 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************42 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(8, 16));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************43 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************44 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************45 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(32, 40));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************46 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(40, 48));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************47 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(48, 56));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************48 */
		stringBuilder.delete(0,32);
		stringBuilder.append(longToBinary((Long)this.filedInfo.get("FA_DD")).substring(56, 64));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************49 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_22")).substring(6, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_PMW")).substring(2, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************50 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_23")).substring(6, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_FMW")).substring(2, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************51 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_24")).substring(4, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_SZW")).substring(4, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************52 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_25")).substring(4, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_SZH")).substring(4, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************53 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_26")).substring(6, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_OC10C")).substring(2, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************54 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_27")).substring(6, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("FA_OC20C")).substring(2, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************55 */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_28")).substring(2, 8));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_9")).substring(6, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/**********************56~ */
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_10")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_11")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_12")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_13")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_14")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_15")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_16")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_17")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_18")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_19")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_20")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_21")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_22")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_23")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_24")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_25")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_26")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_27")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_28")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_29")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_30")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_31")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_32")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_33")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_34")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_35")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_36")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_37")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_38")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_39")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_40")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_41")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_42")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_43")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_44")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_45")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_46")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_47")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_48")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_49")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_50")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_51")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_52")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_53")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_54")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_55")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_56")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_57")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("x_58")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("secret_29")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("ASCR_AO")));
		stringBuilder.append(boolToBinary((Boolean)this.filedInfo.get("ASCR_LO")));
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_S")).substring(3, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SCB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SCR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_DU")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_DD")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_DR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_DL")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_30")).substring(4, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDU")).substring(12, 16));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDU")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDU")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_31")).substring(4, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDD")).substring(12, 16));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDD")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDD")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_32")).substring(4, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDR")).substring(12, 16));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDR")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDR")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("secret_33")).substring(4, 8));
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDL")).substring(12, 16));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDL")).substring(16, 24));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,32);
		stringBuilder.append(intToBinary((Integer)this.filedInfo.get("ASCR_DDL")).substring(24, 32));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
	
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SRR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SRG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SRB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SYR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SYG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
						
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SYB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SMR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		/*---------------------*/
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SMG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SMB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SWR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SWG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_SWB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WCR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WRR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WCG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WRG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WCB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WRB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WMR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WGR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WMG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WGG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WMB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WGB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WYR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WBR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WYG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WBG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WYB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WBB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WWR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WKR")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WWG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WKG")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WWB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		stringBuilder.delete(0,8);
		stringBuilder.append(byteToBinary((Integer)this.filedInfo.get("ASCR_WKB")).substring(0, 8));
		finalString= stringBuilder.toString();
		writeToFile(String.format("0x%02x,",Integer.parseInt(finalString,2)), saveCount);
		Log.d("mDNIe","saveRegisterFromParameter" + " " + String.format("0x%02x,",Integer.parseInt(finalString,2)));
		
		JniIPActivity sensor = new JniIPActivity();
		writeToFile(Float.toString(sensor.mluxL), saveCount);
		writeToFile(sensor.mluxR, saveCount);
		writeToFile(sensor.mluxG, saveCount);
		writeToFile(sensor.mluxB, saveCount);
		
	}
	public void setParameterFromRegister(BufferedReader reader) throws IOException {

		String line = null;
		String nextLine = null;
		String binary = null;
		
		/********************** 1 */
		/* x1 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_1",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.x_1" + " " + this.x_1);

		/********************** 2 */
		/* secret_1 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_1",Integer.parseInt(binary.substring(0, 7), 2)) ;
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_1" + " " + this.secret_1);

		/* x2 */
		this.filedInfo.put("x_2", (binary.substring(7, 8).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_2" + " " + this.x_2);

		/********************** 3 */

		/* secret_2 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_2", Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_2" + " " + this.secret_2);

		/* x3 */
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_3",Integer.parseInt(binary.substring(2, 4), 2)) ;
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_3" + " " + this.x_3);

		/* ASCR_MASK */
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_MASK",(binary.substring(4, 5).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_MASK" + " " + this.ASCR_MASK);

		/* x4 */
		this.filedInfo.put("x_4",(binary.substring(5, 6).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_4" + " " + this.x_4);

		this.filedInfo.put("SLCE_MASK ",(binary.substring(6, 7).equals("1"))); 
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_MASK" + " " + this.SLCE_MASK);

		/* x5 */
		this.filedInfo.put("x_5",(binary.substring(7, 8).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_5" + " " + this.x_5);

		/********************** 4 */

		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_3",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_3" + " " + this.secret_3);

		this.filedInfo.put("ASCR_BT",(binary.substring(2, 3).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_BT" + " " + this.ASCR_BT);

		this.filedInfo.put("ASCR_MO",(binary.substring(3, 4).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_MO" + " " + this.ASCR_MO);

		this.filedInfo.put("x_6",(binary.substring(4, 5).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_6 " + " " + this.x_6);

		this.filedInfo.put("x_7",(binary.substring(5, 6).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_7 " + " " + this.x_7);

		this.filedInfo.put("SLCE_BT",(binary.substring(6, 7).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_BT " + " " + this.SLCE_BT);

		this.filedInfo.put("SLCE_MO",(binary.substring(7, 8).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "athis.SLCE_MO " + " " + this.SLCE_MO);
		/********************** 5 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("SLCE_ON",(binary.substring(0, 1).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_ON" + " " + this.SLCE_ON);

		this.filedInfo.put("secret_4",(binary.substring(1, 2).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_4" + " " + this.secret_4);

		this.filedInfo.put("SLCE_G",Integer.parseInt(binary.substring(3, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_G" + " " + this.SLCE_G);

		/********************** 6 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_5",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_5" + " " + this.secret_5);

		this.filedInfo.put("SLCE_CG",Integer.parseInt(binary.substring(2, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_CG" + " " + this.SLCE_CG);

		/********************** 6 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_6",Integer.parseInt(binary.substring(0, 3), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_6" + " " + this.secret_6);

		this.filedInfo.put("SLCE_SO",(binary.substring(3, 4).equals("1")));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_SO" + " " + this.SLCE_SO);

		this.filedInfo.put("SLCE_SR",Integer.parseInt(binary.substring(4, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_SR" + " " + this.SLCE_SR);

		/********************** 7 */

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_7",binary.substring(0, 1).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_7" + " " + this.secret_7);

		this.filedInfo.put("SLCE_SMD",Integer.parseInt(binary.substring(1, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_SMD" + " " + this.SLCE_SMD);

		/********************** 8 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("SLCE_IG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_IG" + " " + this.SLCE_IG);

		/********************** 9~10 */
		line = reader.readLine();
		nextLine = reader.readLine();
		StringBuilder stringBuilder = new StringBuilder();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_8",Integer.parseInt(binary.substring(0, 7), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_8" + " " + this.secret_8);

		stringBuilder.append(binary.substring(7, 8));
		stringBuilder.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString = stringBuilder.toString();
		this.filedInfo.put("SLCE_RO",Integer.parseInt(finalString, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_RO" + " " + this.SLCE_RO);

		/********************** 11 */
		line = reader.readLine();
		// StringBuilder stringBuilder = new StringBuilder();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_9",Integer.parseInt(binary.substring(0, 7), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_9" + " " + this.secret_9);

		this.filedInfo.put("SLCE_RG",binary.substring(7, 8).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_RG" + " " + this.SLCE_RG);

		/********************** 11 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("SLCE_REF_GAIN",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_REF_GAIN" + " " + this.SLCE_REF_GAIN);

		/********************** 12 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_10",binary.substring(0, 1).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_10" + " " + this.secret_10);

		this.filedInfo.put("SLCE_H",Integer.parseInt(binary.substring(1, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_H" + " " + this.SLCE_H);

		this.filedInfo.put("secret_11",binary.substring(4, 5).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_11" + " " + this.secret_11);

		this.filedInfo.put("SLCE_V",Integer.parseInt(binary.substring(5, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_V" + " " + this.SLCE_V);

		/********************** 13 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("SLCE_BTH",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_BTH" + " " + this.SLCE_BTH);

		/********************** 14 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_12",binary.substring(0, 1).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_12" + " " + this.secret_12);

		this.filedInfo.put("SLCE_BSR",Integer.parseInt(binary.substring(1, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_BSR" + " " + this.SLCE_BSR);

		/********************** 15 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_13",Integer.parseInt(binary.substring(0, 6), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_13" + " " + this.secret_13);

		this.filedInfo.put("SLCE_DTH",Integer.parseInt(binary.substring(6, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_DTH" + " " + this.SLCE_DTH);

		/********************** 16 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("SLCE_MRO",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.SLCE_MRO" + " " + this.SLCE_MRO);

		/********************** 17 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_14",Integer.parseInt(binary.substring(0, 3), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_14" + " " + this.secret_14);

		this.filedInfo.put("NR_E",binary.substring(3, 4).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.NR_E" + " " + this.secret_12);

		this.filedInfo.put("FE_E",binary.substring(4, 5).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FE_E" + " " + this.secret_12);

		this.filedInfo.put("DE_E",binary.substring(5, 6).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.DE_E" + " " + this.DE_E);

		this.filedInfo.put("CE_E",binary.substring(6, 7).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.CE_E" + " " + this.CE_E);

		this.filedInfo.put("CS_E",binary.substring(7, 8).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.GS_E" + " " + this.CS_E);

		/********************** 17 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_8",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_8" + " " + this.x_8);

		/********************** 18~19 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_15",Integer.parseInt(binary.substring(0, 6), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_15" + " " + this.secret_15);

		nextLine = reader.readLine();
		StringBuilder stringBuilder2 = new StringBuilder();

		stringBuilder2.append(binary.substring(6, 8));
		stringBuilder2.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString2 = stringBuilder2.toString();
		this.filedInfo.put("DE_G",Integer.parseInt(finalString2, 2));

		Log.d("mDNIe", "putParameterFromRegister" + "this.DE_G" + " " + this.DE_G);

		/********************** 20~21 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_16",Integer.parseInt(binary.substring(0, 5), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_16" + " " + this.secret_16);

		nextLine = reader.readLine();
		StringBuilder stringBuilder3 = new StringBuilder();

		stringBuilder3.append(binary.substring(5, 8));
		stringBuilder3.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString3 = stringBuilder3.toString();
		this.filedInfo.put("DE_M1",Integer.parseInt(finalString3, 2));

		Log.d("mDNIe", "putParameterFromRegister" + "this.DE_M1" + " " + this.DE_M1);

		/********************** 22~23 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_17",Integer.parseInt(binary.substring(0, 5), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_17" + " " + this.secret_17);

		nextLine = reader.readLine();
		StringBuilder stringBuilder4 = new StringBuilder();

		stringBuilder4.append(binary.substring(5, 8));
		stringBuilder4.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString4 = stringBuilder4.toString();
		this.filedInfo.put("DE_M2",Integer.parseInt(finalString4, 2));

		Log.d("mDNIe", "putParameterFromRegister" + "this.DE_M2" + " " + this.DE_M2);

		/********************** 24 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("FA_ET",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_ET" + " " + this.FA_ET);

		/********************** 25~26 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_18",Integer.parseInt(binary.substring(0, 3), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_18" + " " + this.secret_18);

		nextLine = reader.readLine();
		StringBuilder stringBuilder5 = new StringBuilder();
		stringBuilder5.append(binary.substring(3, 8));
		stringBuilder5.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString5 = stringBuilder5.toString();
		this.filedInfo.put("FA_SP",Integer.parseInt(finalString5, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_SP" + " " + this.FA_SP);

		/********************** 27~28 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_19",Integer.parseInt(binary.substring(0, 3), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_19" + " " + this.secret_19);

		nextLine = reader.readLine();
		StringBuilder stringBuilder6 = new StringBuilder();
		stringBuilder6.append(binary.substring(3, 8));
		stringBuilder6.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString6 = stringBuilder6.toString();
		this.filedInfo.put("FA_SN",Integer.parseInt(finalString6, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_SN" + " " + this.FA_SN);

		/********************** 29~30 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_20",Integer.parseInt(binary.substring(0, 3), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_20" + " " + this.secret_20);

		nextLine = reader.readLine();
		StringBuilder stringBuilder7 = new StringBuilder();
		stringBuilder7.append(binary.substring(3, 8));
		stringBuilder7.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString7 = stringBuilder7.toString();
		this.filedInfo.put("FA_MDG",Integer.parseInt(finalString7, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_MDG" + " " + this.FA_MDG);

		/********************** 30~31 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_21",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_21" + " " + this.secret_21);

		nextLine = reader.readLine();
		StringBuilder stringBuilder8 = new StringBuilder();
		stringBuilder8.append(binary.substring(2, 8));
		stringBuilder8.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString8 = stringBuilder8.toString();
		this.filedInfo.put("FA_PP",Integer.parseInt(finalString8, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_PP" + " " + this.FA_PP);

		/********************** 32~33 */

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		nextLine = reader.readLine();
		StringBuilder stringBuilder9 = new StringBuilder();
		stringBuilder9.append(binary);
		stringBuilder9.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString9 = stringBuilder9.toString();
		this.filedInfo.put("FA_SC",Integer.parseInt(finalString9, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_SC" + " " + this.FA_SC);

		/********************** 34~37 */
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
		this.filedInfo.put("FA_D",Integer.parseInt(finalString10, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_D" + " " + this.FA_D);

		/********************** 38~45 */
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
		this.filedInfo.put("FA_DD",Long.parseLong(finalString11, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_DD" + " " + this.FA_DD);

		/********************** 46 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_22",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_22" + " " + this.secret_22);

		this.filedInfo.put("FA_PMW",Integer.parseInt(binary.substring(2, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_PMW" + " " + this.FA_PMW);

		/********************** 47 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_23",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_23" + " " + this.secret_23);

		this.filedInfo.put("FA_FMW",Integer.parseInt(binary.substring(2, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_FMW" + " " + this.FA_FMW);

		/********************** 47 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_24",Integer.parseInt(binary.substring(0, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_24" + " " + this.secret_24);

		this.filedInfo.put("FA_SZW",Integer.parseInt(binary.substring(4, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_SZW" + " " + this.FA_SZW);

		/********************** 48 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_25",Integer.parseInt(binary.substring(0, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_25" + " " + this.secret_25);

		this.filedInfo.put("FA_SZH",Integer.parseInt(binary.substring(4, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_SZH" + " " + this.FA_SZH);

		/********************** 49 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_26",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_25" + " " + this.secret_25);

		this.filedInfo.put("FA_OC10C",Integer.parseInt(binary.substring(2, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_OC10C" + " " + this.FA_OC10C);

		/********************** 50 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_27",Integer.parseInt(binary.substring(0, 2), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_27" + " " + this.secret_27);

		this.filedInfo.put("FA_OC20C",Integer.parseInt(binary.substring(2, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.FA_OC20C" + " " + this.FA_OC20C);

		/********************** 51 */
		line = reader.readLine();

		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_28",Integer.parseInt(binary.substring(0, 6), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_28" + " " + this.secret_28);

		this.filedInfo.put("x_9",Integer.parseInt(binary.substring(6, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_9" + " " + this.x_9);

		/********************** 52~101 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_10",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_10" + " " + this.x_10);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_11",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_11" + " " + this.x_11);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_12",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_12" + " " + this.x_12);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_13",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_13" + " " + this.x_13);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_14",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_14" + " " + this.x_14);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_15",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_15" + " " + this.x_15);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_16",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_16" + " " + this.x_16);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_17",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_17" + " " + this.x_17);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_18",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_18" + " " + this.x_18);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_19",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_19" + " " + this.x_19);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_20",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_20" + " " + this.x_20);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_21",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_21" + " " + this.x_21);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_22",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_22" + " " + this.x_22);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_23",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_23" + " " + this.x_23);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_24",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_24" + " " + this.x_24);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_25",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_25" + " " + this.x_25);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_26",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_26" + " " + this.x_26);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_27",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_27" + " " + this.x_27);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_28",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_28" + " " + this.x_28);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_29",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_29" + " " + this.x_29);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_30",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_30" + " " + this.x_30);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_31",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_31" + " " + this.x_31);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_32",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_32" + " " + this.x_32);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_33",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_33" + " " + this.x_33);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_34",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_34" + " " + this.x_34);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_35",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_35" + " " + this.x_35);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_36",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_36" + " " + this.x_36);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_37",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_10" + " " + this.x_10);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_38",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_37" + " " + this.x_37);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_39",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_38" + " " + this.x_38);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_40",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_39" + " " + this.x_39);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_41",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_40" + " " + this.x_40);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_42",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_41" + " " + this.x_41);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_43",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_42" + " " + this.x_42);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_44",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_43" + " " + this.x_43);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_45",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_44" + " " + this.x_44);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_46",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_45" + " " + this.x_45);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_47",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_46" + " " + this.x_46);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_48",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_47" + " " + this.x_47);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_49",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_48" + " " + this.x_48);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_50",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_49" + " " + this.x_49);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_51",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_50" + " " + this.x_50);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_52",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_51" + " " + this.x_51);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_53",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_52" + " " + this.x_52);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_54",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_53" + " " + this.x_53);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_55",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_54" + " " + this.x_54);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_56",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_55" + " " + this.x_55);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_57",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_56" + " " + this.x_56);

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("x_58",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.x_57" + " " + this.x_57);

		/********************** 102 */

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));

		this.filedInfo.put("secret_29",binary.substring(0, 1).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_29" + " " + this.secret_29);
		this.filedInfo.put("ASCR_AO",binary.substring(1, 2).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_AO" + " " + this.ASCR_AO);
		this.filedInfo.put("ASCR_LO",binary.substring(2, 3).equals("1"));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_LO" + " " + this.ASCR_LO);
		this.filedInfo.put("ASCR_S",Integer.parseInt(binary.substring(3, 8), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_S" + " " + this.ASCR_S);

		/********************** 103 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SCB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_SCB" + " " + this.ASCR_SCB);

		/********************** 104 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SCR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_SCR" + " " + this.ASCR_SCR);

		/********************** 105 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_DU",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DU" + " " + this.ASCR_DU);

		/********************** 106 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_DD",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DD" + " " + this.ASCR_DD);

		/********************** 107 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_DR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DR" + " " + this.ASCR_DR);

		/********************** 108 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_DL",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DL" + " " + this.ASCR_DL);

		/********************** 109 */
		StringBuilder stringBuilder12 = new StringBuilder();

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_30",Integer.parseInt(binary.substring(0, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_30" + " " + this.secret_30);

		stringBuilder12.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder12.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder12.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString12 = stringBuilder12.toString();
		this.filedInfo.put("ASCR_DDU",Integer.parseInt(finalString12, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DDU" + " " + this.ASCR_DDU);

		/********************** 110 */
		StringBuilder stringBuilder13 = new StringBuilder();

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_31",Integer.parseInt(binary.substring(0, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_31" + " " + this.secret_31);

		stringBuilder13.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder13.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder13.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString13 = stringBuilder13.toString();
		this.filedInfo.put("ASCR_DDD",Integer.parseInt(finalString13, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DDD" + " " + this.ASCR_DDD);

		/********************** 111 */
		StringBuilder stringBuilder14 = new StringBuilder();

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_32",Integer.parseInt(binary.substring(0, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_32" + " " + this.secret_32);

		stringBuilder14.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder14.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder14.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString14 = stringBuilder14.toString();
		this.filedInfo.put("ASCR_DDR",Integer.parseInt(finalString14, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DDR" + " " + this.ASCR_DDR);

		/********************** 112 */
		StringBuilder stringBuilder15 = new StringBuilder();

		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("secret_33",Integer.parseInt(binary.substring(0, 4), 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.secret_33" + " " + this.secret_33);

		stringBuilder15.append(binary.substring(4, 8));
		nextLine = reader.readLine();
		stringBuilder15.append(hexToBinary(nextLine.substring(2, 4)));
		nextLine = reader.readLine();
		stringBuilder15.append(hexToBinary(nextLine.substring(2, 4)));
		String finalString15 = stringBuilder15.toString();
		this.filedInfo.put("ASCR_DDL",Integer.parseInt(finalString15, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_DDL" + " " + this.ASCR_DDL);

		/********************** 113 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SRR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_SRR" + " " + this.ASCR_SRR);

		/********************** 114 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SRG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_SRG" + " " + this.ASCR_SRG);

		/********************** 115 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SRB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "putParameterFromRegister" + "this.ASCR_SRB" + " " + this.ASCR_SRB);

		/********************** 116 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SYR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SYR" + " " + this.ASCR_SYR);

		/********************** 117 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SYG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SYR" + " " + this.ASCR_SYG);

		/********************** 118 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SYB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SYR" + " " + this.ASCR_SYG);
		
		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SMR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SYR" + " " + this.ASCR_SYG);
		
		/********************** 120 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SMG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SYR" + " " + this.ASCR_SYG);
		
		/********************** 121 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SMB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SYR" + " " + this.ASCR_SYG);
		
		/********************** 122 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SWR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SWR" + " " + this.ASCR_SWR);

		/********************** 123 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SWG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SWG" + " " + this.ASCR_SWG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_SWB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_SWB" + " " + this.ASCR_SWB);
		

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WCR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WCR" + " " + this.ASCR_WCR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WRR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WRR" + " " + this.ASCR_WRR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WCG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WCG" + " " + this.ASCR_WCG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WRG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WRG" + " " + this.ASCR_WRG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WCB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WCB" + " " + this.ASCR_WCB);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WRB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WRB" + " " + this.ASCR_WRB);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WMR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WMR" + " " + this.ASCR_WMR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WGR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WGR" + " " + this.ASCR_WGR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WMG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WMG" + " " + this.ASCR_WMG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WGG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WGG" + " " + this.ASCR_WGG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WMB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WMB" + " " + this.ASCR_WMB);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WGB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WGB" + " " + this.ASCR_WGB);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WYR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WYR" + " " + this.ASCR_WYR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WBR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WBR" + " " + this.ASCR_WBR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WYG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WYG" + " " + this.ASCR_WYG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WBG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WBG" + " " + this.ASCR_WBG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WYB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WYB" + " " + this.ASCR_WYB);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WBB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WBB" + " " + this.ASCR_WBB);
		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WWR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WWR" + " " + this.ASCR_WWR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WKR",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WKR" + " " + this.ASCR_WKR);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WWG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WWG" + " " + this.ASCR_WWG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WKG",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WKG" + " " + this.ASCR_WKG);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WWB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WWB" + " " + this.ASCR_WWB);

		/********************** 119 */
		line = reader.readLine();
		binary = hexToBinary(line.substring(2, 4));
		this.filedInfo.put("ASCR_WKB",Integer.parseInt(binary, 2));
		Log.d("mDNIe", "setParameterFromRegister" + "this.ASCR_WKB" + " " + this.ASCR_WKB);

	}
}