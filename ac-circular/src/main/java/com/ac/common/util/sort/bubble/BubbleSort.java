package com.ac.common.util.sort.bubble;

import com.ac.common.util.sort.AbstractSort;


public class BubbleSort<T extends Comparable<T>> implements AbstractSort<T>{

	public T[] sort(T[] array) {
		return bubble(array);
	}
	/**
	 * 冒泡排序
	 * @param <T>
	 * @param array
	 * @return
	 */
	private <T extends  Comparable<T>> T[] bubble(T[] array )
	{
		T cache;
		for(int i =0;i<array.length;i++)
		{
			T out = array[i];
			for(int j =i;j<array.length;j++)
			{
				T in = array[j];
				if(out.compareTo(in)<0)
				{
					cache = out;
					array[i] = in;
					array[j] = cache;
				}
			}
		}
		return  array;
	}
}
