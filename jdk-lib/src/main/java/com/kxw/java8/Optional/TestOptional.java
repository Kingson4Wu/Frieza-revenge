package com.kxw.java8.Optional;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import com.kxw.bean.Kingson;

/**
 * Created by kingsonwu on 17/8/9.
 * 如何正确使用Optional: <a href='https://blog.kaaass.net/archives/764'>@link</a>
 */
public class TestOptional {

    public static void main(String[] args) {

        Optional s = Optional.of("");

        s.ifPresent(System.out::println);

        //使用Optional封装map的返回值
        Optional<Object> value = Optional.ofNullable(new HashMap<>().get("key"));

        Stream.of("a", "c", null, "d")
            .filter(Objects::nonNull)
            .forEach(System.out::println);

    }

    /**
     * 判断非空
     *
     * @param u
     * @return
     */
    public static String getName(Kingson u) {
        return Optional.ofNullable(u)
            .map(user -> user.getName())
            .orElse("Unknown");
    }

    /**
     * 链式调用，而不是一层层判断
     *
     * @param comp
     * @return
     * @throws IllegalArgumentException
     */
    public static String getChampionName(Kingson comp) throws IllegalArgumentException {
        return Optional.ofNullable(comp)
            .map(c -> c.getPerson())
            .map(p -> p.getName())
            .orElseThrow(() -> new IllegalArgumentException("The value of param comp isn't available."));

        /**
         * map  是可能无限级联的, 比如再深一层, 获得用户名的大写形式
         return user.map(u -> u.getUsername())
         .map(name -> name.toUpperCase())
         .orElse(null);
         */
    }

    /**
     * Optional可以用来检验参数的合法性
     *
     * @param name
     * @throws IllegalArgumentException
     */
    public void setName(String name) throws IllegalArgumentException {
        String n = Optional.ofNullable(name).filter(Kingson::isNameValid)
            .orElseThrow(() -> new IllegalArgumentException("Invalid username."));
    }

}
