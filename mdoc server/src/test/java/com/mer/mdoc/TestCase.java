package com.mer.mdoc;

import com.mer.mdoc.core.util.IdUtil;
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
        System.out.println(IdUtil.generateId().length());
    }
}
