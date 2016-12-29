package com.ac.common.util.sort.straight;

import com.ac.common.util.sort.AbstractSort;

public abstract class AbstractAtraight <T> implements AbstractSort<T> {

	/**
	 * 插入排序模板 
	 */
	@SuppressWarnings("hiding")
	public <T extends  Comparable<T>>  T[] straightModel(T[] array,int hill,int length,int start)
	{
		int j ;
		for(int i =hill;i<length;i++)
		{
			T cache = array[i];
			//向最低元素做比对，将小的值后移（小指 所在的位置 即代表当前元素）
			for(j = i; j>=start&&j>hill-1 && cache.compareTo(array[j-1])<0; j-=hill)
			{
				array[j] = array[j-hill] ;
			}
			array[j] = cache;//将 当前 元素重新录入 进数组 
		}
		return array;
	}

}
