package com.mer.mdoc.script;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mer.mdoc.util.FileUtils;
import com.mer.mdoc.util.ProjectUtils;
import com.mer.mdoc.util.TemplateUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 生成实体类
 * @author mer
 * @version 0.1.4
 * @date 2021/1/16
 */
public class GenerateEntityScript {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static void genByJson(String entityName, String entityPackage) {
        String jsonFilepath = ProjectUtils.getProjectTestResources() + File.separator + "bean.json";
        String json = FileUtils.readFile(new File(jsonFilepath));
        JSONObject jsonObject = JSON.parseObject(json);
        StringBuilder content = new StringBuilder();
        boolean dateFlag = false;
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            if (key.equals("id") || key.equals("createTime")) continue;
            Object value = entry.getValue();
            String type = "String";
            if (value instanceof Integer) {
                type = "Integer";
            } else if (value instanceof String && isDateStr(value.toString())) {
                type = "Date";
                dateFlag = true;
            }
            content.append("\t")
                    .append("private ")
                    .append(type)
                    .append(" ")
                    .append(entry.getKey())
                    .append(";")
                    .append("\n\r");
        }
        Map<String,Object> dataModel = new HashMap<>();
        dataModel.put("entityName", entityName);
        dataModel.put("entityPackage", entityPackage);
        dataModel.put("content", content.toString());
        dataModel.put("dateFlag", dateFlag);
        TemplateUtils.createFile("Entity.java", dataModel, entityPackage, entityName);
    }

    private static boolean isDateStr(String str) {
        try {
            dateFormat.parse(str);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

}
