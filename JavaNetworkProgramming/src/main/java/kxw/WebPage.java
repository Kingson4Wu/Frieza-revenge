package kxw;

import java.io.File;
import java.net.MalformedURLException;

/*
* 定义了WebPage对象的基本操作
*/
public interface WebPage
{
    /**根据网页地址将该网页转换成本地文件*/
    public File getPageFile();

    /**分析网页的内容
     * @throws MalformedURLException */
    public void parse() throws MalformedURLException;
}  