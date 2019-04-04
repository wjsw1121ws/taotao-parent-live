package com.wcc.taotao.manager.service;

import com.wcc.taotao.common.pojo.TreeData;

import java.util.List;

/**
 * @Description: 商品分类接口
 * @ClassName: ItemCatService
 * @Auther: changchun_wu
 * @Date: 2019/3/15 22:47
 * @Version: 1.0
 **/
public interface ItemCatService {
    /**
     * 获取分类列表
     * @param parentId
     * @return
     */
    List<TreeData> getItemCatList(Long parentId);
}
