package com.wcc.taotao.search.mapper;

import com.wcc.taotao.common.pojo.SearchItem;

import java.util.List;

/**
 * @Description: solr检索Mapper接口
 * @ClassName: SearchMapper
 * @Auther: changchun_wu
 * @Date: 2019/1/23 23:40
 * @Version: 1.0
 **/
public interface SearchMapper {
    /**
     * @Author: changchun_wu
     * @Date: 2019/1/24 0:14
     * @Description: 导入类目到索引库mapper接口
     **/
    List<SearchItem> getSearchItemList();

    /**
     * 根据商品的id获取SearchItem
     * @param itemId
     * @return
     */
    SearchItem getSearchItemById(Long itemId);
}
