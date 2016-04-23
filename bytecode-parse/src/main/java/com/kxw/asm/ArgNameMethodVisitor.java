package com.kxw.asm;

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import java.util.List;

/**
 * <a href='http://www.oschina.net/code/snippet_2438265_54195'>@link</a>
 */

public class ArgNameMethodVisitor extends MethodVisitor {
    private List<String> argumentNames;
    private int argLen; //变量个数

    public ArgNameMethodVisitor(int api, List<String> argumentNames, int argLen) {
        super(api);
        this.argumentNames = argumentNames;
        this.argLen = argLen;

    }

    //asm遍历局部变量
    @Override
    public void visitLocalVariable(String name, String desc, String signature,
                                   Label start, Label end, int index) {
        if ("this".equals(name)) { //如果是this变量，则掠过
            return;
        }
        if (argLen-- > 0) {
            argumentNames.add(name);
        }
    }

}