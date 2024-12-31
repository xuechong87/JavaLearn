package com.luckystars.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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



    /**
     * 转成中文数字
     */
    public static String toChinese(BigDecimal num) {
        final String[] CN_UPPER_NUMBER = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
        final String[] CN_UPPER_MONETRAY_UNIT = {"分", "角", "元", "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟"};

        if (num == null || num.compareTo(BigDecimal.ZERO) == 0) {
            return "零";
        }
        // 转换为字符串，并去掉小数点
        String numStr = num.stripTrailingZeros().toPlainString();
        // 处理负数
        boolean negative = numStr.startsWith("-");
        if (negative) {
            numStr = numStr.substring(1);
        }
        StringBuilder sb = new StringBuilder();
        int len = numStr.length();
        for (int i = 0; i < len; i++) {
            int digit = numStr.charAt(i) - '0';
            int unitLen = len - i - 1;
            if (digit != 0) {
                sb.append(CN_UPPER_NUMBER[digit]).append(CN_UPPER_MONETRAY_UNIT[unitLen]);
            } else if (sb.length() > 0 && !sb.substring(sb.length() - 1).equals(CN_UPPER_NUMBER[0])) {
                sb.append(CN_UPPER_NUMBER[0]);
            }
        }
        // 添加负号
        if (negative) {
            sb.insert(0, "负");
        }
        return sb.toString();
    }


    public String toString(){
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }



}
