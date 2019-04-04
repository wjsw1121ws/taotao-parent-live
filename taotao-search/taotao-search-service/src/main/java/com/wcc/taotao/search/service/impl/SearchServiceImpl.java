package com.wcc.taotao.search.service.impl;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.SearchItem;
import com.wcc.taotao.common.pojo.SearchResult;
import com.wcc.taotao.search.dao.SearchDao;
import com.wcc.taotao.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: solr查询service层实现
 * @ClassName: SearchServiceImpl
 * @Author: changchun_wu
 * @Date: 2019/3/30 17:42
 * @Version: 1.0
 **/

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;


    /**
     * solr关键字查询
     * @param queryString 查询的关键字
     * @param page        当前页
     * @param rows        每页显示的记录数
     * @return
     * @throws Exception
     */
    public SearchResult getSearchResult(String queryString, Integer page, Integer rows) throws Exception {
        //1.创建SolrQuery对象
        SolrQuery solrQuery = new SolrQuery();
        //2.数据有效性验证
        if (StringUtils.isNotBlank(queryString)) {
            solrQuery.setQuery(queryString);
        }else {
            solrQuery.setQuery("*:*");
        }
        if (page == null) page = 1;
        if (rows == null) rows = 60;
        //3.设置分页
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);
        solrQuery.set("df", "item_keywords");
        //4.设置高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style=\"color: red\">");
        solrQuery.setHighlightSimplePost("</em>");
        //5.调用dao执行查询
        SearchResult searchResult = searchDao.getSearchResult(solrQuery);
        //6.设置总页数
        long totalCount = searchResult.getTotalCount();
        searchResult.setPageCount(totalCount / rows == 0 ? totalCount / rows : totalCount / rows + 1);
        return searchResult;
    }

    /**
     * 根据商品的id获取SearchItem
     * @param itemId
     * @return
     */
    @Override
    public DataResult addSearchItemById(Long itemId) throws Exception {
        return searchDao.addSearchItemById(itemId);
    }
}
