package com.wcc.taotao.search.dao;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @Description: solr查询dao层
 * @ClassName: SearchDao
 * @Auther: changchun_wu
 * @Date: 2019/3/18 23:42
 * @Version: 1.0
 **/

public interface SearchDao {
    /**
     * 获取查询结果
     * @param solrQuery
     * @return
     */
    SearchResult getSearchResult(SolrQuery solrQuery) throws Exception;

    /**
     * 根据商品的id获取SearchItem
     * @param itemId
     * @return
     */
    DataResult addSearchItemById(Long itemId) throws Exception;
}
