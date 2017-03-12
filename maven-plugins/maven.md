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