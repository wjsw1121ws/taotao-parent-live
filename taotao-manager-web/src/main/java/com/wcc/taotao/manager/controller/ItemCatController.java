package com.wcc.taotao.manager.controller;

import com.wcc.taotao.common.pojo.TreeData;
import com.wcc.taotao.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description: 商品分类controller
 * @ClassName: ItemCatController
 * @Auther: changchun_wu
 * @Date: 2019/3/15 23:13
 * @Version: 1.0
 **/

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;
    /**
     * 显示所有商品分类信息
     * @param parentId
     * @return
     */
    @RequestMapping(value = "/item/cat/list",method = RequestMethod.POST)
    @ResponseBody
    public List<TreeData> getItemCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
        //1.消费服务
        //2.注入service
        //3.调用service方法
        List<TreeData> itemCatList = itemCatService.getItemCatList(parentId);
        //4.返回结果集
        return itemCatList;
    }
}
