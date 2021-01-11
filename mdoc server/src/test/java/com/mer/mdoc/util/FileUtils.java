package com.mer.mdoc.util;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author wxc
 * @date 2021/1/9
 * @version: 1.0.0
 * @title 文件工具类
 */
@Slf4j
public class FileUtils {

    /**
     * 替换单个文件的内容
     * @param file 文件
     * @param pairs 需要替换的内容 键值对 key-旧内容 value-新内容
     */
    public static void replaceContent(File file, Map<String, String> pairs) {
        String content = readFile(file);
        boolean flag = true;
        for (Map.Entry<String, String> entry : pairs.entrySet()) {
            String target = entry.getKey();
            String replacement = entry.getValue();
            if (content.contains(target)) {
                content = content.replaceAll(target, replacement);
                flag = false;
            }
        }
        if (flag) {
            return;
        }
        writeFile(file, content);
    }

    /**
     * 读文件
     * @param file 文件
     * @return
     */
    public static String readFile(File file) {
        StringBuilder builder = new StringBuilder();
        if (file.isDirectory()) {
            return builder.toString();
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            char[] buff = new char[1024];
            int len;
            while( (len = br.read(buff)) != -1 ){
                builder.append(new String(buff, 0, len));
            }
            br.close();
            return builder.toString();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return builder.toString();
        }
    }

    /**
     * 写文件
     * @param file 文件
     * @param content 内容
     */
    public static void writeFile(File file, String content) {
        try {
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            pw.write(content);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public static List<File> getAllFile(String dirPath, String... type) {
        val l = System.currentTimeMillis();
        File dir = new File(dirPath);
        val fileList = getAllFile(dir, type);
        System.out.println("耗时："+ (System.currentTimeMillis() - l) + "ms");
        return fileList;
    }

    public static List<File> getAllFile(File dir, String... type) {
        if (!dir.isDirectory()) {
            return new ArrayList<>();
        }
        List<File> fileList = new ArrayList<>();
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                fileList.addAll(getAllFile(file, type));
                continue;
            }
            val fileType = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            boolean typeFlag = true;
            for (String s : type) {
                typeFlag = false;
                if (fileType.equalsIgnoreCase(s)) {
                    typeFlag = true;
                    break;
                }
            }
            if (!typeFlag) {
                continue;
            }
            fileList.add(file);
        }
        return fileList;
    }

}
