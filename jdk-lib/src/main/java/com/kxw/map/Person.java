package com.kxw.map;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Person)) {
            return false;
        }
        Person p = (Person) obj;
        if (this.name.equals(p.name) && this.age == p.age) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.name.hashCode() * this.age;
    }

    @Override
    public String toString() {
        return "姓名：" + this.name + "，年龄：" + this.age;
    }
}