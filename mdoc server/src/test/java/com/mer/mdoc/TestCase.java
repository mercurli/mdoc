package com.mer.mdoc;

import com.mer.mdoc.script.ReplaceScript;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

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
        try {
            ReplaceScript.entityToPlusMapper("com.mer.mdoc.modules.system.entity");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}