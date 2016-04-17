package com.google.guava.io;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Multiset;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.io.Resources;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * {<a href='http://ifeve.com/google-guava-io/'>@link</a>}
 */
public class TestIO {
    public void test() throws IOException {


        File file = new File("d://kxw.txt");
        //Read the lines of a UTF-8 text file
        ImmutableList<String> lines = Files.asCharSource(file, Charsets.UTF_8).readLines();
        //Count distinct word occurrences in a file
        Multiset<String> wordOccurrences = HashMultiset.create(
                Splitter.on(CharMatcher.WHITESPACE)
                        .trimResults()
                        .omitEmptyStrings()
                        .split(Files.asCharSource(file, Charsets.UTF_8).read()));
        //SHA-1 a file
        HashCode hash = Files.asByteSource(file).hash(Hashing.sha1());
        //Copy the data from a URL to a file

        URL url = new URL("");
        Resources.asByteSource(url).copyTo(Files.asByteSink(file));

        /**
         * 文件操作
         除了创建文件源和文件的方法，Files类还包含了若干你可能感兴趣的便利方法。

         createParentDirs(File)	必要时为文件创建父目录
         getFileExtension(String)	返回给定路径所表示文件的扩展名
         getNameWithoutExtension(String)	返回去除了扩展名的文件名
         simplifyPath(String)	规范文件路径，并不总是与文件系统一致，请仔细测试
         fileTreeTraverser()	返回TreeTraverser用于遍历文件树
         */
    }
}
