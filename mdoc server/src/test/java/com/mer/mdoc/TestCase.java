package com.mer.mdoc;

import com.mer.mdoc.core.util.IdUtil;
import com.mer.mdoc.core.util.PasswordUtil;
import org.junit.Test;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title
 */
public class TestCase {

    @Test
    public void test1() {
        String salt = "2ASOE47B";
        String username = "admin";
        String password = "123456";
        String encrypt = PasswordUtil.encrypt(username, password, salt);
        System.out.println(encrypt);
        String decrypt = PasswordUtil.decrypt(encrypt, password, salt);
        System.out.println(decrypt);
    }
}