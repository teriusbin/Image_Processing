package com.samsung.ip.algorithm;

public class Algorithm_ListView {
	private boolean enable;
	private String name;
		
	public Algorithm_ListView(boolean enable, String name) {
		super();
		this.enable = enable;
		this.name = name;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}		
}
