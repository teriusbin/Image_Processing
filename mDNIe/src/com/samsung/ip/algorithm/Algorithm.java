package com.samsung.ip.algorithm;

import java.util.List;

public class Algorithm {
	private String name;
	private boolean enable;
	private List<Parameter> parameter;

	public Algorithm(String name, boolean enable, List<Parameter> paramter) {
		super();
		// TODO Auto-generated constructor stub
		this.name = name;
		this.enable = enable;
		this.parameter = parameter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public List<Parameter> getParameter() {
		return parameter;
	}

	public void setParameter(List<Parameter> parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		return "Algorithm [name=" + name + ", enable=" + enable + ", parameter=" + parameter + "]";
	}

}
