package com.kxw.interpreter.context;

import java.util.HashMap;
import java.util.Map;

import com.kxw.interpreter.terminalExpression.Variable;
/**
 * 
 *环境(Context)类定义出从变量到布尔值的一个映射
 */
public class Context {

    private Map<Variable,Boolean> map = new HashMap<Variable,Boolean>();
    
    public void assign(Variable var , boolean value){
        map.put(var, new Boolean(value));
    }
    
    public boolean lookup(Variable var) throws IllegalArgumentException{
        Boolean value = map.get(var);
        if(value == null){
            throw new IllegalArgumentException();
        }
        return value.booleanValue();
    }
}