package com.mer.mdoc;

import com.mer.mdoc.modules.system.entity.SysUser;
import com.mer.mdoc.script.GenerateEntityScript;
import com.mer.mdoc.script.ReplaceScript;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Mercurli
 * @date 2019/9/23
 * @version: V1.0
 * @title
 */
@Slf4j
public class TestCase {

    @Test
    public void test1() {
        GenerateEntityScript.genByJson("Note", "com.mer.mdoc.modules.biz.entity");
    }

    @Test
    public void test2() {
        ReplaceScript.entityToPlusMapper("com.mer.mdoc.modules.biz.entity");
    }

    @Test
    public void test3() {
        SysUser sysUser = new SysUser().setName("汪");
        SysUser sysUser1 = new SysUser();
    }

}