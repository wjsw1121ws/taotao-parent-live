package com.wcc.taotao.manager.controller;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.TreeData;
import com.wcc.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 分类管理controller层
 * @ClassName: ContentCategoryController
 * @Auther: changchun_wu
 * @Date: 2019/3/17 0:52
 * @Version: 1.0
 **/

@Controller
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;
    /**
     * 获取分类列表树形结构
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/content/category/list",method = RequestMethod.GET)
    @ResponseBody
    public List<TreeData> getContentCategoryList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        //1.消费服务
        //2.注入服务
        //3.调用服务方法
        List<TreeData> contentList = contentCategoryService.getContentCategoryList(parentId);
        //4.返回结果
        return contentList;
    }

    @RequestMapping(value = "/content/category/create",method = RequestMethod.POST)
    @ResponseBody
    public DataResult addContentCategory(Long parentId,String name){
        //1.服务消费
        //2.注入service
        //3.调用服务方法
        DataResult dataResult = contentCategoryService.addContentCategory(parentId, name);
        //4.返回结果
        return dataResult;
    }
}
