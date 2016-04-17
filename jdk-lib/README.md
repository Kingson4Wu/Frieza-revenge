

1. Sonar和FindBugs的Security - Array is stored directly问题
如果有如下类 
public class DemoClass{ 
    private Object[] objArray; 
        public Object[] getObjArray() 
        { 
        return objArray; 
        } 
        public void setObjArray(Object[] objArray) 
        { 
        this.objArray = objArray; 
        } 
} 
当尝试用Sonar或者FindBugs去检查的话，会出现如下提示： 
Security - Array is stored directly 
--------
java规范建议写成这样 
  private Object[] objArray; 

public Object[] getObjArray() { 
if (null != objArray) { 
return Arrays.copyOf(objArray, objArray.length); 
} else { 
return null; 
} 
} 

public void setObjArray(Object[] objArray) { 
if (null != objArray) { 
this.objArray = Arrays.copyOf(objArray, objArray.length); 
} else { 
this.objArray = null; 
} 
}
------------

2. 方法名，变量名等不要用下划线


3. 条件表达式
条件运算符会做数值类型的类型提升
` Object o1 = true ? new Integer(1) : new Double(2.0);`
o1是Double类型

4. 复合赋值运算符
`i += j;`
`i = i + j;`
直觉上认为，2行代码是等价的，对吧？但结果即不是！JLS（Java语言规范）指出：

复合赋值运算符表达式 E1 op= E2 等价于 E1 = (T)((E1) op (E2)) 其中T是E1的类型，但E1只会被求值一次。
<pre>
使用*=或/=作为例子可以方便说明其中的转型问题：
byte b = 10;
b *= 5.7;
System.out.println(b); // prints 57
 
byte b = 100;
b /= 2.5;
System.out.println(b); // prints 40
 
char ch = '0';
ch *= 1.1;
System.out.println(ch); // prints '4'
 
char ch = 'A';
ch *= 1.5;
System.out.println(ch); // prints 'a'
</pre>

5. 可以用break、continue和有标签的代码块来实现goto
<pre>
向前跳：
label: {
  // do stuff
  if (check) break label;
  // do more stuff
}

向后跳：
label: do {
  // do stuff
  if (check) continue label;
  // do more stuff
  break label;
} while(true);
</pre>


