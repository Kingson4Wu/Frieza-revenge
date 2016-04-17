package com.kxw.state.openClose;


	/** 
	 *  
	 *作者：alaric 
	 *时间：2013-9-3下午10:12:27 
	 *描述：实现状态类 
	 */  
	public class OpenState  implements State {  
	  
	    @Override  
	    public void change(Context context) {  
	        System.out.println("this is OpenState");  
	        context.setState(new CloseState());  
	    }  
	}  