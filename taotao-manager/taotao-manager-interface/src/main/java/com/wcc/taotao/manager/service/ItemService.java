package com.wcc.taotao.manager.service;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.ShowPageDataList;
import com.wcc.taotao.pojo.TbItem;
import com.wcc.taotao.pojo.TbItemDesc;

/**
 * @Description: 商品列表接口
 * @ClassName: ItemService
 * @Auther: changchun_wu
 * @Date: 2019/3/15 21:22
 * @Version: 1.0
 **/
public interface ItemService {
    /**
     * 显示商品列表
     * @param page 当前页
     * @param rows 每页显示的数据
     * @return
     */
    ShowPageDataList<TbItem> showItem(Integer page, Integer rows);

    /**
     * 商品保存
     * @param item 要保存的商品信息
     * @param desc 要保存的商品描述信息
     * @return
     */
    DataResult addItem(TbItem item,String desc);

    /**
     * 通过商品id查询商品
     * @param itemId
     * @return
     */
    TbItem getItemById(Long itemId);

    /**
     * 通过商品描述中的商品id查询商品
     * @param itemId
     * @return
     */
    TbItemDesc getItemDescById(Long itemId);
}
