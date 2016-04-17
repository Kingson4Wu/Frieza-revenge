package com.kxw.templateMethod.abstractTemplate;

/** 
 *  
 *作者：alaric 
 *时间：2013-8-9下午8:15:18 
 *描述：抽象模版角色 
 */  
public abstract class AbstractClass {  
  
private	int num1;
private int num2;
	
	
	
	
    public AbstractClass(int num1, int num2) {
	super();
	this.num1 = num1;
	this.num2 = num2;
}

	/** 
     *  
     *作者：alaric 
     *时间：2013-8-9下午8:17:00 
     *描述：模版方法 
     */  
    public final void templateMethod(){  
        int m = getNum1();  
        int n = getNum2(); 
        
        int s = operate(m ,n);  
        show(s);  
    }  
  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-9下午8:21:49 
     *描述：获取第一个操作数 
     */  
    private int getNum1(){  
        return num1;  
    }  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-9下午8:21:49 
     *描述：获取第二个操作数 
     */  
    private int getNum2(){  
        return num2;  
    }  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-9下午8:19:53 
     *描述：算法 
     */  
    public abstract int operate(int m,int n);  
    /** 
     *  
     *作者：alaric 
     *时间：2013-8-9下午8:20:59 
     *描述：显示 
     */  
    public void show(int s){  
        System.out.println("结果是:"+ s);  
    }  
      
} 



