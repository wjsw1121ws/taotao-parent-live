package com.wcc.taotao.manager.test;

import com.wcc.taotao.common.utils.IDUtils;
import org.junit.Test;

/**
 * @Description:
 * @ClassName: TestIDUtils
 * @Auther: changchun_wu
 * @Date: 2019/3/16 20:59
 * @Version: 1.0
 **/
public class TestIDUtils {
    @Test
    public void test(){
        System.out.println(IDUtils.genItemId());
        System.out.println(IDUtils.genImageName());
    }
}
