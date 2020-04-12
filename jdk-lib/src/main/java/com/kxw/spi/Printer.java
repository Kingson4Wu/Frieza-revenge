package com.kxw.spi;

/**
 * <a href='https://mp.weixin.qq.com/s/8rrHhgUn4VxEgBlw_Oe-qQ'>JAVA 拾遗 —— 关于SPI机制</>
 * interface只定义一个接口，不提供实现。规范的制定方一般都是比较牛叉的存在，这些接口通常位于java，javax前缀的包中。这里的Printer就是模拟一个规范接口。
 */
public interface Printer {
    void print();
}
