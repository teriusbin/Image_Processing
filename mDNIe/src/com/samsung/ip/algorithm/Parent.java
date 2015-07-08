package com.samsung.ip.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parent {

	private String name;

	private List<Child> children;

	public Parent() {
		super();
	}
	
	

	public Parent(String name, List<Child> children) {
		super();
		this.name = name;
		this.children = children;
	}



	public List<Child> getChildren() {
		return children;
	}

	public String getName() {
		return name;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Parent [name=" + name + ", children=" + children + "]";
	}

}

class Test{
	
	public void test(){
		List<Parent> parents = new ArrayList<>();
		
		parents.add(new Parent("ctx", new ArrayList<Child>()));
		parents.add(new Parent("cty", new ArrayList<Child>()));
		
		for(Parent parent: parents){
			if(parent.equals("ctx")){
				
			}
		}
		
		
		HashMap<String, Parent> pMap = new HashMap<>();
		
		pMap.put("ctx", new Parent("ctx", new ArrayList<Child>()));
		pMap.put("cta", new Parent("cta", new ArrayList<Child>()));
		
		Parent parent = pMap.get("ctx");
		if(parent == null){
			
		}
		
	}
}