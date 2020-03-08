package com.zyaml.nai.comom;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: 994
 * @Date: 2020-03-06 23:03
 */
class ContionsDictionaryTest {
    ContionsDictionary contionsDictionary = new ContionsDictionary();

    @Test
    void match() {
        String str1 = "名称";
        String str2 = "不存在的词";

        String c = contionsDictionary.match(str1);
        Assert.assertEquals("name",c);

        c = contionsDictionary.match(str2);
        Assert.assertEquals("",c);
    }
}