package com.luckystars.utils;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SliceUtils {

    public static final Integer VOUCHER_BATCH_SIZE = 50;

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
        int sliceCount = size<=VOUCHER_BATCH_SIZE ?1 :(size%VOUCHER_BATCH_SIZE==0 ? size/VOUCHER_BATCH_SIZE :size/VOUCHER_BATCH_SIZE+1);
        List<Slice> voucherSliceList = new ArrayList<>();
        for (int i = 0; i < sliceCount; i++) {
            Slice voucherSlice = new Slice();
            voucherSlice.setStart(i*VOUCHER_BATCH_SIZE);
            if(i == sliceCount-1){
                voucherSlice.setEnd(size-1);
            }else{
                voucherSlice.setEnd((i+1)*VOUCHER_BATCH_SIZE-1);
            }
            voucherSliceList.add(voucherSlice);
        }
        return voucherSliceList;
    }

    @Test
    public void test(){
        System.out.println(getSlice(49));
        System.out.println(getSlice(50));
        System.out.println(getSlice(51));
        System.out.println(getSlice(99));
        System.out.println(getSlice(100));
        System.out.println(getSlice(101));
        System.out.println(getSlice(111));
    }
}
