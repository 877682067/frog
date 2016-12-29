package com.ac.common.util.sort.bubble.quick;

import com.ac.common.util.sort.AbstractSort;
import com.ac.common.util.sort.SortUtil;


public class QuickSort<T extends Comparable<T>> implements AbstractSort<T>{

	public T[] sort(T[] array) {
		return quick (array);
	}
	public  T[] quick (T[] array)
	{
		quick(array,0,array.length-1);
		
		return array;
	}
	private void quick (T[] array,int start,int end)
	{
		int middle = array.length>>1;
		quickSort(array,start,end);
		quick(array,0,middle);//左侧
		quick(array,middle,end);//右侧
	}
	private  void quickSort(T[] array,int start,int end)
	{
		int pit = findHinge(array,0,array.length);//寻找枢纽元
		T hinge = array[pit];
		while(start<end)
		{

			while(array[end].compareTo(hinge)<0)
			{
				end--;
			}
			array[pit] = array[end];
			while(array[start].compareTo(hinge)>0)
			{
				start++;
			}
			SortUtil.exchange(array,start,end);
			
		}
		
	}
	private static <T extends Comparable<T>> int  findHinge(T[] array,int left,int right)
	{
		int hinge  = (left+right)>>1;
		//left middle right 由小到大排列
		if(array[left].compareTo(array[hinge])>0)
		{
			SortUtil.exchange(array,left,hinge);
		}
		if(array[right].compareTo(array[left])<0)
		{
			SortUtil.exchange(array,right,left);
		}
		if(array[right].compareTo(array[hinge])<0)
		{
			SortUtil.exchange(array,right,hinge);
		}
		return hinge;
	}
}
