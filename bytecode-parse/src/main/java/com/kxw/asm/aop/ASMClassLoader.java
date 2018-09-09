package com.kxw.asm.aop;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import static org.objectweb.asm.Opcodes.ASM4;

public class ASMClassLoader extends ClassLoader {

    private Set<String> clazzSet = new HashSet<>();

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if (clazzSet.contains(name)) {
            return loadClass(name, false);
        } else {

            Class<?> clazz = findClass(name);
            clazzSet.add(name);
            return clazz;
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

       /* String path = name.replaceAll("\\.", "/") + ".class";
        InputStream is = Thread.currentThread().getContextClassLoader()
            .getResourceAsStream(path);*/

        InputStream is = this.getClass().getResourceAsStream("/TestBean.class");

        ClassWriter cw = new ClassWriter(0);
        //

        ClassReader reader = null;
        try {
            reader = new ClassReader(is);
        } catch (IOException e) {
            e.printStackTrace();
            return super.loadClass(name);
        }
        reader.accept(new AopClassAdapter(ASM4, cw), ClassReader.SKIP_DEBUG);
        //
        byte[] code = cw.toByteArray();
        if (code != null) {
            if (code != null) {
                return defineClass(name + "_Tmp", code, 0, code.length);
            } else {
                throw new ClassNotFoundException();
            }
        } else {
            return super.loadClass(name);
        }
    }
}
