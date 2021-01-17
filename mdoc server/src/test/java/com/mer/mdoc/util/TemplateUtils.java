package com.mer.mdoc.util;

import com.mer.mdoc.script.ReplaceScript;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author mer
 * @version 0.1.4
 * @date 2021/1/16
 */
@Slf4j
public class TemplateUtils {

    /**
     * 通过模板创建文件
     * @param templateFilename 模板文件名
     * @param dataModel 数据
     * @param packagePath 包路径
     */
    public static void createFile(String templateFilename, Map<String,Object> dataModel, String packagePath, String fileName) {
        try {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
            //指定模板文件的来源
            String templatePath = Objects.requireNonNull(ReplaceScript.class.getClassLoader().getResource("template")).toURI().getPath();
            cfg.setDirectoryForTemplateLoading(new File(templatePath));
            //这是模板的编码
            cfg.setDefaultEncoding("UTF-8");
            //获取模板
            Template template = cfg.getTemplate(templateFilename);
            String filePath = ProjectUtils.getProjectJavaDir() + File.separator + packagePath.replaceAll("\\.", "/") + File.separator + fileName + ".java";
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath)));
            //将模板与数据模型合并
            template.process(dataModel, out);
            out.flush();
            out.close();
        } catch (URISyntaxException | TemplateException | IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
