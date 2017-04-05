package com.kxw.jvm.instrumentation.agentmain;

/**
 * Created by kingsonwu on 17/4/3.
 *
 * @author kingsonwu
 * @date 2017/04/03
 */
class HeaderUtility {

    static boolean isPriorityCall(String request) {
        return ("X-Pirority").equals(request);
    }

}