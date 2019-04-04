package com.wcc.taotao.search.service.impl;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.SearchItem;
import com.wcc.taotao.search.mapper.SearchMapper;
import com.wcc.taotao.search.service.SolrImportService;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * @Description: 索引管理
 * @ClassName: SolrImportServiceImpl
 * @Auther: changchun_wu
 * @Date: 2019/3/17 22:13
 * @Version: 1.0
 **/

@Service
public class SolrImportServiceImpl implements SolrImportService {

    @Autowired
    private HttpSolrClient solrClient;

    @Autowired
    private SearchMapper searchMapper;

    /**
     * 导入所有商品索引
     * @return
     * @throws Exception
     */
    @Override
    public DataResult itemSolrImport() throws Exception{
        //1.提供服务
        //2.注入mapper
        //3.查询所有的商品信息
        SolrInputDocument document = null;
        List<SearchItem> itemList = searchMapper.getSearchItemList();
        for (SearchItem searchItem : itemList) {
            document = new SolrInputDocument();
            document.addField("id",searchItem.getId().toString());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSell_point());
            document.addField("item_price",searchItem.getPrice());
            String image = searchItem.getImage().split(",")[0];
            document.addField("item_image",image);
            document.addField("item_category_name",searchItem.getItem_desc());
            document.addField("item_desc",searchItem.getCategory_name());
            solrClient.add(document);
        }
        return DataResult.ok();
    }
}
