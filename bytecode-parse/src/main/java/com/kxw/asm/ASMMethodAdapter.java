package com.kxw.asm;

public class ASMMethodAdapter {

    public static void main(String[] args) {
       /* //生成与旧方法同名的方法
        MethodVisitor mv = cv.visitMethod(access,name,desc,signature,exceptions);
        //开始方法体
        mv.visitCode();
        //获取本地ID
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, "util/XXXUtil", "getLocalIds", desc, false);
        mv.visitVarInsn(ASTORE, 1);
        //调用旧方法
        mv.visitVarInsn(ALOAD, 1);
        mv.visitMethodInsn(INVOKESTATIC, cname, newName, desc, false);
        mv.visitVarInsn(ASTORE, 2);
        //保存到list
        mv.visitTypeInsn(NEW, "java/util/ArrayList");
        mv.visitInsn(DUP);
        mv.visitMethodInsn(INVOKESPECIAL, "java/util/ArrayList", "<init>", "()V", false);
        mv.visitVarInsn(ASTORE, 3);
        mv.visitVarInsn(ALOAD, 3);
        19 mv.visitVarInsn(ALOAD, 2);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "addAll", "
        (Ljava/util/Collection;)Z", false);
        mv.visitInsn(POP);
        //获取异地ID
        mv.visitVarInsn(ALOAD, 0);
        mv.visitMethodInsn(INVOKESTATIC, "util/XXXUtil", "getXXXXIds", desc, false);
        mv.visitVarInsn(ASTORE, 4);
        //调用远程服务方法
        mv.visitVarInsn(ALOAD, 4);
        mv.visitMethodInsn(INVOKESTATIC, methodInfo.remoteClassName,
            methodInfo.remoteMethodName, "(Ljava/util/List;)Ljava/util/List;", false);
        mv.visitVarInsn(ASTORE, 5);
        //类型转换
        mv.visitVarInsn(ALOAD, 5);
        mv.visitLdcInsn(Type.getType(methodInfo.localEntityClass));
        mv.visitMethodInsn(INVOKESTATIC, "util/BeanUtil", "convert","
        (Ljava/util/List;Ljava/lang/Class;)Ljava/util/List;", false);
        mv.visitVarInsn(ASTORE, 6);
        //添加到list
        mv.visitVarInsn(ALOAD, 3);
        mv.visitVarInsn(ALOAD, 6);
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/ArrayList", "addAll", "
        (Ljava/util/Collection;)Z", false);
        //返回list
        mv.visitInsn(POP);
        mv.visitVarInsn(ALOAD, 3);
        mv.visitInsn(ARETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();*/
    }
}
