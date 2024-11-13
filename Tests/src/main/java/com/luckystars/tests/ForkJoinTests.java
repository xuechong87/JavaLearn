package com.luckystars.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;

public class ForkJoinTests {
    public static void main(String[] args) {
        ArrayList<Integer> arr = (ArrayList<Integer>) Arrays.asList(1,2,3,4);

        Integer sum =arr.parallelStream().reduce(Integer::sum).get();


    }
}
