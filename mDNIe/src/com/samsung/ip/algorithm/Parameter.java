package com.samsung.ip.algorithm;

public class Parameter {

	private String parameter;
	private boolean enable;
	private int data;
	private float dataf;

	public Parameter(String parameter, boolean enable, int data) {
		super();
		// TODO Auto-generated constructor stub
		this.parameter = parameter;
		this.enable = enable;
		this.data = data;
	}

	public Parameter(String parameter, boolean enable, float data) {
		super();
		// TODO Auto-generated constructor stub
		this.parameter = parameter;
		this.enable = enable;
		this.dataf = data;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Parameter [parameter=" + parameter + ", enable=" + enable + ", data=" + data + ", dataf=" + dataf + "]";
	}
}
