package com.luckystars.utils;

import com.google.common.collect.*;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class GuavaUtils {

    //不可变列表
    public void immutableList(){

        List<String> imList = ImmutableList.<String>of("a", "b", "c");

        List<String>  imList2 = ImmutableList.<String>builder().add("a").add("b").add("c").build();

    }

    public void dictionary(){

        Dictionary<String,Object>  dict = new Hashtable<>();

    }


}
