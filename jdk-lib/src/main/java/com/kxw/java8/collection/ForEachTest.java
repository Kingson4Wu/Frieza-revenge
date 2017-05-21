package com.kxw.java8.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.kxw.java8.function2.Article;

/**
 * Created by kingsonwu on 17/4/14.
 *
 * <a href='https://mp.weixin.qq.com/s/Mkyt4tJQgELAHXaWQ8HgKQ'>@link</a>
 */
public class ForEachTest {

    List<Article> articles = new ArrayList<>();
    public static void main(String[] args) {

    }

    public Optional<Article> getFirstJavaArticle() {

        return articles.stream()
            .filter(article -> article.getTagList().contains("Java"))
            .findFirst();
    }

    public List<Article> getAllJavaArticles() {
        return articles.stream()
            .filter(article -> article.getTagList().contains("Java"))
            .collect(Collectors.toList());
    }

    public Map<String, List<Article>> groupByAuthor() {
        return articles.stream()
            .collect(Collectors.groupingBy(Article::getAuthor));
    }


    public Set<String> getDistinctTags() {
        return articles.stream()
            .flatMap(article -> article.getTagList().stream())
            .collect(Collectors.toSet());
    }

}
