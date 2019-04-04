package com.wcc.taotao.search.service;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.SearchResult;

/**
 * @Description: solr查询service接口
 * @ClassName: SearchService
 * @Author: changchun_wu
 * @Date: 2019/3/30 19:27
 * @Version: 1.0
 **/


public interface SearchService {
    /**
     * solr关键字查询
     * @param queryString 查询的关键字
     * @param page        当前页
     * @param rows        每页显示的记录数
     * @return
     * @throws Exception
     */
    SearchResult getSearchResult(String queryString, Integer page, Integer rows) throws Exception;

    /**
     * 根据商品的id获取SearchItem
     * @param itemId
     * @return
     */
    DataResult addSearchItemById(Long itemId) throws Exception;
}
