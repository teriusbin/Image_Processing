package com.samsung.ip.algorithm;

import java.util.List;

public class AlgorithmItem {
	private String mName;
	private boolean mEnable;
	private int mIndex;

	public AlgorithmItem(String name, boolean enable, int index ) {
		super();
		// TODO Auto-generated constructor stub
		this.mName = name;
		this.mEnable = enable;
		this.mIndex = index;
		
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public boolean isEnable() {
		return mEnable;
	}

	public void setEnable(boolean enable) {
		this.mEnable = enable;
	}

	public int getmIndex() {
		return mIndex;
	}

	public void setmIndex(int mIndex) {
		this.mIndex = mIndex;
	}



	@Override
	public String toString() {
		return "Algorithm [name=" + mName + ", enable=" + mEnable ;
	}

}
