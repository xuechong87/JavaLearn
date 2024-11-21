package com.luckystars.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;


public class LuckyStringUtil {


    public static String padZero(BigDecimal num, int length) {
        //用0补全长度
        String str = num.toString();
        return StringUtils.leftPad(str,length,'0');
    }
    @Test
    public void testPadZero() {
        System.out.println(padZero(new BigDecimal("93.1"), 4));
    }
}
