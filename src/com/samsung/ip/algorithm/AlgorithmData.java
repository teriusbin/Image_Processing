package com.samsung.ip.algorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

public class AlgorithmData implements Serializable {

	public  ArrayList<HashMap<String, Object>> algorithmList;  
	public  HashMap<String, Object> paramMap;
	
	public String[] keyParmaSet;

	public Integer x_1 = 0;
	public Boolean x_2 = false;
	public Integer x_3 = (Integer)0;
	public Boolean x_4 = true;
	public Boolean x_5 = true;
	public Boolean x_6 = true;
	public Boolean x_7 = false;
	public Integer x_8 =  (Integer) 255;
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
	public Integer x_35= (Integer) 0;
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

	public Integer secret_1  = 0;
	public Integer secret_2  = 0;
	public Integer secret_3  = 0;
	public Boolean secret_4  = false;
	public Integer secret_5  = 0;
	public Integer secret_6  = 0;
	public Boolean secret_7  = false;
	public Integer secret_8  = 0;
	public Integer secret_9  = 0;
	public Boolean secret_10  = true;
	public Boolean secret_11  = true;
	public Boolean secret_12  = false;
	public Integer secret_13  = 0;
	public Integer secret_14  = 0;
	public Integer secret_15  = 0;
	public Integer secret_16  = 0;
	public Integer secret_17  = 0;
	public Integer secret_18  = 0;
	public Integer secret_19  = 0;
	public Integer secret_20  = 0;
	public Integer secret_21  = 0;
	public Integer secret_22  = 0;
	public Integer secret_23  = 0;
	public Integer secret_24  = 0;
	public Integer secret_25  = 0;
	public Integer secret_26  = 0;
	public Integer secret_27  = 0;
	public Integer secret_28  = 0;
	public Boolean secret_29  = false;
	public Integer secret_30  = 0;
	public Integer secret_31  = 0;
	public Integer secret_32  = 0;
	public Integer secret_33  = 0;
	
	public Boolean SLCE_MASK = true;
	public Boolean SLCE_BT = false;
	public Boolean SLCE_MO = false;
	public Boolean SLCE_ON = true;
	public Integer SLCE_G = (Integer) 24;
	public Integer SLCE_CG = (Integer) 36;
	public Boolean SLCE_SO = true;
	public Integer SLCE_SR =(Integer) 0;
	public Integer SLCE_SMD =(Integer) 20;
	public Integer SLCE_IG =(Integer) 179;
	public Integer SLCE_RO =(Integer) 270;
	public Boolean SLCE_RG= true;
	public Integer SLCE_REF_GAIN =(Integer) 0;
	public Integer SLCE_H  =(Integer)6;
	public Integer SLCE_V  =(Integer)6;
	public Integer SLCE_BTH= (Integer)250;
	public Integer SLCE_BSR= (Integer)45;
	public Integer SLCE_DTH= (Integer)3;
	public Integer SLCE_MRO= (Integer)150;

	public boolean NR_E =false;
	
	public Boolean DE_E  = false;
	public Integer DE_G  =(Integer)0;
	public Integer DE_M1 = (Integer)2047;
	public Integer DE_M2 =(Integer)2047;

	public Boolean FE_E = false;
	public Integer FA_ET = (Integer) 20;;
	public Integer FA_SP = (int) 10;
	public Integer FA_SN = (int) 50;
	public Integer FA_MDG = (int) 500;
	public Integer FA_PP = (int) 2954;
	public Integer FA_SC = (int) 28313;
	public Integer FA_D = (int) 454497310;
	public Long FA_DD = (Long)170858368933757474L;

	public Integer FA_PMW =(Integer) 16;
	public Integer FA_FMW =(Integer) 16;
	public Integer FA_SZW =(Integer) 7;
	public Integer FA_SZH =(Integer) 7 ;
	public Integer FA_OC10C= (Integer) 32;
	public Integer FA_OC20C =(Integer) 45;
	
