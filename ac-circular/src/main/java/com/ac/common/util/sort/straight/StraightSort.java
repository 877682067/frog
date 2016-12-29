package com.ac.common.util.sort.straight;

public class StraightSort<T extends Comparable<T>> extends AbstractAtraight<T>{

	/**
	 *  插入 排序
	 * @param <T>
	 * @param array
	 * @return
	 */
	public  T[] straight(T[] array)
	{
		return straightModel(array,1,array.length,0);
	}

	public T[] sort(T[] array) {
		return straight(array);
	}
	
}
