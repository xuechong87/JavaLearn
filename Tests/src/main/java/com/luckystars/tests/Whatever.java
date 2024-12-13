package com.luckystars.tests;

import java.util.ArrayList;
import java.util.List;

public class Whatever {
    public static void generateCode(String[] englishFields, String[] chineseFields) {
        if (englishFields.length != chineseFields.length) {
            throw new IllegalArgumentException("两个数组长度不相等");
        }

        List<String> codeLines = new ArrayList<>();
        for (int i = 0; i < englishFields.length; i++) {
            String codeLine = String.format("CustomerInformationList.add(new CellMapper(\"%s\", \"%s\"));", englishFields[i], chineseFields[i]);
            codeLines.add(codeLine);
        }

        // 输出所有生成的代码
        for (String codeLine : codeLines) {
            System.out.println(codeLine);
        }
    }

    public static void main(String[] args) {
//        String[] englishFields = {"key10", "standard", "key11", "nameOne", "legalKindOne", "IDNumberOne", "TeamOne", "addressOne", "key12", "nameTwo", "legalKindTwo", "IDNumberTwo", "TeamTwo", "addressTwo", "key13", "nameThree", "legalKindThree", "IDNumberThree", "TeamThree", "addressThree", "key14", "nameFour", "legalKindFour", "IDNumberFour", "TeamFour", "addressFour"};
//        String[] chineseFields = {"受益所有人类别", "标准", "受益所有人1", "姓名", "身份证明文件种类", "证件号码", "有效期限", "联系地址", "受益所有人2", "姓名", "身份证明文件种类", "证件号码", "有效期限", "联系地址", "受益所有人3", "姓名", "身份证明文件种类", "证件号码", "有效期限", "联系地址", "受益所有人4", "姓名", "身份证明文件种类", "证件号码", "有效期限", "联系地址"};
        String[] englishFields = {"customerName", "LicenseType", "creditCode", "whetherLeasehold", "termStart", "teamEnd", "registCapi", "address", "scope", "stock", "information"};
        String[] chineseFields = {"客户名称", "证照类型", "证照号码", "是否含租赁期", "证照有效期开始", "证照有效期结束", "注册资本（元）", "住所/注册地址", "经营范围", "股权结构和控制权结构", "补充资料补充资料（股权结构图"};

        generateCode(englishFields, chineseFields);
    }
}
