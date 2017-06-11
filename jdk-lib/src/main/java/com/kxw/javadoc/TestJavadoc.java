package com.kxw.javadoc;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;
import com.sun.tools.javadoc.Main;

/**
 * Created by kingson.wu on 2016/3/10.
 */
public class TestJavadoc {


    public static void generate() {
        Main.execute(new String[] {
                "-private",
                "-doclet", TestJavadoc.class.getName(),
                "-subpackages", "/",
        });
    }

    public static boolean start(RootDoc rootDoc) {

        for (ClassDoc classDoc : rootDoc.classes()) {

            String className = classDoc.toString();
            AnnotationDesc[] annotations = classDoc.annotations();
            boolean flag = false;
            for(AnnotationDesc desc: annotations) {
                if("@com.kxw.core.annotation.Component".equals(desc.toString())) {
                    flag = true;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        generate();
    }
}
