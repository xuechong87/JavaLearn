package com.luckystars.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SliceUtils {

    public static final Integer BATCH_SIZE = 50;

    public static class Slice {
        private Integer start;
        private Integer end;
        public Integer getStart() {
            return start;
        }
        public void setStart(Integer start) {
            this.start = start;
        }
        public Integer getEnd() {
            return end;
        }
        public void setEnd(Integer end) {
            this.end = end;
        }
        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
        }
    }

    public static List<Slice> getSlice(Integer size){
        int sliceCount =(size% BATCH_SIZE ==0 ? size/ BATCH_SIZE :size/ BATCH_SIZE +1);
        List<Slice> sliceList = new ArrayList<>();
        for (int i = 0; i < sliceCount; i++) {
            Slice slice = new Slice();
            slice.setStart(i* BATCH_SIZE);
            if(i == sliceCount-1){
                slice.setEnd(size-1);
            }else{
                slice.setEnd((i+1)* BATCH_SIZE -1);
            }
            sliceList.add(slice);
        }
        return sliceList;
    }

    @Test
    public void test(){
        testSlice(0);
        testSlice(49);
        testSlice(50);
        testSlice(51);
        testSlice(99);
        testSlice(100);
        testSlice(101);
        testSlice(111);

    }

    public static void testSlice(int listSize){
        System.out.println("========listSize:"+listSize+"========");
        List<Integer> intList = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            intList.add(i);
        }
        List<Slice> sliceList = getSlice(listSize);
        for (Slice slice : sliceList) {
            System.out.println(slice);
            List<Integer> splitList = intList.subList(slice.getStart(), slice.getEnd()+1);
            System.out.println(splitList);
        }
        System.out.println("========listSlice end========");
    }
}
