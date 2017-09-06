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

/**
 Python客户端代码：

 #!python
 # encoding: utf-8

 '''
 使用Python语言实现远程调用
 使用Python客户端来进行远程调用，我们可以从https://github.com/bgilmore/mustaine下载，然后安装Hessian的代理客户端Python实现库：
 git clone https://github.com/bgilmore/mustaine.git
 cd mustaine
 sudo python setup.py install

 http://san-yun.iteye.com/blog/1628405
 http://pydoc.net/Python/mustaine/0.1.7/mustaine.parser/

 https://github.com/Kingson4Wu/Frieza-revenge/blob/master/RPC-framework%2Fsrc%2Fmain%2Fjava%2Fapp%2Fdemo%2FBasicService.java

 '''


 from mustaine.client import HessianProxy
 test = HessianProxy("http://localhost:8080/EasyHessian/hello")
 print test._headers
 print test.hello()
 print test.getUser()
 print test.getUser().userName
 user = test.getUser()
 print user.password
 test.setGreeting("Hessian Python")
 print test.hello()
 */