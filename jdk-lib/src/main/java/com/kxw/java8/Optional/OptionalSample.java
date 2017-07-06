package com.kxw.java8.Optional;

import java.util.Optional;

public class OptionalSample {

    //Optional 应该只用于返回类型

    public static void main(String[] args) {
        Optional<String> optional = Optional.empty();
        System.out.println(optional.orElse("kxw"));
    }
}
