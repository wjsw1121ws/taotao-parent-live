package com.wcc.taotao.manager.controller;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.ShowPageDataList;
import com.wcc.taotao.content.service.ContentService;
import com.wcc.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 分类管理controller层
 * @ClassName: ContentController
 * @Auther: changchun_wu
 * @Date: 2019/3/17 2:54
 * @Version: 1.0
 **/

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 获取分类列表
     * @param categoryId 数所分类的id
     * @param page 当前页
     * @param rows 每页显示的记录数
     * @return
     */
    @RequestMapping(value = "/content/query/list",method = RequestMethod.GET)
    @ResponseBody
    public ShowPageDataList getContentList(Long categoryId, Integer page, Integer rows){
        //1.消费服务
        //2.注入服务
        //3.调用服务方法
        ShowPageDataList<TbContent> contentList = contentService.getContentList(categoryId, page, rows);
        //4.返回结果集
        return contentList;
    }

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "/content/save",method = RequestMethod.POST)
    @ResponseBody
    public DataResult addContent(TbContent tbContent){
        //1.消费服务
        //2.注入服务
        //3.调用服务方法
        DataResult dataResult = contentService.addContent(tbContent);
        //4.返回
        return dataResult;
    }
}
