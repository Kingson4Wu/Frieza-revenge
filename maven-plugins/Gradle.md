+ 生成Eclipse项目
call gradle cleanEclipse 
call gradle eclipse 
pause

+ 执行gradle build
1. 生成jar包
```groovy
apply plugin: 'java'
apply plugin: 'eclipse'
sourceCompatibility = 1.7
version = '1.0'
jar {
    manifest {
        attributes 'Implementation-Title': 'Gradle Quickstart',
                   'Implementation-Version': version
    }
}
repositories {
    mavenCentral()
}
dependencies {
    compile group: 'commons-collections', name: 'commons-collections', version: '3.2'
    testCompile group: 'junit', name: 'junit', version: '4.+'
}
test {
    systemProperties 'property': 'value'
}
uploadArchives {
    repositories {
       flatDir {
           dirs 'repos'
       }
    }
}
```
2. 生成war包
```groovy
apply plugin: 'war'
apply plugin: 'jetty'
repositories {
    mavenCentral()
}
dependencies {
    compile group: 'commons-io', name: 'commons-io', version: '1.4'
    compile group: 'log4j', name: 'log4j', version: '1.2.15', ext: 'jar'
}
httpPort = 8080
stopPort = 9451
stopKey = 'foo'
```

+ Build Script Basics
```groovy
task hello {
    doLast {
        println 'Hello world!'
    }
}
//3.A shortcut task definition
task hello2 << {
    println 'Hello world2!'
}
// 4.Build scripts are code
//Using Groovy in Gradle's tasks
task upper << {
    String someString = 'mY_nAmE'
    println "Original: " + someString 
    println "Upper case: " + someString.toUpperCase()
}
task count << {
    4.times { print "$it " }
}
//5.Task dependencies
// Declaration of task that depends on other task
task intro(dependsOn: hello) << {
    println "I'm Gradle"
}
//Lazy dependsOn - the other task does not exist (yet)
task taskX(dependsOn: 'taskY') << {
    println 'taskX'
}
task taskY << {
    println 'taskY'
}
//6.Dynamic tasks
4.times { counter ->
    task "task$counter" << {
        println "I'm task number $counter"
    }
}
//7.Manipulating existing tasks
task0.dependsOn task2, task3
//Accessing a task via API - adding behaviour
task hello3 << {
    println 'Hello Earth'
}
hello3.doFirst {
    println 'Hello Venus'
}
hello3.doLast {
    println 'Hello Mars'
}
hello3 << {
    println 'Hello Jupiter'
}
//8.Shortcut notations
//Accessing task as a property of the build script
task hello4 << {
    println 'Hello world!'
}
hello4.doLast {
    println "Greetings from the $hello.name task."
}
//9.Extra task properties
task myTask {
    ext.myProperty = "myValue"
}
task printTaskProperties << {
    println myTask.myProperty
}
//10.Using Ant Tasks
/*task loadfile << {
    def files = file('../antLoadfileResources').listFiles().sort()
    files.each { File file ->
        if (file.isFile()) {
            ant.loadfile(srcFile: file, property: file.name)
            println " *** $file.name ***"
            println "${ant.properties[file.name]}"
        }
    }
}
*/
//11. Using methods
/*task checksum << {
    fileList('../antLoadfileResources').each {File file ->
        ant.checksum(file: file, property: "cs_$file.name")
        println "$file.name Checksum: ${ant.properties["cs_$file.name"]}"
    }
}
task loadfile << {
    fileList('../antLoadfileResources').each {File file ->
        ant.loadfile(srcFile: file, property: file.name)
        println "I'm fond of $file.name"
    }
}
File[] fileList(String dir) {
    file(dir).listFiles({file -> file.isFile() } as FileFilter).sort()
}
*/
//12.Default tasks(This is equivalent to running gradle clean run.)
defaultTasks 'clean', 'run'
task clean << {
    println 'Default Cleaning!'
}
task run << {
    println 'Default Running!'
}
task other << {
    println "I'm not a default task!"
}
//13. Configure by DAG
task distribution << {
    println "We build the zip with version=$version"
}
task release(dependsOn: 'distribution') << {
    println 'We release now'
}
gradle.taskGraph.whenReady {taskGraph ->
    if (taskGraph.hasTask(release)) {
        version = '1.0'
    } else {
        version = '1.0-SNAPSHOT'
    }
}
```

+ summary
apply plugin: 'war'
apply plugin: 'jetty'

gradle jettyRunWar

----------------

You can exclude a task from being executed using the -x command-line option and providing the name of the task to exclude. Let's try this with the sample build file above.

Example 11.2. Excluding tasks

Output of gradle dist -x test
> gradle dist -x test
:compile
compiling source
:dist
building the distribution

BUILD SUCCESSFUL

Total time: 1 secs

-----------------

gradle build

gradle生成jar包

gradle生成war包

+ Chapter 10. Web Application Quickstart
+ Chapter 7. Java Quickstart
+ Chapter 6. Build Script Basics
