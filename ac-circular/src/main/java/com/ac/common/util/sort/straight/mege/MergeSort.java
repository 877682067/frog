package com.ac.common.util.sort.straight.mege;

import com.ac.common.util.sort.straight.AbstractAtraight;
/*
 * 归并排序
 */
public class MergeSort<T extends Comparable<T>> extends AbstractAtraight<T>
{
	

	public T[] merge(T[] array)
	{
		merge(array,0,array.length);
		return array;
	}
	private void merge(T[] array,int left,int right)
	{
		if(left>=right)
			return;
		int middle =  (left+right) >> 1;
		merge(array,left,middle);
		merge(array,++middle,right);
		//归并且排序
		merge(array,left,middle,right);
	}
	private void  merge(T[] array,int left,int middle,int right)
	{
		//利用插入排序的思想做合并并排序处理 
		super.straightModel(array,middle,array.length,left);
	}
	public T[] sort(T[] array) {
		return merge(array);
	}
}
