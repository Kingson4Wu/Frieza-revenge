<http://www.jb51.net/article/48304.htm>
接口的默认方法

Java 8允许我们给接口添加一个非抽象的方法实现，只需要使用 default关键字即可，这个特征又叫做扩展方法，示例如下：
代码如下:
<pre>
interface Formula {
    double calculate(int a);
    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
</pre>
Formula接口在拥有calculate方法之外同时还定义了sqrt方法，实现了Formula接口的子类只需要实现一个calculate方法，默认方法sqrt将在子类上可以直接使用。
代码如下:
<pre>
Formula formula = new Formula() {
    @Override
    public double calculate(int a) {
        return sqrt(a * 100);
    }
};
formula.calculate(100);     // 100.0
formula.sqrt(16);           // 4.0
</pre>
文中的formula被实现为一个匿名类的实例，该代码非常容易理解，6行代码实现了计算 sqrt(a * 100)。在下一节中，我们将会看到实现单方法接口的更简单的做法。
译者注： 在Java中只有单继承，如果要让一个类赋予新的特性，通常是使用接口来实现，在C++中支持多继承，允许一个子类同时具有多个父类的接口与功能，在其他语言中，让一个类同时具有其他的可复用代码的方法叫做mixin。新的Java 8 的这个特新在编译器实现的角度上来说更加接近Scala的trait。 在C#中也有名为扩展方法的概念，允许给已存在的类型扩展方法，和Java 8的这个在语义上有差别。


访问接口的默认方法

还记得第一节中的formula例子么，接口Formula定义了一个默认方法sqrt可以直接被formula的实例包括匿名对象访问到，但是在lambda表达式中这个是不行的。
Lambda表达式中是无法访问到默认方法的，以下代码将无法编译：
复制代码 代码如下:

Formula formula = (a) -> sqrt( a * 100);
Built-in Functional Interfaces

JDK 1.8 API包含了很多内建的函数式接口，在老Java中常用到的比如Comparator或者Runnable接口，这些接口都增加了@FunctionalInterface注解以便能用在lambda上。
Java 8 API同样还提供了很多全新的函数式接口来让工作更加方便，有一些接口是来自Google Guava库里的，即便你对这些很熟悉了，还是有必要看看这些是如何扩展到lambda上使用的。

------------
Java 8:通过反射获取方法参数名 :<http://ju.outofmemory.cn/entry/71666>
Java 8中，使用javac编译器的时候加上-parameters参数的话，会在生成的.class文件中额外存储参数的元信息。

Java编译器的新特性
Java 8终于将这个特性规范化，在语言层面（使用反射API和Parameter.getName()方法）和字节码层面（使用新的javac编译器以及-parameters参数）提供支持。
在Java 8中这个特性是默认关闭的,开启带-parameters参数

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


<http://www.oschina.net/translate/everything-about-java-8>

LongAdder
CompletableFuture
StampedLock

--------
Java 8 接口中的默认方法和静态方法
<https://universsky.gitbooks.io/impatient-java8/content/chapter1.html>

<pre>
设计的初衷

这样看起来，接口和类的界限就有点不明显了，同时也会带来多继承，菱形问题。这样设计的初衷是什么？
　　 由于java8开始支持lambda表达式，可以把函数当做参数传递，最明显的lambda表达式应用场景莫过于对collection的每一个元素应用lambda。如果想为Collection实现lambda表达式

　首先想到的是为Collection的父接口iterator添加抽象方法forEach()。然而，对于已经发布的版本，是没法在给接口添加新方法的同时不影响已有的实现。如果添加了，那么所有的iterator()实现类都要重写这个方法，如果只是jre自己的类库还好说，大量的第三方类库都使用到了java的优秀集合框架，如果都要重写，这是不合理的。
　　因此，如果在Java 8里使用lambda的时候，因为向前兼容的原因而不能用于collection库，那有多糟糕啊。
　　由于上述原因，引入了一个新的概念。虚拟扩展方法，也即通常说的defender方法, 现在可以将其加入到接口，这样可以提供声明的行为的默认实现。
　　简单的说，Java的接口现在可以实现方法了。默认方法带来的好处是可以为接口添加新的默认方法，而不会破坏接口的实现。
　　之前：Java接口纯粹是契约的集合，是一种程序设计的表达方式。从数据抽象的角度看，能够在不定义class的同时又可以定义type，将是程序设计中强大而有用的机制。Java接口就是这些纯粹的接口组成的数据抽象。Java接口只能够拥有抽象方法，它不涉及任何实现，也不能创建其对象(这一点和抽象类一致)。
　　多重继承模型导致额外的复杂性，其中最著名的是钻石问题或者叫“讨嫌的菱形派生”(Dreadful Diamond onDerivation、DDD)。为什么Java接口能够避免多继承的复杂性，关键在于它仅仅包含abstract方法。然而从设计的角度看，Java接口放弃了多继承的内在/固有目标，而显得是一个权宜之计。
　　现在：Java8之前，接口不能升级。因为在接口中添加一个方法，会导致老版本接口的所有实现类的中断。λ表达式作为核心出现，为了配合λ表达式，JDK中Collection库需要添加新的方法，如forEach()，stream()等，于是引入了默认方法(defender methods,Virtual extension methods)。它是库/框架设计的程序员的后悔药。对于以前的遗留代码，大家都不知道有这个新方法，既不会调用，也不会去实现，如同不存在；编写新代码的程序员可以将它视为保底的方法体。类型层次中任何符合override规则的方法，优先于默认方法，因为遗留代码可能正好有同样的方法存在。
　　默认方法，理论上抹杀了Java接口与抽象类的本质区别——前者是契约的集合，后者是接口与实现的结合体。当然，语法上两者的差别和以前一样。这就需要程序员来自觉维护两者的本质区别，把默认方法作为库、框架向前兼容的手段。
　　默认方法的一个好处：多继承的著名的是钻石问题(The Diamond Problem )再次需要关注。因而使以前某些人认为的“为了解决多继承问题而引入接口机制”的说法变成明显的错误——以前也是错误的认识。

JVM中默认方法的实现非常高效，而且方法调用的字节码指令支持默认方法。默认方法使得已有的Java接口能够改进而不会导致编译过程失败。接口java.util.Collection中新增了大量的方法，都是很好的示例，如： stream(), parallelStream(),forEach(), removeIf(),等等。
虽然默认方法很强大，我们还是要谨慎使用它：在将一个方法定义为default方法之前，最好三思是不是必须要这么做，因为它可能在层级复杂的情况下引起歧义和编译错误。
</pre>


------
重复注解
在Java 8中使用@Repeatable注解定义重复注解，实际上，这并不是语言层面的改进，而是编译器做的一个trick，底层的技术仍然相同。

Java 8拓宽了注解的应用场景。现在，注解几乎可以使用在任何元素上：局部变量、接口类型、超类和接口实现类，甚至可以用在函数的异常定义上。

------

<http://listenzhangbin.com/post/2017/01/java8-learning-notes/>

#### 函数式接口
<pre>
函数式接口	           函数描述符	              原始类型特化
Predicate<T>	    T -> boolean	        IntPredicate, LongPredicate, DoublePredicate
Consumer<T>	        T -> void	            IntConsumer, LongConsumer, DoubleConsumer
Function<T,R>	    T -> R	                IntFunction<R>, IntToDoubleFunction, IntToLongFunction, LongFunction<R>, LongToDoubleFunction, LongToIntFunction, DoubleFunction<R>, ToIntFunction<T>, ToDoubleFunction<T>, ToLongFunction<T>
Supplier<T>	        () -> T	                BooleanSupplier, IntSupplier, LongSupplier, DoubleSupplier
UnaryOperator<T>	T -> T	                IntUnaryOperator, LongUnaryOperator, DoubleUnaryOperator
BinaryOperator<T>	(T,T) -> T	            IntBinaryOperator, LongBinaryOperator, DoubleBinaryOperator
BiPredicate<L,R>	(L,R) -> boolean	
BiConsumer<T,U>	    (T,U) -> void	        ObjIntConsumer<T>, ObjLongConsumer<T>, ObjDoubleConsumer<T>
BiFunction<T,U,R>	(T,U) -> R	            ToIntBiFunction<T,U>, ToLongBiFunction<T,U>, ToDoubleBiFunction<T,U>
</pre>

#### Stream

<pre>
操作	         类型	  返回类型	    使用的类型/函数式接口	       函数描述符
filter	    中间	     Stream<T>	     Predicate<T>	         T -> boolean
distinct	中间	     Stream<T>		
skip	    中间	     Stream<T>	     long	
limit	    中间  	 Stream<T>	     long	
map	        中间	     Stream<R>	     Function<T,R>	         T -> R
flatMap	    中间	     Stream<R>	     Function<T, Stream<R>>	 T -> Stream<R>
sorted	    中间	     Stream<R>	     Comparator<T>	         (T,T) -> int
anyMatch	终端	     boolean	     Predicate<T>	         T -> boolean
noneMatch	终端      boolean	     Predicate<T>	         T -> boolean
allMatch	终端      boolean	     Predicate<T>	         T -> boolean
findAny	    终端      Optional<T>		
findFirst	终端      Optional<T>		     
forEach	    终端	     void	         Consumer<T>	         T -> void
collect	    终端	     R	             Collector<T,A,R>	
reduce	    终端  	 Optional<T>	 BinaryOperator<T>	     (T,T) -> T
count	    终端	     long
</pre>