package com.kxw.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArgNameClassVisitor extends ClassVisitor {

    private Map<String, List<String>> nameArgMap = new HashMap<String, List<String>>();

    public ArgNameClassVisitor() {
        super(Opcodes.ASM5);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc,
                                     String signature, String[] exceptions) {
        Type methodType = Type.getMethodType(desc);
        int len = methodType.getArgumentTypes().length;
        List<String> argumentNames = new ArrayList<String>();
        nameArgMap.put(name, argumentNames);
        MethodVisitor visitor = new ArgNameMethodVisitor(Opcodes.ASM5, argumentNames, len);
        return visitor;
    }

    public static void test(String kxw) {

    }

    public Map<String, List<String>> getNameArgMap() {
        return nameArgMap;
    }

    public static void main(String[] args) throws IOException {
        //ClassReader cr=new ClassReader(ArgNameClassVisitor.class.getName());
        ClassReader cr = new ClassReader(HelloWorldAsm.class.getName());
        ArgNameClassVisitor cl = new ArgNameClassVisitor();
        cr.accept(cl, 0);
        for (Map.Entry<String, List<String>> entry : cl.getNameArgMap().entrySet()) {
            System.out.println(entry.getKey());
            for (String str : entry.getValue()) {
                System.out.println("value:" + str);
            }
        }
/**
 * javac -g:none ,加上这个参数就没有了
 * ---
 * javac -g:vars
 * 以及
 * mvn clean install 都可以
 */
    }
}
