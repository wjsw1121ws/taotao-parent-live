package com.wcc.taotao.search.dao.impl;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.SearchItem;
import com.wcc.taotao.common.pojo.SearchResult;
import com.wcc.taotao.search.dao.SearchDao;
import com.wcc.taotao.search.mapper.SearchMapper;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: solr查询dao实现
 * @ClassName: SearchDaoImpl
 * @Auther: changchun_wu
 * @Date: 2019/3/18 23:45
 * @Version: 1.0
 **/

@Repository
public class SearchDaoImpl implements SearchDao{

    @Autowired
    private HttpSolrClient solrClient;

    @Autowired
    private SearchMapper searchMapper;

    /**
     * 获取查询结果
     * @param solrQuery
     * @return
     */
    @Override
    public SearchResult getSearchResult(SolrQuery solrQuery) throws Exception{
        //1.注入solrClient
        //2.获取查询响应对象
        QueryResponse response = solrClient.query(solrQuery);
        //3.获取文档集合对象
        SolrDocumentList documentList = response.getResults();
        //4.创建结果集对象
        List<SearchItem> itemList = new ArrayList<>();
        //5.获取高亮
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        for (SolrDocument document : documentList) {
            SearchItem searchItem = new SearchItem();
            searchItem.setId(Long.parseLong(document.get("id").toString()));
            searchItem.setSell_point(document.get("item_sell_point").toString());
            Map<String, List<String>> map = highlighting.get(document.get("id"));
            List<String> item_title = map.get("item_title");
            String highlight = "";
            if (item_title!=null&&item_title.size()!=0){
                highlight = item_title.get(0);
            }else {
                highlight = document.get("item_title").toString();
            }
            searchItem.setTitle(highlight);
            searchItem.setCategory_name(document.get("item_category_name").toString());
            searchItem.setImage(document.get("item_image").toString());
            searchItem.setPrice(Long.parseLong(document.get("item_price").toString()));
            itemList.add(searchItem);
        }

        SearchResult searchResult = new SearchResult();
        searchResult.setItemList(itemList);
        searchResult.setTotalCount(documentList.getNumFound());
        return searchResult;
    }

    /**
     * 根据商品的id获取搜索条目
     * @param itemId
     * @return
     */
    @Override
    public DataResult addSearchItemById(Long itemId) throws Exception{
        //1. 注入mapper
        //2.调用mapper方法
        SearchItem searchItem = searchMapper.getSearchItemById(itemId);
        //3.创建文档对象
        SolrInputDocument document = new SolrInputDocument();
        //4.想文档对象中添加商品搜索条目的信息
        document.addField("id",searchItem.getId().toString());
        document.addField("item_title",searchItem.getTitle());
        document.addField("item_sell_point",searchItem.getSell_point());
        document.addField("item_price",searchItem.getPrice());
        String image = searchItem.getImage().split(",")[0];
        document.addField("item_image",image);
        document.addField("item_category_name",searchItem.getItem_desc());
        document.addField("item_desc",searchItem.getCategory_name());
        //5.将文档保存到索引库
        solrClient.add(document);
        //6.提交
        //7.返回结果
        return null;
    }
}
