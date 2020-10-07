package com.kxw.invokedynamic;

/**
 * MethodHandle与反射Method区别，invokedynamic指令
 */
public class ToUpperCaseGenerator {
   /* private static final MethodHandle BSM =
        new MethodHandle(MH_INVOKESTATIC,
            ToUpperCase.class.getName().replace('.', '/'),
            "bootstrap",
            MethodType.methodType(
                CallSite.class, Lookup.class, String.class, MethodType.class, String.class).toMethodDescriptorString());

    public static void main(String[] args) throws IOException {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        cw.visit(V1_7, ACC_PUBLIC | ACC_SUPER, "ToUpperCaseMain", null, "java/lang/Object", null);
        MethodVisitor mv = cw.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitInvokeDynamicInsn("toUpperCase", "()Ljava/lang/String;", BSM, "Hello");
        mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
        mv.visitInsn(RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
        cw.visitEnd();

        Files.write(Paths.get("ToUpperCaseMain.class"), cw.toByteArray());
    }*/
}

