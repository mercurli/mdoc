package com.mer.mdoc.util;

import java.io.File;

/**
 * @author wxc
 * @date 2021/1/9
 * @version:
 * @title 文件路径工具类
 */
public class ProjectUtils {

    private static final String PROJECT_DIR = System.getProperty("user.dir");

    public static final String JAVA_DIR = "src/main/java";

    public static final String TEST_RESOURCES_DIR = "src/test/resources";

    public static String getProjectDir() {
        return PROJECT_DIR;
    }

    public static String getProjectJavaDir() {
        return PROJECT_DIR + File.separator + JAVA_DIR;
    }

    public static String getProjectTestResources() {
        return PROJECT_DIR + File.separator + TEST_RESOURCES_DIR;
    }

}
