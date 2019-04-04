package com.wcc.taotao.search.service;

import com.wcc.taotao.common.pojo.DataResult;

/**
 * @Description: 索引导入
 * @ClassName: SolrImport
 * @Auther: changchun_wu
 * @Date: 2019/3/17 22:11
 * @Version: 1.0
 **/
public interface SolrImportService {

    /**
     * 导入所有商品索引
     * @return
     * @throws Exception
     */
    DataResult itemSolrImport() throws Exception;
}
