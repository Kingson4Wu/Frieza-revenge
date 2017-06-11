package com.kxw.interpreter2.terminalExpression;

import com.kxw.interpreter2.context.Context;
import com.kxw.interpreter2.expression.Expression;

public class Variable extends Expression  

{  
  
       @Override
       public int interpret(Context con)
  
       {  
  
              //this为调用interpret方法的Variable对象  
  
              return con.LookupValue(this);  
  
       }  
  
}  