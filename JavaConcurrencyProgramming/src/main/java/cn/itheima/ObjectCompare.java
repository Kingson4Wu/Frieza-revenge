package cn.itheima;

public class ObjectCompare
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Object o1 = new String("1");
		Object o2 = new String("1");
		System.out.println(o1==o2);	//false
		System.out.println(o1.equals(o2));	//true
		System.out.println(o1);	//1
		System.out.println(o2);	//1
		Object o3 = "1"+"";
		Object o4 = "1"+"";
		System.out.println(o3==o4);	//true
		System.out.println(o3.equals(o4));	//true
		Object o5 = "2"+"";
		Object o6 = get("2","");
		System.out.println(o5==o6);	//false
		System.out.println(o5.equals(o6));	//true
		System.out.println(o5+"__"+o6);	//2__2
		
	}
	public static Object get(String a, String b)
	{
		return a+b;
	}
}
