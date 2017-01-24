package com.kxw.java8.function2;

import com.kxw.bean.Kingson;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 利用 Function 接口提供的两个组合函数—— compose 和 andThen 来实现函数的组合
 * <a href='http://www.importnew.com/17209.html'>@link</a>
 */
public class TestComposeAndThen {
    //什么是函数组合？
    //首先需要创建一些小的可重用函数，然后将这些小函数组合为新函数。

    public static void main(String[] args) {
        //定义两个简单的函数—— times2 和 squared
        Function<Integer, Integer> times2 = e -> e * 2;
        Function<Integer, Integer> squared = e -> e * e;

        //使用 compose 和 andThen 将它们连起来
        System.out.println(times2.compose(squared).apply(4));
        // Returns 32

        System.out.println(times2.andThen(squared).apply(4));
        // Returns 64

        //compose 和 andThen 的不同之处是函数执行的顺序不同。compose 函数先执行参数，然后执行调用者，
        // 而 andThen 先执行调用者，然后再执行参数

        /** 开始组合函数 */
        //有一个文章列表，现在需要根据不同的需求来过滤这些文章。
        //首先，我们介绍两个基本功能—— byAuthor 和 byTag——基于作者和标签来过滤文章。
        BiFunction<String, List<Article>, List<Article>> byAuthor =
                (name, articles) -> articles.stream()
                        .filter(a -> a.getAuthor().equals(name))
                        .collect(Collectors.toList());

        BiFunction<String, List<Article>, List<Article>> byTag =
                (tag, articles) -> articles.stream()
                        .filter(a -> a.getTags().contains(tag))
                        .collect(Collectors.toList());

        /*
        两个函数都是 BiFunction——意味着需要两个参数。
        byAuthor 接收作者名称和文章列表两个参数，返回根据作者过滤后的文章列表。
        byTag 与此相同，接收标签和文章列表两个参数，返回根据标签过滤后的文章列表。
        由于 BiFunction 接收两个参数，它只提供 andThen 函数。你不能将一个函数的结果放在一个接收两个参数的函数中，
        因此没有 compose 函数。
        继续，我们还有一个基本功能，需对文章列表从新到旧进行排序，并返回排序后的文章列表。
         */

        Function<List<Article>, List<Article>> sortByDate =
                articles -> articles.stream()
                        .sorted((x, y) -> y.published().compareTo(x.published()))
                        .collect(Collectors.toList());

        Function<List<Article>, Optional<Article>> first =
                a -> a.stream().findFirst();

        //现在，我们已经有了基本的函数，现在看我们怎么利用这些函数来组合成新的函数。

        //首先，我们组合一个返回最近发表的文章列表函数

        Function<List<Article>, Optional<Article>> newest =
                first.compose(sortByDate);

        //使用 first 这个函数以及我们之前创建的 sortByDate，我们能创建一个新的函数，该函数返回给定文章列表的最新文章。
        //我们可以继续通过不同的方式混合这些函数，从而可以组合出不同意义的函数，而不需要重复写代码。

        //找出作者的最新文章
        BiFunction<String, List<Article>, Optional<Article>> newestByAuthor =
                byAuthor.andThen(newest);
        //或者对某一作者的文章进行排序
        BiFunction<String, List<Article>, List<Article>> byAuthorSorted =
                byAuthor.andThen(sortByDate);
        //或者你可能不关心作者，只想根据你喜欢标签获取最新的文章
        BiFunction<String, List<Article>, Optional<Article>> newestByTag =
                byTag.andThen(newest);


        //我想要表达的观点是：通过 Function 接口及其组合功能，可以创建小的代码块，再将其组合来满足你的需求，这样可以可以更简单、更有意思地实现 DRY 原则。
        //就这样了——利用 compose 和 andThen 来使用简单的方式组合功能。

        BiFunction<String, List<Article>, List<Kingson>> kk =
                (tag, articles) -> articles.stream()
                        .map(article -> new Kingson())
                        .collect(Collectors.toList());

    }
}
