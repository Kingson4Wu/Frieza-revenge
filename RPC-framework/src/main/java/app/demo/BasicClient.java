package app.demo;

import app.demo2.BasicAPI2;
import com.caucho.hessian.client.HessianProxyFactory;

public class BasicClient {
    public static void main(String[] args) throws Exception {
        //String url = "http://127.0.0.1:8080/Hessian/hello";
        String url = "http://localhost:8080/EasyHessian/hello";
        HessianProxyFactory factory = new HessianProxyFactory();
        //BasicAPI basic = (BasicAPI) factory.create(BasicAPI.class, url);
        BasicAPI2 basic = (BasicAPI2) factory.create(BasicAPI2.class, url);
        System.out.println("Hello:" + basic.hello());
        System.out.println("Hello:" + basic.getUser());
        System.out.println("Hello:" + basic.getUser().getUserName());
        System.out.println("Hello:" + basic.getUser().getPassword());
        basic.setGreeting("HelloGreeting");
        System.out.println("Hello:" + basic.hello());
    }
}