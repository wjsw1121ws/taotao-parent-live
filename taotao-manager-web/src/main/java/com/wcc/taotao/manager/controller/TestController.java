package com.wcc.taotao.manager.controller;

import com.wcc.taotao.manager.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description: 测试方法
 * @ClassName: TestController
 * @Auther: changchun_wu
 * @Date: 2019/3/15 0:09
 * @Version: 1.0
 **/

@Controller
public class TestController {
    @Autowired
    private TestService testService;
    @RequestMapping(value = "/test/test",method = RequestMethod.GET)
    public String test(Long itemId,Model model){
        String item = testService.test(itemId);
        model.addAttribute("item",item);
        return "test";
    }
}
