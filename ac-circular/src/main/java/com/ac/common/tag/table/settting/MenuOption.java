package com.ac.common.tag.table.settting;

import java.util.LinkedList;
import java.util.List;


public class MenuOption {
	private List<Integer> length =  new LinkedList<Integer>();
	private List<String> name =  new LinkedList<String>();
	
	public  void addOption(int length)
	{
		String name = length+"";
		if(length < 0)
		{
			name = "全部";
		}
		this.length.add(length);
		this.name.add(name);
	}
	public List<Integer> getLength() {
		return length;
	}
	public List<String> getName() {
		return name;
	}

}
