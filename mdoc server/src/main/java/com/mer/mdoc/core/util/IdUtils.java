package com.mer.mdoc.core.util;

import java.util.UUID;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title
 */
public class IdUtils {

    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
