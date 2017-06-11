package com.kxw.eclipse.jdt;

import org.eclipse.jdt.core.dom.*;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by kingsonwu on 16/7/17.
 */
public class EclipseJdtTest {

    public static void main(String[] args) {
        //The following factory method gives access the Suns javacompiler (when launched from Suns JDK).
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setSource("public class A { int i = 9;  \n int j; \n ArrayList<Integer> al = new ArrayList<Integer>();j=1000; }".toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        final CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        cu.accept(new ASTVisitor() {
            Set<String> names = new HashSet<String>();

            @Override
            public boolean visit(VariableDeclarationFragment node) {
                SimpleName name = node.getName();
                this.names.add(name.getIdentifier());
                System.out.println("Declaration of '" + name + "' at line" + cu.getLineNumber(name.getStartPosition()));
                return false; // do not continue to avoid usage info
            }

            @Override
            public boolean visit(SimpleName node) {
                if (this.names.contains(node.getIdentifier())) {
                    System.out.println("Usage of '" + node + "' at line " + cu.getLineNumber(node.getStartPosition()));
                }
                return true;
            }
        });
    }
}
