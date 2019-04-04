package com.wcc.taotao.search.controller;

import com.wcc.taotao.common.pojo.SearchResult;
import com.wcc.taotao.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description: solr查询controller层
 * @ClassName: SearchController
 * @Author: changchun_wu
 * @Date: 2019/3/30 19:05
 * @Version: 1.0
 **/
@Controller
public class SearchController{
    @Value("${PAGE_ROWS}")
    private Integer PAGE_ROWS;
    @Autowired
    private SearchService searchService;

    /**
     * solr关键字查询
     * @param queryString
     * @param page
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search")
    public String getSearchResult(@RequestParam(value = "q") String queryString,
                                        Integer page, Model model) throws Exception{
        //1.引入dobbo服务
        //2.注入service
        //3.调用服务方法
        queryString = new String(queryString.getBytes("ISO-8859-1"),"utf-8");
        SearchResult searchResult = searchService.getSearchResult(queryString, page, PAGE_ROWS);
        //4.将查询结果返回给页面
        model.addAttribute("query",queryString);
        model.addAttribute("totalPages",searchResult.getPageCount());
        model.addAttribute("itemList",searchResult.getItemList());
        model.addAttribute("page",page);
        //返回
        return "search";
    }
}