	public Boolean CE_E;
	public Boolean GS_E;
	
	
	public Boolean ASCR_MASK = true;
	public Boolean ASCR_BT = false;
	public Boolean ASCR_MO = false;
	public Boolean ASCR_AO = true;
	public Boolean ASCR_LO = true;
	public Integer ASCR_S = (Integer) 16;
	public Integer ASCR_SCB = (Integer) 103;
	public Integer ASCR_SCR = (Integer) 169;
	public Integer ASCR_DU = (Integer) 12;
	public Integer ASCR_DD = (Integer) 12;
	public Integer ASCR_DR = (Integer) 12;
	public Integer ASCR_DL = (Integer) 12;
	public Integer ASCR_DDU = (Integer) 43691;
	public Integer ASCR_DDD = (Integer) 43691;
	public Integer ASCR_DDR = (Integer) 43691;
	public Integer ASCR_DDL = (Integer) 43691;
	public Integer ASCR_SRR = (Integer) 213;
	public Integer ASCR_SRG = (Integer) 44;
	public Integer ASCR_SRB = (Integer) 42;
	public Integer ASCR_SYR = (Integer) 255;
	public Integer ASCR_SYG = (Integer) 245;
	public Integer ASCR_SYB = (Integer) 99;
	public Integer ASCR_SMR = (Integer) 254;
	public Integer ASCR_SMG = (Integer) 74;
	public Integer ASCR_SMB = (Integer) 255;
	public Integer ASCR_SWR = (Integer) 255;
	public Integer ASCR_SWG = (Integer) 249;
	public Integer ASCR_SWB = (Integer) 248;
	public Integer ASCR_WCR = (Integer) 0;
	public Integer ASCR_WRR = (Integer) 255;
	public Integer ASCR_WCG = (Integer) 255;
	public Integer ASCR_WRG = (Integer) 0;
	public Integer ASCR_WCB = (Integer) 255;
	public Integer ASCR_WRB = (Integer) 0;
	public Integer ASCR_WMR = (Integer) 255;
	public Integer ASCR_WGR = (Integer) 0;
	public Integer ASCR_WMG = (Integer) 0;
	public Integer ASCR_WGG = (Integer) 255;
	public Integer ASCR_WMB = (Integer) 255;
	public Integer ASCR_WGB = (Integer) 0;
	public Integer ASCR_WYR = (Integer) 255;
	public Integer ASCR_WBR = (Integer) 0;
	public Integer ASCR_WYG = (Integer) 255;
	public Integer ASCR_WBG = (Integer) 0;
	public Integer ASCR_WYB = (Integer) 0;
	public Integer ASCR_WBB = (Integer) 255;
	public Integer ASCR_WWR = (Integer) 255;
	public Integer ASCR_WKR = (Integer) 0;
	public Integer ASCR_WWG = (Integer) 255;
	public Integer ASCR_WKG = (Integer) 0;
	public Integer ASCR_WWB = (Integer) 255;
	public Integer ASCR_WKB = (Integer) 0;

	public AlgorithmData(){
		
		algorithmList = new ArrayList<HashMap<String, Object>>();
		
		/*paramMap = new HashMap<String , Object>();
		paramMap.put("algorithmName", "FS");
		paramMap.put("param1", FS_param1);
		paramMap.put("param2", FS_param2);
		paramMap.put("param3", FS_param3);
	//	paramMap.put("param4", FS_param4);
		algorithmList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "HDR_Glare");
		paramMap.put("param1", HDR_Glare_param1);
		paramMap.put("param2", HDR_Glare_param2);
		paramMap.put("param3", HDR_Glare_param3);
		algorithmList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "NR");
		paramMap.put("param1", NR_param1);
		paramMap.put("param2", NR_param2);
		paramMap.put("param3", NR_param3);
		algorithmList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "FA_DE");
		paramMap.put("param1", FA_DE_param1);
		paramMap.put("param2", FA_DE_param2);
		paramMap.put("param3", FA_DE_param3);
		algorithmList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "CS");
		paramMap.put("param1", CS_param1);
		paramMap.put("param2", CS_param2);
		paramMap.put("param3", CS_param3);
		algorithmList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "CC");
		paramMap.put("param1", CC_param1);
		paramMap.put("param2", CC_param2);
		paramMap.put("param3", CC_param3);
		algorithmList.add(paramMap);*/
		
		/*paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "ASCR");
		paramMap.put("param1", ASCR_param1);
		paramMap.put("param2", ASCR_param2);
		paramMap.put("param3", ASCR_param3);
		algorithmList.add(paramMap);*/
		
	}
	

	public int getAlgorithmListSize(){
		
		return algorithmList.size();
		
	}
	public int getParameterMapSize(int i){
		
		return algorithmList.get(i).size();
		
	}
	
	public Object getName(int i, String inputString){
	
		return algorithmList.get(i).get(inputString); 
		
	}
	
	public Object getParameter(int i, String inputString){
		
		return  algorithmList.get(i).get(inputString); 
		
	}
	
	public void getKey(int i){
		
		Log.d("test", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		keyParmaSet = algorithmList.get(i).keySet().toArray(new String[algorithmList.get(i).keySet().size()]);
		
		//return  keySet; 
	}
	
	public int keyCount(){
		
		return  keyParmaSet.length; 
	
	}
	
	public Object getKeyString( int j){
		
		return  keyParmaSet[j]; 
	}
	
	
	
}