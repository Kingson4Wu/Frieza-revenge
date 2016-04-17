json解析：[2]fastjson 使用
浏览：562 |更新：2014-02-17 11:19
利用阿里的fastjson包对对象进行 json的转化与解析，本篇为第二篇，第一篇讲述的是利用gson进行json数据解析，地址：jingyan.baidu.com/article/e8cdb32b619f8437042bad53.html

常用类型

类型一：JavaBean

类型二：List<JavaBean>

类型三:List<String>

类型四:List<Map<String,Object>>

将上面的四种数据对象转换成json字符串的方法都是一样的

String jsonString = JSON.toJSONString(obj);

工具/原料
fastjson.jar下载地址 pan.baidu.com/s/1i3ysuaD
方法/步骤
1
将json字符串转化成JavaBean对象
Person person = new Person("1","fastjson",1);
//这里将javabean转化成json字符串
String jsonString = JSON.toJSONString(person);
//这里将json字符串转化成javabean对象,
person =JSON.parseObject(jsonString,Person.class);
2
将json字符串转化成List<JavaBean>对象
Person person1 = new Person("1","fastjson1",1);
Person person2 = new Person("2","fastjson2",2);
List<Person> persons = new ArrayList<Person>();
persons.add(person1);
persons.add(person2);
String jsonString = JSON.toJSONString(persons);
System.out.println("json字符串:"+jsonString);
//解析json字符串
List<Person> persons2 = JSON.parseArray(jsonString,Person.class);
3
将json字符串转化成List<String>对象
List<String> list = new ArrayList<String>();
list.add("fastjson1");
list.add("fastjson2");
list.add("fastjson3");
String jsonString = JSON.toJSONString(list);
System.out.println("json字符串:"+jsonString);
//解析json字符串
List<String> list2 = JSON.parseObject(jsonString,new TypeReference<List<String>>(){});
4
将json字符串转化成List<Map<String,Object>>对象
Map<String,Object> map = new HashMap<String,Object>();
map.put("key1", "value1");
map.put("key2", "value2");
Map<String,Object> map2 = new HashMap<String,Object>();
map2.put("key1", 1);
map2.put("key2", 2);
List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
list.add(map);
list.add(map2);
String jsonString = JSON.toJSONString(list);
System.out.println("json字符串:"+jsonString);
//解析json字符串
List<Map<String,Object>> list2 = JSON.parseObject(jsonString,new TypeReference<List<Map<String,Object>>>(){});
5
为了大家学习使用的方便，将测试java项目打包上传到了百度网盘，下载地址
pan.baidu.com/s/1jGskEb0




<http://www.csdn.net/article/2014-09-25/2821866#rd>
