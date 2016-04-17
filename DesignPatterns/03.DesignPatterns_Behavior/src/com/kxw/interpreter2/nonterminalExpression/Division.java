package com.kxw.interpreter2.nonterminalExpression;

import com.kxw.interpreter2.context.Context;
import com.kxw.interpreter2.expression.Expression;

public class Division extends Expression  

{  
  
       private Expression left , right ;  
  
       public Division(Expression left , Expression right)  
  
       {  
  
              this.left = left ;  
  
              this.right= right ;  
  
       }  
  
       public int interpret(Context con)  
  
       {  
  
              try{  
  
                            return left.interpret(con) / right.interpret(con);  
  
              }catch(ArithmeticException ae)  
  
              {  
  
                     System.out.println("被除数为0！");  
  
                     return -11111;  
  
              }  
  
       }  
  
}  