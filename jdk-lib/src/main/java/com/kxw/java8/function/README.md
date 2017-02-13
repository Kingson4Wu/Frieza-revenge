在java.util.function中定义的函数式接口。总的来说，分为如下这几类：

功能型接口：Function Interface Function<T,R> 抽象方法：R apply(T t)
生产型接口：Interface Supplier<T> 抽象方法：T get()
消费型接口：Interface Consumer<T> 抽象方法：void accept(T t)
断言型接口：Interface Predicate<T> 抽象方法：boolean test(T t)