+ ApplicationContext通过ApplicationEvent类和ApplicationListener接口进行事件处理。 如果将实现ApplicationListener接口的bean注入到上下文中，则每次使用ApplicationContext发布ApplicationEvent时，都会通知该bean。 本质上，这是标准的观察者设计模式。
+ (<https://www.cnkirito.moe/2017/09/10/event-1/>)
