package com.kxw.asm.aop;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class AopClassAdapter extends ClassVisitor implements Opcodes {
    public AopClassAdapter(int api, ClassVisitor cv) {
        super(api, cv);
    }

    public void visit(int version, int access, String name,
                      String signature, String superName, String[] interfaces) {
        //更改类名，并使新类继承原有的类。
        super.visit(version, access, name + "_Tmp", signature, name, interfaces);
        {//输出一个默认的构造方法
            MethodVisitor mv = super.visitMethod(ACC_PUBLIC, "<init>",
                "()V", null, null);
            mv.visitCode();
            mv.visitVarInsn(ALOAD, 0);
            mv.visitMethodInsn(INVOKESPECIAL, name, "<init>", "()V");
            mv.visitInsn(RETURN);
            mv.visitMaxs(1, 1);
            mv.visitEnd();
        }
    }

    public MethodVisitor visitMethod(int access, String name,
                                     String desc, String signature, String[] exceptions) {
        if ("<init>".equals(name)) {
            return null;//放弃原有类中所有构造方法
        }
        if (!name.equals("halloAop")) {
            return null;// 只对halloAop方法执行代理
        }
        //
        MethodVisitor mv = super.visitMethod(access, name,
            desc, signature, exceptions);
        return new AopMethod(this.api, mv);
    }
}

class AopMethod extends MethodVisitor implements Opcodes {
    public AopMethod(int api, MethodVisitor mv) {
        super(api, mv);
    }

    public void visitCode() {
        super.visitCode();
        this.visitMethodInsn(INVOKESTATIC, "org/more/test/asm/AopInterceptor", "beforeInvoke", "()V");
    }

    public void visitInsn(int opcode) {
        if (opcode == RETURN) {//在返回之前安插after 代码。
            mv.visitMethodInsn(INVOKESTATIC, "org/more/test/asm/AopInterceptor", "afterInvoke", "()V");
        }
        super.visitInsn(opcode);
    }
}
