package com.luckystars.utils;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;


public class StringUtil {

    /**
     * 用0补全指定长度
     * @param num    需要补全的BigDecimal数值
     * @param length 补全后的总长度
     * @return 补全0后的字符串表示
     */
    public static String padZero(BigDecimal num, int length) {
        String str = num.toString(); // 将BigDecimal转换为字符串
        int indexOfDot = str.indexOf(".");
        //获取小数位数
        int decimalLength = indexOfDot>=0? str.length() - indexOfDot : 0;
        //将整数位数补全
        return StringUtils.leftPad(str, length+decimalLength, '0');
    }
    @Test
    public void testPadZero() {
        System.out.println(padZero(new BigDecimal("93.333"), 4));
    }



}
