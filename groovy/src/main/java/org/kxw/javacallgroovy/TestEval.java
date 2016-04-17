package org.kxw.javacallgroovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

//java execute groovy script
public class TestEval {

    public Object doit() {
        ScriptEngineManager factory = new ScriptEngineManager(TestEval.class.getClassLoader());
        ScriptEngine scriptEngine =  factory.getEngineByName("groovy");//或者"Groovy" groovy版本要1.6以上的
        try {
            scriptEngine.put("test", "hello world!");
            scriptEngine.put("outer", this);
            scriptEngine.eval("println test; outer.java_out()");
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        Binding bb = new Binding();
        bb.setVariable("test", "hello Kingson!");
        bb.setProperty("outer", this);
        GroovyShell gs = new GroovyShell(bb);


        return gs.evaluate("println test; outer.java_out()");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestEval te = new TestEval();
        te.doit();

    }

    public void java_out(){
        System.out.println("out from java");
    }

}