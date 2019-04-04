package com.wcc.taotao.manager.controller;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.search.service.SolrImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 索引管理
 * @ClassName: SolrImportController
 * @Auther: changchun_wu
 * @Date: 2019/3/17 23:09
 * @Version: 1.0
 **/

@Controller
public class SolrImportController {

    @Autowired
    private SolrImportService solrImportService;

    /**
     * 导入所有商品索引
     * @return
     */
    @RequestMapping(value = "/solr/import")
    @ResponseBody
    public DataResult itemSolrImport(){
        try {
            //1.消费服务
            //2.注入service
            DataResult dataResult = solrImportService.itemSolrImport();
            //3.调用服务方法
            //4.返回
            return dataResult;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataResult.build(201,"");
    }
}
