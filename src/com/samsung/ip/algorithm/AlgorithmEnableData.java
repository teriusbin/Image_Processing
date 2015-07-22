package com.samsung.ip.algorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class AlgorithmEnableData implements Serializable {
	public  ArrayList<HashMap<String, Object>> algorithmEableList;
	public  HashMap<String, Object> paramMap;
	
	public AlgorithmEnableData(){
		algorithmEableList = new ArrayList<HashMap<String, Object>>();
		
		paramMap = new HashMap<String , Object>();
		paramMap.put("algorithmName", "FS");
		paramMap.put("algo",false);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", false);
		paramMap.put("param4", false);
		paramMap.put("param5", true);
		paramMap.put("param6", false);
		algorithmEableList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "HDR_Glare");
		paramMap.put("algo",true);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", false);
		paramMap.put("param4", false);
		paramMap.put("param5", false);
		paramMap.put("param6", true);
		algorithmEableList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "NR");
		paramMap.put("algo",false);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", true);
		paramMap.put("param4", false);
		paramMap.put("param5", false);
		paramMap.put("param6", false);
		algorithmEableList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "FA_DE");
		paramMap.put("algo",false);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", false);
		paramMap.put("param4", false);
		paramMap.put("param5", false);
		paramMap.put("param6", false);
		algorithmEableList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "CS");
		paramMap.put("algo",false);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", false);
		paramMap.put("param4", false);
		paramMap.put("param5", false);
		paramMap.put("param6", false);
		algorithmEableList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "CC");
		paramMap.put("algo",false);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", false);
		paramMap.put("param4", false);
		paramMap.put("param5", false);
		paramMap.put("param6", false);
		algorithmEableList.add(paramMap);
		
		paramMap= new HashMap<String , Object>();
		paramMap.put("algorithmName", "ASCR");
		paramMap.put("algo",false);
		paramMap.put("param1", false);
		paramMap.put("param2", false);
		paramMap.put("param3", false);
		paramMap.put("param4", false);
		paramMap.put("param5", false);
		paramMap.put("param6", false);
		algorithmEableList.add(paramMap);
		
	}
}
