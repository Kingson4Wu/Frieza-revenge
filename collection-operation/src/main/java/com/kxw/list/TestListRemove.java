package com.kxw.list;


import java.util.*;

public class TestListRemove {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        Student student = new Student();
        student.setId(2);
        student.setName("kxw1");
        Student student2 = new Student();
        student2.setId(65);
        student2.setName("kxw2");
        Student student3 = new Student();
        student3.setId(88);
        student3.setName("abc1");
        Student student4 = new Student();
        student4.setId(98);
        student4.setName("abc2");
        Student student5 = new Student();
        student5.setId(43);
        student5.setName("kxw3");
        Student student6 = new Student();
        student6.setId(86);
        student6.setName("abc3");
        list.add(student);
        list.add(student2);
        list.add(student3);
        list.add(student4);
        list.add(student5);
        list.add(student6);
        Iterator<Student> iterator = list.iterator();
        while (iterator.hasNext()) {
            Student result = iterator.next();
            if (result.getId() < 70) {
                iterator.remove();
            }
        }
        Iterator<Student> iterator2 = list.iterator();
        while (iterator2.hasNext()) {
            Student result = iterator2.next();
            System.out.println(result.getName());
        }

        //反向遍历删除
        String customMainImageStr = "";
        List<String> imageArr=new ArrayList<>();
        imageArr= Arrays.asList(customMainImageStr.split(","));

        ListIterator<String> iterator3 = imageArr.listIterator();
        while (iterator3.hasPrevious()) {
            String str = iterator3.previous();
            if (str.equals("")) {
                iterator3.remove();
            }else{
                break;
            }
        }

        StringBuilder sd=new StringBuilder();
        for(String str :imageArr){
            sd.append(str).append(",");
        }

        customMainImageStr = sd.substring(0, sd.length() - 1);
    }
}

class Student {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
/**
 * Iterator<Student> iterator = list.iterator();
 * while (iterator.hasNext()) {
 * Student result = iterator.next();
 * if (result.getId() < 70) {
 * iterator.remove();
 * }
 * }
 */
