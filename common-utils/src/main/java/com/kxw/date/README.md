+ java 时间戳与Date 相互转化相关函数

java 时间戳与Date 相互转化相关函数
2010-05-06 13:14
一.日期转换为时间戳
public long getTimestamp() throws ParseException{

   Date date1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        .parse("2009/12/11 00:00:00");
        Date date2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
        .parse("1970/01/01 08:00:00");
        long l = date1.getTime() - date2.getTime() > 0 ? date1.getTime()
        - date2.getTime() : date2.getTime() - date1.getTime();
        long rand = (int)(Math.random()*1000);

        return rand;
}
二.时间戳转换为date 型
public void getDate(String unixDate) {

   SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
   SimpleDateFormat fm2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//12小时制
   long unixLong = 0;
   String date = "";
   try {
   unixLong = Long.parseLong(unixDate) * 1000;
   } catch(Exception ex) {
   System.out.println("String转换Long错误，请确认数据可以转换！");
   }
   try {
   date = fm1.format(unixLong);
   date = fm2.format(new Date(date));
   } catch(Exception ex) {
   System.out.println("String转换Date错误，请确认数据可以转换！");
   }
   System.out.println(date);
   }

来源： <http://blog.sina.com.cn/s/blog_8ebe17aa0100ye47.html>

 4public class DateDemo {
 5  public DateDemo() {
 6  }
 7  public static void main(String[] args){
 8    String s = "2004-05-06 03:01:02";
 9    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
10    try {
11      Date d = DateFormat.getDateTimeInstance().parse(s);
12      System.out.println(sdf.format(d));
13    }
14    catch (ParseException ex) {
15      ex.printStackTrace();
16    }
17
18  }
19}
来源： <http://www.blogjava.net/runforever/articles/7001.html>

-------------------------

if ("".equals(shieldStartTime)) {
            params.put("shieldTimeFrom", System.currentTimeMillis() / 1000);
        } else {
            Date date1 = simpleDateFormat.parse(shieldStartTime);
            long timeStamp1 = (long) date1.getTime()/1000;
            System.out.println(timeStamp1);
            params.put("shieldTimeFrom", timeStamp1);
        }
        if ("".equals(shieldEndTime)) {
            params.put("shieldTimeTo", new Long(Integer.MAX_VALUE));// forever
        } else {
            Date date2 = simpleDateFormat.parse(shieldEndTime);
            long timeStamp2 = (long) date2.getTime()/1000;
            System.out.println(timeStamp2);
            params.put("shieldTimeTo", timeStamp2);
        }


------------------

Joda-Time !!!