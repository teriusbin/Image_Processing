package com.samsung.ip.algorithm;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.samsung.ip.R;

import android.util.Log;

public class AlgorithmEnableData implements Serializable {
	public ArrayList<HashMap<String, Object>> algorithmEableList;
	public HashMap<String, Object> paramMap;
	public ArrayList<AlgoSequence> algoSequence;
	public static final String TAG = "AlgorithmEnableData";
	public AlgorithmEnableData mAlgoEnableData;
	public Map<String, Object> filedInfo;
	public String[] algoList;
	public ArrayList<String[]> parameterArrayList;

	// 알고리즘 enable 설정
	public boolean SLCE_Enable = false;
	public boolean NR_Enable = false;
	public boolean DE_FA_Enable = false;
	public boolean CS_Enable = false;
	public boolean CC_Enable = false;
	public boolean ASCR_Enable = false;

	// 파라미터 enable 설정

	// SLCE 알고리즘
	public boolean SLCE_MASK = false;
	public boolean SLCE_BT = false;
	public boolean SLCE_MO = false;
	public boolean SLCE_ON = false;
	public boolean SLCE_G = false;
	public boolean SLCE_CG = false;
	public boolean SLCE_SO = false;
	public boolean SLCE_SR = false;
	public boolean SLCE_SMD = false;
	public boolean SLCE_IG = false;
	public boolean SLCE_RO = false;
	public boolean SLCE_RG = false;
	public boolean SLCE_REF_GAIN = false;
	public boolean SLCE_H = false;
	public boolean SLCE_V = false;
	public boolean SLCE_BTH = false;
	public boolean SLCE_BSR = false;
	public boolean SLCE_DTH = false;
	public boolean SLCE_MRO = false;

	// NR 알고리즘
	public boolean NR_E = false;

	// FA/DE
	public boolean DE_E = false;
	public boolean DE_G = false;
	public boolean DE_M1 = false;
	public boolean DE_M2 = false;

	public boolean FE_E = false;
	public boolean FA_ET = false;
	public boolean FA_SP = false;
	public boolean FA_SN = false;
	public boolean FA_MDG = false;
	public boolean FA_PP = false;
	public boolean FA_SC = false;
	public boolean FA_D = false;
	public boolean FA_DD = false;
	public boolean FA_PMW = false;
	public boolean FA_FMW = false;
	public boolean FA_SZW = false;
	public boolean FA_SZH = false;
	public boolean FA_OC10C = false;
	public boolean FA_OC20C = false;

	// CS 알고리즘
	public boolean CE_E = false;
	public boolean CS_E = false;

	// CC 알고리즘
	public boolean TestValue = false;

	

	// ASCR 알고리즘
	public boolean ASCR_MASK = false;
	public boolean ASCR_BT = false;
	public boolean ASCR_MO = false;
	public boolean ASCR_AO = false;
	public boolean ASCR_LO = false;
	public boolean ASCR_S = false;
	public boolean ASCR_SCB = false;
	public boolean ASCR_SCR = false;
	public boolean ASCR_DU = false;
	public boolean ASCR_DD = false;
	public boolean ASCR_DR = false;
	public boolean ASCR_DL = false;
	public boolean ASCR_DDU = false;
	public boolean ASCR_DDD = false;
	public boolean ASCR_DDR = false;
	public boolean ASCR_DDL = false;
	public boolean ASCR_SRR = false;
	public boolean ASCR_SRG = false;
	public boolean ASCR_SRB = false;
	public boolean ASCR_SYR = false;
	public boolean ASCR_SYG = false;
	public boolean ASCR_SYB = false;
	public boolean ASCR_SMR = false;
	public boolean ASCR_SMG = false;
	public boolean ASCR_SMB = false;
	public boolean ASCR_SWR = false;
	public boolean ASCR_SWG = false;
	public boolean ASCR_SWB = false;
	public boolean ASCR_WCR = false;
	public boolean ASCR_WRR = false;
	public boolean ASCR_WCG = false;
	public boolean ASCR_WRG = false;
	public boolean ASCR_WCB = false;
	public boolean ASCR_WRB = false;
	public boolean ASCR_WMR = false;
	public boolean ASCR_WGR = false;
	public boolean ASCR_WMG = false;
	public boolean ASCR_WGG = false;
	public boolean ASCR_WMB = false;
	public boolean ASCR_WGB = false;
	public boolean ASCR_WYR = false;
	public boolean ASCR_WBR = false;
	public boolean ASCR_WYG = false;
	public boolean ASCR_WBG = false;
	public boolean ASCR_WYB = false;
	public boolean ASCR_WBB = false;
	public boolean ASCR_WWR = false;
	public boolean ASCR_WKR = false;
	public boolean ASCR_WWG = false;
	public boolean ASCR_WKG = false;
	public boolean ASCR_WWB = false;
	public boolean ASCR_WKB = false;

	// 필드 맵 생성 함수
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
	public AlgorithmEnableData() {

	}

	public AlgorithmEnableData(String[] algoList, ArrayList<String[]> parameterArrayList) {
		// algoSequence = new ArrayList<AlgoSequence>();
		algorithmEableList = new ArrayList<HashMap<String, Object>>();
		this.algoList = algoList;
		this.parameterArrayList = parameterArrayList;

		// field Map 을 생성하기 위하여 AlgorithmData 객체를 받아야 한다.
		// 맵핑을 통하여 멤버변수에 접근할 수 있고, 자동으로 hashmap 생성 가능
		filedInfo = toMap(this);

		// 생성자를 통해서 알고리즘과 파라미터 해쉬맵 생성
		algorithmEableList = new ArrayList<HashMap<String, Object>>();
		int index = 0;
		for (String algo : algoList) {
			paramMap = new HashMap<String, Object>();

			// 알고리즘 이름 해쉬맵에 삽입
			paramMap.put("algorithmName", algo);
			String temp = algo + "_Enable";
			paramMap.put("algoEnable", filedInfo.get(temp));
			Log.d(TAG, "name :" + algo + "value :" + filedInfo.get(temp));

			String[] parameterList = parameterArrayList.get(index++);
			for (String para : parameterList) {

				paramMap.put(para, filedInfo.get(para));
				Log.d(TAG, "name :" + para + " value:" + filedInfo.get(para));

			}
			algorithmEableList.add(paramMap);
		}
		// 자동으로 AlgorithmList 생성

	}

	public void addAlgoSequce(String str, int sequence, int index) {
		algoSequence.add(new AlgoSequence(str, sequence, index));
		Log.d(TAG, "add str:" + str + " sequence:" + sequence);
	}

	public class AlgoSequence implements Serializable {
		private String algoName;
		private int algoSequence;
		private int index;

		public AlgoSequence(String algoName, int algoSequence, int index) {
			super();
			this.algoName = algoName;
			this.algoSequence = algoSequence;
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		public String getAlgoName() {
			return algoName;
		}

		public void setAlgoName(String algoName) {
			this.algoName = algoName;
		}

		public int getAlgoSequence() {
			return algoSequence;
		}

		public void setAlgoSequence(int algoSequence) {
			this.algoSequence = algoSequence;
		}

	}
}
