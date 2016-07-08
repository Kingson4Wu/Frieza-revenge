package com.kxw.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ToutiaoArticleDemo {

    public static void main(String[] args) throws IOException {
        String url = "http://toutiao.com/a6303423188190904577/";
        print("Fetching %s...\n", url);
        Document doc = Jsoup.connect(url).get();
    }

    private static void print(String msg, Object... args) {
        System.out.println(String.format(msg, args));
    }
}
