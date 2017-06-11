package com.kxw.interpreter2.nonterminalExpression;

import com.kxw.interpreter2.context.Context;
import com.kxw.interpreter2.expression.Expression;

public class Subtract extends Expression  

{  
  
       private Expression left , right ;  
  
       public Subtract(Expression left , Expression right)  
  
       {  
  
              this.left = left ;  
  
              this.right= right ;  
  
       }  
  
       @Override
       public int interpret(Context con)
  
       {  
  
              return left.interpret(con) - right.interpret(con);  
  
       }  
  
}  