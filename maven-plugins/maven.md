maven-antrun-plugin(运行ant的插件)
<http://lianghao619.iteye.com/blog/1339104>

```xml
<plugin>   
   <artifactId>maven-antrun-plugin</artifactId>    
     <executions>       
        <execution>         
             <phase>compile</phase>        
                   <goals>            
                         <goal>run</goal>        
                   </goals>             
                   <configuration>       
                         <tasks>          
                             <delete file="${project.build.directory}/classes/abc.properties" />        
                         </tasks>          
                   </configuration>        
        </execution>    
     </executions>  
</plugin>  
```

如果你使用Maven进行项目管理，则可以在maven-compiler-plugin编译器的配置项中配置-parameters参数：
```xml

<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.1</version>
    <configuration>
        <compilerArgument>-parameters</compilerArgument>
        <source>1.8</source>
        <target>1.8</target>
    </configuration>
</plugin>
```


maven classloader:<http://maven.apache.org/guides/mini/guide-maven-classloading.html>
<http://mozhenghua.iteye.com/blog/524523>
(1)
<pre>
<plugin>
    </configuration>
        <dependencies>
            <dependency>
</pre>
(2)
`@requiresDependencyResolution`

maven生命周期：<http://v-gimi.iteye.com/blog/1412103>


+ 通过maven-enforcer插件解决类冲突
(ATA)


+ 常用Maven插件介绍:<http://www.cnblogs.com/crazy-fox/archive/2012/02/09/2343722.html>
<http://www.infoq.com/cn/news/2011/04/xxb-maven-7-plugin>
 <http://www.infoq.com/cn/news/2011/05/xxb-maven-8-plugin>

+ jar打包Maven插件制定main方法
```java
<plugin>
                <!-- mvn assembly:assembly -->
                <artifactId>maven-assembly-plugin</artifactId>
                <configuration>
                    <archive>
                        <!--指定 Main Class -->
                        <manifest>
                            <mainClass>com.vipshop.messagecache.start.ServerStart</mainClass>
                        </manifest>
                    </archive>
                    <descriptorRefs>
                        <!-- 打包需要的 jar -->
                        <descriptorRef>jar-with-dependencies</descriptorRef>
                    </descriptorRefs>
                </configuration>
                <executions>
                    <execution>
                        <id>make-assembly</id>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```
