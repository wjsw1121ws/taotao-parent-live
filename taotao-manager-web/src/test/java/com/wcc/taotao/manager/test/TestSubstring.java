package com.wcc.taotao.manager.test;

import org.junit.Test;


/**
 * @Description:
 * @ClassName: TestSubstring
 * @Auther: changchun_wu
 * @Date: 2019/3/16 1:53
 * @Version: 1.0
 **/
public class TestSubstring {
    @Test
    public void test1(){
        String name = "ABCD.jpg";
        int i = name.lastIndexOf(".");
        String substring = name.substring(i);
        System.out.println(i);
        System.out.println(substring);
    }
}
