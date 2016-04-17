package com.kxw.java8.function2;

import java.util.Date;

/**
 * Created by kingsonwu on 16/2/7.
 */
public class Article {

    String author;
    String tags;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Date published() {

        return new Date();
    }
}
