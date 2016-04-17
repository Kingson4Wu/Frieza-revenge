package com.kxw.reflection2;

import java.lang.reflect.Array;

public class ArrayTester2
{
	public static void main(String args[])
	{
		int[] dims = new int[] { 5, 10, 15 };//
		Object array = Array.newInstance(Integer.TYPE, dims);//三维数组
		Object arrayObj = Array.get(array, 3);//下标为3
		Class<?> cls = arrayObj.getClass().getComponentType();
		//范围对象的主键类型,即二维数组
		System.out.println(cls);
		arrayObj = Array.get(arrayObj, 5);//下标为3，5
		Array.setInt(arrayObj, 10, 37);//下标为3，5，10的值设为37
		int arrayCast[][][] = (int[][][]) array;
		System.out.println(arrayCast[3][5][10]);
	}
}