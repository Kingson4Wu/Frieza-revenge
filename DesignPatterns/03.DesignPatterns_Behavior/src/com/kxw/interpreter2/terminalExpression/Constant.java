package com.kxw.interpreter2.terminalExpression;

import com.kxw.interpreter2.context.Context;
import com.kxw.interpreter2.expression.Expression;


//终结符表达式角色  

public class Constant extends Expression  

{  

     private int i ;  

     public Constant(int i)  

     {  

            this.i = i;  

     }  

     @Override
     public int interpret(Context con)

     {  

            return i ;  

     }  

}  