<http://www.importnew.com/17599.html>

查找方法注入：又称为Lookup方法注入，用于注入方法返回结果，也就是说能通过配置方式替换方法返回结果。使用<lookup-method name=”方法名” bean=”bean名字”/>配置；其中name属性指定方法名，bean属性指定方法需返回的Bean。


因为“singleton”Bean在容器中只有一个实例，而“prototype”Bean是每次获取容器都返回一个全新的实例，
所以如果“singleton”Bean在使用“prototype” Bean情况时，那么“prototype”Bean由于是“singleton”Bean的一个字段属性，
所以获取的这个“prototype”Bean就和它所在的“singleton”Bean具有同样的生命周期，所以不是我们所期待的结果。
因此查找方法注入就是用于解决这个问题。

