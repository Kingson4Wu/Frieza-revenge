package com.kxw.java8;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by kingsonwu on 17/8/25.
 */
public class TestFile {

    public static void main(String[] args) throws IOException {

        //Files.list
        try (Stream<Path> stream = Files.list(Paths.get("/opt"))) {
            String joined = stream.map(String::valueOf)
                .filter(path -> !path.startsWith("."))
                .sorted().collect(Collectors.joining("; "));
            System.out.println("List: " + joined);
            // => List: /opt/cisco; /opt/local

        }

        //Files.find

        Path start = Paths.get("/home/admin/");
        int maxDepth = 5;
        //目录树层数
        try (Stream<Path> stream = Files.find(start, maxDepth, (path, attr) ->
            String.valueOf(path).endsWith(".js"))) {
            String joined = stream.sorted()
                .map(String::valueOf)
                .collect(Collectors.joining("; "));
            System.out.println("Found: " + joined);
        }

        //Files.walk 与上find功能一致
        //start = Paths.get("/home/admin/");
        //int maxDepth = 5;//目录树层数
        try (Stream<Path> stream = Files.walk(start, maxDepth)) {
            String joined = stream .map(String::valueOf)
                .filter(path -> path.endsWith(".js"))
                .sorted()
                .collect(Collectors.joining("; "));
            System.out.println("walk(): " + joined); }

        //Files.readAllLines
        List<String> lines = Files.readAllLines(Paths.get("res/nashorn.js"));
        lines.add("print('foobar');");
        Files.write(Paths.get("res/nashorn1-modified.js"), lines);

        //Files.lines
        try (Stream<String> stream = Files.lines(Paths.get("res/nashorn.js"))) {
            stream
            .filter(line -> line.contains("print")) .map(String::trim)
            .forEach(System.out::println);
        }

        //Files.newBufferedReader&Files.newBufferedWriter
        Path path = Paths.get("res/nashorn1.js");
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            System.out.println(reader.readLine()); }
         path = Paths.get("res/output.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("print('Hello World');"); }



    }
}
