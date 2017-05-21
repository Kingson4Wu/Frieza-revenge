package com.kxw.java8.function2;

import java.util.Date;
import java.util.List;

/**
 * Created by kingsonwu on 16/2/7.
 */
public class Article {

    String author;
    String tags;

    List<String> tagList;

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

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }
}
