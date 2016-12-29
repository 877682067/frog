package com.ac.common.util.sort.straight.hill;

import com.ac.common.util.sort.straight.AbstractAtraight;

public class HillSort<T extends Comparable<T>> extends AbstractAtraight<T>
{
	
	/**
	 * 希尔排序( 即分组插入排序)
	 */
	public T[] hill(T[] array )
	{
		//将元素分组做排序，初始分组为间隔数组长度 的1/2
		for(int hill = array.length/2;  hill>0; hill/=2 )
		{
			//T cache;//记录当前元素
			//当前元素所在分组元素做插入排序
			array = straightModel(array,hill,array.length,0);
		}
		return array;
	}

	public T[] sort(T[] array) {
		return hill(array);
	}
	public static void main(String[] args)
	{
		Integer[] integer = {81,94,11,96,12,35};
		
		HillSort<Integer> hill= new HillSort<Integer>();
		integer  = hill.sort(integer);
		for(int i = 0;i<integer.length;i++)
		{
			System.out.println(integer[i]);
			
		}
	}
}
