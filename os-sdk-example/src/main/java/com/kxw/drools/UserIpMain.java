package com.kxw.drools;

import com.kxw.drools.ip.UserIpInterface;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by kingson.wu on 2016/3/15.
 */
public class UserIpMain {

    private static KieContainer kc;

    static {
        KieServices ks = KieServices.Factory.get();
        kc = ks.getKieClasspathContainer();
    }

    public static void main(String[] args) {
        UserIpInterface dto = new UserIpInterface();
        dto.setName("kxw");
        dto.setSum(1);

        KieSession ksession = kc.newKieSession("UserIp");
        ksession.insert(dto);
        ksession.fireAllRules();
        ksession.dispose();

        System.out.println(dto.getName());
        System.out.println(dto.getSum());
    }
}
