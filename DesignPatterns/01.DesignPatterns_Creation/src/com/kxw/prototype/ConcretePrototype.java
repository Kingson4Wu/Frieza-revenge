package com.kxw.prototype;

/**
 * 作者：alaric
 * 时间：2013-7-18下午10:41:39
 * 描述：实现接口
 */
public class ConcretePrototype implements Prototype {

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
            return null;
        }

    }     
    
    
    /*
     public Object deepClone(){  
    ByteArrayOutputStream bos = new ByteArrayOutputStream();   
    ObjectOutputStream oos = new ObjectOutputStream(bos);   
    oos.writeObject(this);   
    ByteArrayInputStream bis = new ByteArrayInputStream(baos.toByteArray())   
    ObjectInputStream ois = new ObjectInputStream(bis);   
    return ois.readObject();  
	} 
     */

}
