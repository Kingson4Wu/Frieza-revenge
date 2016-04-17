+ Groovy入门教程<http://blog.csdn.net/kmyhy/article/details/4200563>


#### Notes

打开Windows下的dos，即‘命令行’，在那个黑底白字的窗口中输入“groovyConsole”，回车，过一会儿就会出现一个GroovyConsole的窗口，在上面的文本域中，输入
println 'Hello, world!' // 打印Hello, world!
然后按Ctrl + R 来运行你的第一个Hello, world程序，在下面的窗口中便可看到运行结果：Hello, world!
来源： <http://www.blogjava.net/BlueSUN/archive/2007/03/17/104391.html>


groovy hello.groovy

——————————————

例子
一个简单的helloworld脚本:

def name='World'; println "Hello $name!"
一个面向对象的复杂些的版本:

class Greet {
  def name
  Greet(who) { name = who[0].toUpperCase() +
                      who[1..-1] }
  def salute() { println "Hello $name!" }
}

g = new Greet('world')  // create object
g.salute()              // Output "Hello World!"
利用现有的Java库:

import org.apache.commons.lang.WordUtils

class Greeter extends Greet {
  Greeter(who) { name = WordUtils.capitalize(who) }
}

new Greeter('world').salute()
在命令行上使用:

groovy -e "println 'Hello ' + args[0]" World

来源： <http://groovy.codehaus.org/Chinese+Home>


