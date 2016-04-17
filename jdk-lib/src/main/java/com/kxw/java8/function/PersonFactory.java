package com.kxw.java8.function;

interface PersonFactory<P extends Person> {
    P create(String firstName, String lastName);
}