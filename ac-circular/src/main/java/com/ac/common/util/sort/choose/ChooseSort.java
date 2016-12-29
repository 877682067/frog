package com.ac.common.util.sort.choose;

import com.ac.common.util.sort.AbstractSort;
import com.ac.common.util.sort.SortUtil;

public class ChooseSort<T extends Comparable<T>> implements AbstractSort<T>
{

	public T[] sort(T[] array) {
		return choose(array);
	}

	/**
	 * 选择排序
	 * @param <T>
	 * @param array
	 * @return
	 */
	public <T extends Comparable<T>> T[] choose(T[] array)
	{
		for(int i=0; i<array.length;i++)
		{
			int	minIndex = i;
			//find min
			for(int  j = i+1; j<array.length;  j++)
			{
				if(array[j].compareTo(array[minIndex])<0)
				{
					minIndex = j;
				}
			}
			SortUtil.exchange(array,i,minIndex);
		}
		return array;
	}
}
