package com.samsung.ip.algorithm;

public class Child {

	private String name;
	
	private Boolean enabled;
	
	public Child() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Child [name=" + name + ", enabled=" + enabled + "]";
	}

	
	
}
