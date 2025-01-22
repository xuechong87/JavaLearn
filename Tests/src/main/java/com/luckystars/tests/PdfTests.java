package com.luckystars.tests;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.Test;
import sun.misc.Resource;

public class PdfTests {

    public static String PATH ;
    static {
        String userDir = System.getProperty("user.dir");
        String resourceDir = userDir + "/src/main/resources";
        System.out.println("Resource directory path: " + resourceDir);
        PATH = resourceDir;
    }


    @Test
    public void test() throws Throwable {
        String downloadUrl = null;
        Map<String, Object> dynamicData = new HashMap<>();

        dynamicData.put("@[key1]", "11aaa");
        dynamicData.put("${key2}", "合同编号");
        dynamicData.put("${key3}", "起租日");
        dynamicData.put("${key4}", "合同金额（元）");
        dynamicData.put("${key5}", "租赁期数（期）");
        dynamicData.put("${key6}", "留购价款（元）");
        dynamicData.put("${key7}", "开户行");
        dynamicData.put("${key8}", "户名");
        dynamicData.put("${key9}", "银行账号");
        dynamicData.put("${key10}", "大额支付行号");

        dynamicData.put("${customerName}", "中文名称");
        dynamicData.put("${contractNo}", "John Doe");
        dynamicData.put("${leaseDate}", "John Doe");
        dynamicData.put("${amount}", "John Doe");
        dynamicData.put("${term}", "John Doe");
        dynamicData.put("${purchasePrice}", "John Doe");
        dynamicData.put("${openBankName}", "John Doe");
        dynamicData.put("${accountTitle}", "John Doe");
        dynamicData.put("${bankAccount}", "John Doe");
        dynamicData.put("${openBank}", "John Doe");
        dynamicData.put("${items}", "John Doe");
        dynamicData.put("${today}", "John Doe");

        // 使用ResourceLoader加载资源
        InputStream fis = new FileInputStream(PATH + File.separator +"repaymentDetail.docx");

        XWPFDocument doc = new XWPFDocument(fis);

        // 替换 Word 模板中的占位符
        replacePlaceholders(doc, dynamicData);

        // 将修改后的 Word 内容转换为 PDF
        wordToPdf(doc, "测试文件");
    }



    private static void replacePlaceholders(XWPFDocument doc, Map<String, Object> dynamicData) throws Exception{
        // 替换段落中的占位符
        for (XWPFParagraph para : doc.getParagraphs()) {
            replaceInParagraph(para, dynamicData);
        }
        // 替换表格中的占位符
        for (XWPFTable table : doc.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
//                    String txt = cell.getText();
//                    if(dynamicData.containsKey(txt)){
//                        cell.getParagraphs().clear();
//                         // 设置单元格内容
//                        String txtToWrite = dynamicData.get(txt).toString();
////                        XWPFParagraph newPara = cell.getParagraphs().get(0); // 添加一个新的段落
//                        XWPFParagraph newPara = cell.addParagraph(); // 添加一个新的段落
//                        XWPFRun newRun = newPara.createRun(); // 在新段落中创建一个运行
//                        newRun.setText(txtToWrite); // 设置运行的文本内容为 txtToWrite
////                        newRun.setFontFamily("华文宋体");
//
//
//
////                        List<XWPFRun> newrunsList = Arrays.asList(newRun);
////                        Field runsListField = XWPFParagraph.class.getDeclaredField("runs");
////                        runsListField.setAccessible(true);
////                        List<XWPFRun> runsList = (List<XWPFRun>) runsListField.get(newPara);
////                        runsList.clear();
////                        runsList.add(newRun);
////                        runsListField.set(newPara, newrunsList);
////
////                        List<IRunElement> newirunsList = Arrays.asList(newRun);
////                        Field irunsListField = XWPFParagraph.class.getDeclaredField("iruns");
////                        irunsListField.setAccessible(true);
////                        List<IRunElement> irunsList = (List<IRunElement>) irunsListField.get(newPara);
////                        irunsList.clear();
////                        irunsList.add(newRun);
////                        irunsListField.set(newPara, newirunsList);
//
//
//
////                        cell.getParagraphs().add(newPara);
//                        cell.setParagraph(newPara);
//
//                        System.out.println("txtToWrite: " + txtToWrite);
//                    }
                    for (XWPFParagraph para : cell.getParagraphs()) {
                        replaceInParagraph(para, dynamicData);
                    }
                }
            }
        }
    }


    private static void replaceInParagraph(XWPFParagraph paragraph, Map<String, Object> variables) {
        List<XWPFRun> runs = paragraph.getRuns();
        for (int i = 0; i < runs.size(); i++) {
            XWPFRun run = runs.get(i);
            String text = run.getText(0);
            if (text!= null) {
                for (Map.Entry<String, Object> entry : variables.entrySet()) {
                    String variable = entry.getKey();
                    Object value = entry.getValue();
                    if (text.contains(variable)) {
                        text = text.replace(variable, value.toString());
                        run.setText(text, 0);
                    }
                }
            }
        }
    }


    private static void mergeRuns(XWPFParagraph paragraph) {
        List<XWPFRun> runs = paragraph.getRuns();
        if (runs.size() <= 1) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (XWPFRun run : runs) {
            sb.append(run.getText(0));
        }
        XWPFRun firstRun = runs.get(0);
        firstRun.setText(sb.toString(), 0);
        for (int i = 1; i < runs.size(); i++) {
            paragraph.removeRun(i);
        }
    }


    private void wordToPdf(XWPFDocument document, String fileName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        String pdfFileName = fileName + sdf.format(new Date()) +".pdf";
        try {

            String pdfPath = PATH + File.separator + pdfFileName;
            File outFile = new File(pdfPath);
                OutputStream out = new FileOutputStream(outFile);
                PdfOptions options1 = PdfOptions.create();  //gb2312
                PdfConverter.getInstance().convert(document, out, options1);
//            }
        } catch (Exception e) {
            throw new RuntimeException("word转PDF失败", e);
        }
    }

}
