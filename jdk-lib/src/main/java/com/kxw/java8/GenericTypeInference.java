package com.kxw.java8;

/**
 * <a href='http://stackoverflow.com/questions/30521974/why-does-the-java-8-generic-type-inference-pick-this-overload'>@link</a>
 */
public class GenericTypeInference {

    public static void main(String[] args) {
        print(new SillyGenericWrapper().get());
    }

    private static void print(Object object) {
        System.out.println("Object");
    }

    private static void print(String string) {
        System.out.println("String");
    }

    public static class SillyGenericWrapper {
        public <T> T get() {
            return null;
        }
    }
}