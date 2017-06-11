package kxw;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 简单的蜘蛛爬虫，运行起来异常会很多，暂时先不管
 * @author gbk
 *
 */
public class HTMLPage extends Thread implements WebPage
{
    private static int pageId = 0;
    private static int MAX_PAGENUM = 1000;
    //存放处理过的URL，保证不重复   
    private static Set<String> urls = new HashSet<String>();
    private File localFile;
    private StringBuffer contents;
    private URL url;

    private final  static Pattern p=  Pattern.compile("href=.*?>");

    public HTMLPage(URL url)
    {
        this.url = url;
    }

    /**
     * 将网页下载到本地，用来以后分析
     */
    @Override
    public File getPageFile()
    {
        int ch = 0;
        contents = new StringBuffer();
        pageId++;
        localFile = new File("E:/html/"+pageId+".txt");
        try
        {
            InputStream inputStream = url.openStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            FileOutputStream fileOutputStream = new FileOutputStream(localFile);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            while((ch = inputStreamReader.read()) != -1)
            {
                contents.append((char)ch);
                outputStreamWriter.write(ch);
            }
            outputStreamWriter.close();
            fileOutputStream.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return localFile;
    }

    /**
     * 分析网页，将不重复的url地址添加到候选叶中
     */
    @Override
    public void parse() throws MalformedURLException
    {
        //无法处理内部链接，即不带http   
        String regex ="<a.*?href=http://.*?>.*?</a>";
        Pattern pt=Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher mt=pt.matcher(contents);
        while(mt.find())
        {
            //获取网址   
            Matcher myurl=p.matcher(mt.group());
            while(myurl.find())
            {
                String url = myurl.group().replaceAll("href=|>","");
                //没有做同步，所以最后会稍微多出几个文件   
                if(!urls.contains(url)&&pageId<MAX_PAGENUM)
                {
                    urls.add(url);
                    //新建一个线程，重复上述操作   
                    HTMLPage page = new HTMLPage(new URL(url));
                    page.start();
                }
            }
            System.out.println();
        }
    }

    @Override
    public void run()
    {
        getPageFile();
        try
        {
            parse();
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MalformedURLException
    {
        HTMLPage page = new HTMLPage(new URL("http://www.baidu.com"));
        page.start();
    }
}