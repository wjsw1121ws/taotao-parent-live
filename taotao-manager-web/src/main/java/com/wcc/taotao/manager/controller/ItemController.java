package com.wcc.taotao.manager.controller;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.ShowPageDataList;
import com.wcc.taotao.manager.service.ItemService;
import com.wcc.taotao.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 商品列表controller层
 * @ClassName: ItemController
 * @Auther: changchun_wu
 * @Date: 2019/3/15 21:54
 * @Version: 1.0
 **/

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 分页显示商品
     * @param page 当前也
     * @param rows 每页显示的记录数
     * @return
     */
    @RequestMapping(value = "/item/list")
    @ResponseBody
    public ShowPageDataList<TbItem> showItem(Integer page, Integer rows){
        //1.服务消费
        //2.注入service
        //3.调用服务方法
        ShowPageDataList<TbItem> itemList = itemService.showItem(page, rows);
        //4.返回数据
        return itemList;
    }

    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public DataResult addItem(TbItem item,String desc){
        //1.服务消费
        //2.注入service
        //3.调用service中的方法
        DataResult dataResult = itemService.addItem(item, desc);
        //4.返回
        return dataResult;
    }
}
