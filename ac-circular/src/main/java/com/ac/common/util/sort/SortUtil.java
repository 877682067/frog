package com.ac.common.util.sort;


public class SortUtil {

	/**
	 * 堆排序
	 */
	public static <T extends Comparable<T>> T[] heap(T[] array)
	{
		
		
		return array;
	}
	public static <T extends Comparable<T>> void exchange(T[] array,int a,int b)
	{
		T cache = array[a];
		array[a] = array[b];
		array[b] = cache; 
	}
	
}
