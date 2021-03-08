package com.mer.mdoc.script;

import com.mer.mdoc.util.FileUtils;
import com.mer.mdoc.util.ProjectUtils;
import com.mer.mdoc.util.TemplateUtils;
import lombok.val;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wxc
 * @date 2021/1/9
 */
public class ReplaceScript {

    /**
     * 替换所有的java文件
     * @param pairs
     */
    public static void replaceAllJava(Map<String, String> pairs) {
        long l = System.currentTimeMillis();
        List<File> javaList = FileUtils.getAllFile(ProjectUtils.getProjectJavaDir(), "java");
        for (File file : javaList) {
            FileUtils.replaceContent(file, pairs);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - l) + "ms");
    }

    /**
     * 通过实体类生成Mapper
     * @param entityPackage 实体类包路径
     */
    public static void entityToPlusMapper(String entityPackage) {
        //创建FreeMarker的数据模型
        Map<String, Object> dataModel = new HashMap<>();
        String dirPath = ProjectUtils.getProjectJavaDir() + File.separator + entityPackage.replaceAll("\\.", "/");
        String parentPackage = entityPackage.substring(0, entityPackage.lastIndexOf("."));
        for (File file : new File(dirPath).listFiles()) {
            val fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            if (!"java".equalsIgnoreCase(fileType)) {
                continue;
            }
            val mapperPackage = parentPackage + ".mapper";
            val entityName = file.getName().substring(0, file.getName().lastIndexOf("."));
            dataModel.put("parentPackage", parentPackage);
            dataModel.put("entityName", entityName);
            TemplateUtils.createFile("Mapper.java", dataModel, mapperPackage, entityName + "Mapper");
        }
    }

}
