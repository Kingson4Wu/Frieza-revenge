package com.kxw.jvm.instrumentation.agentmainjar;

import java.io.IOException;

/**
 * Created by kingsonwu on 17/4/3.
 *
 * @author kingsonwu
 * @date 2017/04/03
 */
public class TestJarPath {

        public static void main(String[] args) throws IOException {
            System.out.println(BugFixAgent.class.getResource("").getPath());
            System.out.println(BugFixAgent.class.getClassLoader().getResource("./class/typo.fix").getPath());


        }
}
