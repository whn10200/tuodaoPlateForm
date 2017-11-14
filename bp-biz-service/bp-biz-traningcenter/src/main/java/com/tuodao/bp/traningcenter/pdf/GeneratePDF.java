package com.tuodao.bp.traningcenter.pdf;

import com.lowagie.text.pdf.BaseFont;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;


import java.io.*;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

@Service
public class GeneratePDF {


    @Value("${transaction.center.pdf.file_path}")
    private static String FILE_PATH="E:\\tuodao\\financeBank\\src\\main\\webapp\\demo";


    @Value("${transaction.center.pdf.out_file_path}")
    private static String OUT_FILE_PATH="D:\\pdf";
;
    private static Logger log = Logger.getLogger(GeneratePDF.class);



    public static String getOutFilePath() {
        return OUT_FILE_PATH;
    }

    public static void setOutFilePath(String outFilePath) {
        OUT_FILE_PATH = outFilePath;
    }

    public static String generatePdfFile(Map map) throws Exception {
        /* 创建配置 */
        Configuration cfg = new Configuration();
        /* 指定模板存放的路径 */
        cfg.setDirectoryForTemplateLoading(new File(FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");

		/* 从上面指定的模板目录中加载对应的模板文件 */
        Template temp;
        if (map.get("type").equals("1"))
        {
            temp= cfg.getTemplate("pdfDemo.ftl");
        }
        else if(map.get("type").equals("11"))
        {
            temp= cfg.getTemplate("newPdfDemoDy.ftl");
        }
        else if(map.get("type").equals("22"))
        {
            temp= cfg.getTemplate("newPdfDemoZy.ftl");
        }
        else
        {
            temp= cfg.getTemplate("pdfDemoDy.ftl");
        }
		/* 创建数据模型 */
        Map root = new HashMap();
        root.put("map", map); //头部信息

        ITextRenderer renderer = new ITextRenderer();

        File file = new File(OUT_FILE_PATH);
        //判断文件夹是否存在,如果不存在则创建文件夹
        if (!file.exists()) {
            file.mkdir();
        }
        String fileName = map.get("no") + ".pdf";
        String outputFile = OUT_FILE_PATH + "/" + fileName;
        OutputStream os = new FileOutputStream(outputFile);

        CharArrayWriter out = new CharArrayWriter();
//        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        temp.process(root, out);
        //log.info(out.toString());
        renderer.setDocumentFromString(out.toString());

//        PDFEncryption pdfEncryption = new PDFEncryption();
//        pdfEncryption.setOwnerPassword("h1d".getBytes());
//        pdfEncryption.setUserPassword("h1d".getBytes());
//        pdfEncryption.setAllowedPrivileges();
//        pdfEncryption.setEncryptionType();
//        renderer.setPDFEncryption(pdfEncryption);
        // 解决中文问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont(FILE_PATH + "/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);

        renderer.layout();
        renderer.createPDF(os);
        System.out.println("项目合同PDF文件生成成功。。。。。。。。。。。。");
        log.info("项目合同PDF文件生成成功。。。。。。。。。。。。。。。。。");
        os.close();
        return fileName;
    }

    /**
     * 对接北京银行修改协议通过HTML生成PDF
     */
    public void createNewPDFFromHtml() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();



        GeneratePDF.generatePdfFile(map);

    }


    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();

        try
        {
            GeneratePDF.generatePdfFile(map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}