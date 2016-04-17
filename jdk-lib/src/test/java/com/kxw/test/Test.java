package com.kxw.test;

class Test {
    int x() { return 1; }
    //String x() { return "123"; }
}
//是的！Java语言不允许一个类里有2个方法是『重载一致』的，而不会关心这2个方法的throws子句或返回类型实际是不同的。