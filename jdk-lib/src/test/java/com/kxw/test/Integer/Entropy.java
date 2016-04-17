package com.kxw.test.Integer;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * <a href='http://blog.jooq.org/2013/10/17/add-some-entropy-to-your-jvm/'>@link</a>
 * <a href='http://www.importnew.com/13859.html'>@link</a>
 * 用反射覆盖JDK的Integer缓存，然后使用自动打包解包（auto-boxing/auto-unboxing）有关
 */
public class Entropy {
    public static void main(String[] args)
            throws Exception {

        // Extract the IntegerCache through reflection
        Class<?> clazz = Class.forName(
                "java.lang.Integer$IntegerCache");
        Field field = clazz.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] cache = (Integer[]) field.get(clazz);

        // Rewrite the Integer cache
        for (int i = 0; i < cache.length; i++) {
            cache[i] = new Integer(
                    new Random().nextInt(cache.length));
        }

        // Prove randomness
        for (int i = 0; i < 10; i++) {
            System.out.println((Integer) i);
        }
    }
}
