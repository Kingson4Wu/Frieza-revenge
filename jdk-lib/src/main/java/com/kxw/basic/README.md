+ <http://stackoverflow.com/questions/6547310/in-java-what-is-the-difference-between-this-method-and-method?answertab=votes#tab-top>
In Java, what is the difference between this.method() and method()?
The only time it matters is if you are using OuterClass.this.method() e.g.

```java
class OuterClass {
    void method() { }

    class InnerClass {
        void method() {
            OuterClass.this.method(); // not the same as method().
        }
    }
 }
```

