package com.kxw.state.openClose;

/** 
 *  
 *作者：alaric 
 *时间：2013-9-3下午10:13:37 
 *描述：测试类 
 */  
public class Client {  
  
    /** 
     *作者：alaric 
     *时间：2013-9-3下午7:52:05 
     *描述： 
     */  
    public static void main(String[] args) {  
          
        State state = new OpenState();  
        Context context = new Context(state);  
        //初始状态是A  
        context.change();  
        //装换一次后变成B  
        context.change();  
        //再转换一次后又变成A  
        context.change();  
    }  
  
}  