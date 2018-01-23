package com.kxw.interpreter.test;

import com.kxw.interpreter.context.Context;
import com.kxw.interpreter.expression.Expression;
import com.kxw.interpreter.nonterminalExpression.And;
import com.kxw.interpreter.nonterminalExpression.Not;
import com.kxw.interpreter.nonterminalExpression.Or;
import com.kxw.interpreter.terminalExpression.Constant;
import com.kxw.interpreter.terminalExpression.Variable;

public class Client {

    public static void main(String[] args) {
        Context ctx = new Context();
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        Constant c = new Constant(true);
        ctx.assign(x, false);//内存泄露风险
        ctx.assign(y, true);
        
        Expression exp = new Or(new And(c,x) , new And(y,new Not(x)));
        System.out.println("x=" + x.interpret(ctx));
        System.out.println("y=" + y.interpret(ctx));
        System.out.println(exp.toString() + "=" + exp.interpret(ctx));
    }

}