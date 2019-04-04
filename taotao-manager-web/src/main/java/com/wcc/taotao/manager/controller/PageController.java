package com.wcc.taotao.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 页面显示controller
 * @ClassName: PageController
 * @Auther: changchun_wu
 * @Date: 2019/3/15 20:23
 * @Version: 1.0
 **/

@Controller
public class PageController {

    /**
     * 显示后台管理首页
     * @return
     */
    @RequestMapping(value = "/")
    public String showIndex(){
        return "index";
    }

    /**
     * 显示后台管理中各个页面
     * @param page
     * @return
     */
    @RequestMapping(value = "/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
