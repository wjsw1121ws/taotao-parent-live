package com.wcc.taotao.item.controller;

import com.wcc.taotao.item.pojo.Item;
import com.wcc.taotao.manager.service.ItemService;
import com.wcc.taotao.pojo.TbItem;
import com.wcc.taotao.pojo.TbItemDesc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 商品controller层
 * @ClassName: ItemController
 * @Author: changchun_wu
 * @Date: 2019/3/30 23:45
 * @Version: 1.0
 **/
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    public String getSearchItemById(@PathVariable Long itemId, Model model){
        //1.消费dubbo服务
        //2.注入service
        //3.调用service方法
        TbItem tbItem = itemService.getItemById(itemId);
        Item item = new Item(tbItem);
        TbItemDesc itemDesc = itemService.getItemDescById(itemId);
        //4.将数据放到模型中
        model.addAttribute("item",item);
        model.addAttribute("itemDesc",itemDesc);
        //5.返回数据
        return "item";
    }
}
