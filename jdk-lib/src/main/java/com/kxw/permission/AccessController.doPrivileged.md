<http://huangyunbin.iteye.com/blog/1942509>

AccessController.doPrivileged意思是这个是特别的,不用做权限检查. 

在什么地方会用到呢:加入1.jar中有类可以读取一个文件,现在我们要使用1.jar去做这个事情.但是我们的类本生是没有权限去读取那个文件的,一般情况下就是眼睁睁的看着了.   

但是jiava提供了doPrivileged.在1.jar中如果读取文件的方法是通过doPrivileged来实现的.就不会有后面的检查了,现在我们就可以使用1.jar去读取那个文件了. 

例子: 
package huangyunbin.client;  
  
import java.io.FilePermission;  
import java.security.AccessController;  
import java.security.Permission;  
import java.security.PrivilegedAction;  
  
public class Client  
{  
    public   void  doCheck() {  
                AccessController.doPrivileged( new  PrivilegedAction()  {  
            public  Object run()  {  
                check();  
                return   null ;  
            }  
        } );  
    }  
  
    private   void  check()  {  
        Permission perm  =   new FilePermission( "/1.txt" ,  "read" );  
        AccessController.checkPermission(perm);  
        System.out.println( " TestService has permission " );  
    }  
}  


把这个类打包成client.jar 放到/home/h/client/下 
我们建立个my.policy文件,文件内容是: 
grant codeBase  "file:/home/h/client/*"   {  
     permission java.io.FilePermission  "/1.txt","read";  
 };  


配置文件的意思是 /home/h/client/下面的jar包或class类 可以读取/1.txt. 


现在我们再创建一个项目:创建一个类来调用前面的Client 
public class server  
{  
    public static void main(String[] args)  
    {  
        Client c =new    Client();  
        c.doCheck();  
    }  
}  


运行这个server类.注意这里要用上之前的my.policy文件 
在vm参数中写上这样的: 

Java代码  收藏代码
-Djava.security.manager   
-Djava.security.policy=/home/h/my.policy  


运行,结果是 
TestService has permission 

在配置文件my.policy中我们没有允许server去读取/1.txt,但是现在却可以正常访问.这个就是 AccessController.doPrivileged的作用.