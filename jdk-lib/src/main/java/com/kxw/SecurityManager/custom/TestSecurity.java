package com.kxw.SecurityManager.custom;

/**
 * Created by Kingson.wu on 2015/8/22.
 */
import java.io.*;

public class TestSecurity
{
    public static void main(String args[])
    {
        try {
            //System.setSecurityManager(new PasswordSecurityManager("123456"));
        } catch (SecurityException se) {
            System.out.println("SecurityManager already set!");
        }
        try {
            //DataInputStream fis = new DataInputStream(new FileInputStream("input.txt"));
            BufferedReader fis = new BufferedReader(new FileReader("input.txt"));
            //DataOutputStream fos = new DataOutputStream( new FileOutputStream("output.txt"));
            BufferedWriter fos = new BufferedWriter(new FileWriter("output.txt"));
            String inputString;
            while ((inputString = fis.readLine()) != null) {
                //fos.writeBytes(inputString);
                //fos.writeByte('\n');
                fos.write(inputString);
                fos.write('\n');
            }
            fis.close();
            fos.close();
        } catch (IOException ioe) {
            System.out.println("I/O failed for SecurityManagerTest.");
        }catch(Exception e)
        {
            System.out.println(e.toString());
        }

    }
}
/**
 * 在一般的情况下，同个线程中，我们用的是同一个类加载器去动态加载所需要的类文件，但是，如果我们设置了SecurityManager的时候，情况就不一样了，当我们设置了安全管理器之后，当前类由于需要用到安全管理器来判断当前类是否有加载类MyFileInputStream的权限，所以当前类会委托SecurityManager来加载MyFileInputStream，而对于SecurityManger来说它就从CLASSPATH指定的路径加载我们的类，所以它没有找到我们的MyFileInputStream类。
 * */