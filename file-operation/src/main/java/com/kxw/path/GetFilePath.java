package com.kxw.path;
import java.io.File;
import java.io.IOException;
/**
 * {<a href='http://www.cnblogs.com/xulianbo/archive/2011/08/26/2154027.html'>@link</a>}
 * file.getPath() getAbsolutePath() getCanonicalPath()区别
 */


public class GetFilePath {

    public static void main(String[] args) throws IOException
    {
        System.out.println("------默认相对路径，取得路径不同-----");
        File f = new File("..\\src\\file");
        System.out.println(f.getPath());
        System.out.println(f.getAbsolutePath());
        System.out.println(f.getCanonicalPath());
        System.out.println("------默认相对路径，取得路径不同-----");
        File f2 = new File(".\\src\\file");
        System.out.println(f2.getPath());
        System.out.println(f2.getAbsolutePath());
        System.out.println(f2.getCanonicalPath());
        System.out.println("------默认绝对路径，取得路径相同-----");
        File f3 = new File("C:\\src\\file");
        System.out.println(f3.getPath());
        System.out.println(f3.getAbsolutePath());
        System.out.println(f3.getCanonicalPath());

//   执行结果为：
//   ------默认相对路径，取得路径不同-----
//   ..\src\file
//   C:\workspace\Tip\..\src\file
//   C:\workspace\src\file
//   ------默认相对路径，取得路径不同-----
//   .\src\file
//   C:\workspace\Tip\.\src\file
//   C:\workspace\Tip\src\file
//   ------默认绝对路径，取得路径相同-----
//   C:\src\file
//   C:\src\file
//   C:\src\file
//
//   比较可以得到
//   getPath()返回的是构造方法里的路径，不做任何处理
//   getAbsolutePath()返回的是 user.dir+getPath()，也就是执行路径加上构造方法中的路径
//   getCanonicalPath()返回的是将符号完全解析的路径，也就是全路径
    }
}