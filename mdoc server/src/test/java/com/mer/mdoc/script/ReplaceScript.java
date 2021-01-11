package com.mer.mdoc.script;

import com.mer.mdoc.util.FileUtils;
import com.mer.mdoc.util.ProjectUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.val;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wxc
 * @date 2021/1/9
 * @version:
 * @title
 */
public class ReplaceScript {

    public static void replaceAllJava(Map<String, String> pairs) {
        long l = System.currentTimeMillis();
        List<File> javaList = FileUtils.getAllFile(ProjectUtils.getProjectJavaDir(), "java");
        for (File file : javaList) {
            FileUtils.replaceContent(file, pairs);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - l) + "ms");
    }

    public static void entityToPlusMapper(String packagePath) throws IOException, TemplateException, URISyntaxException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        //指定模板文件的来源
        String templatePath = ReplaceScript.class.getClassLoader().getResource("template").toURI().getPath();
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        //这是模板的编码
        cfg.setDefaultEncoding("UTF-8");
        //获取模板
        Template template = cfg.getTemplate("Mapper.java");
        //创建FreeMarker的数据模型
        Map<String,String> root = new HashMap<>();
        String dirPath = ProjectUtils.getProjectJavaDir() + File.separator + packagePath.replaceAll("\\.", "/");

        String parentPackage = packagePath.substring(0, packagePath.lastIndexOf("."));
        Writer out = null;
        for (File file : new File(dirPath).listFiles()) {
            val fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if (!"java".equalsIgnoreCase(fileType)) {
                continue;
            }
            val entityName = file.getName().substring(0, file.getName().lastIndexOf("."));
            System.out.println(entityName);

            root.put("entityPackage", packagePath);
            root.put("entityName",entityName);
            //这是输出文件
            File f = new File(ProjectUtils.getProjectJavaDir() + File.separator + parentPackage.replaceAll("\\.", "/") + File.separator + "mapper" + File.separator + entityName + "Mapper.java");
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f)));
            //将模板与数据模型合并
            template.process(root, out);
        }
        out.flush();
        out.close();
    }

}
