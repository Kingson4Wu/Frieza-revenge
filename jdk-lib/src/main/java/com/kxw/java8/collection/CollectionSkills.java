package com.kxw.java8.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kxw.bean.Kingson;

public class CollectionSkills {

    public static void main(String[] args) {
        List<Kingson> list = new ArrayList<>();

        Map<String, String> map = list.stream().collect(
            Collectors.toMap(ele -> String.valueOf(ele.getId()), Kingson::getName));

        Map<String, Kingson> kmap = list.stream().collect(
            Collectors.toMap(ele -> String.valueOf(ele.getId()), ele->ele));

        List<Integer> idList = list.stream().map(Kingson::getId).collect(Collectors.toList());

        Map<Integer, List<Kingson>> kLMap = list.stream().collect(Collectors.groupingBy(Kingson::getId));

        list.stream().map(Kingson::getName).collect(Collectors.joining("','", "('", "')"));
    }
}
