package com.kxw.java8;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestStreamDebug {

    public static void main(String[] args) {
        List<String> strings = Stream.of("C", "A", "B")
            .sorted()
            .collect(toList());
    }
}
