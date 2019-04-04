package com.wcc.taotao.content.service;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.ShowPageDataList;
import com.wcc.taotao.pojo.TbContent;

import java.util.List;

/**
 * @Description: 内容管理interface
 * @ClassName: ContentService
 * @Auther: changchun_wu
 * @Date: 2019/3/17 2:33
 * @Version: 1.0
 **/
public interface ContentService {
    /**
     * 获取内容表
     * @param categoryId 数所分类的id
     * @param page 当前页
     * @param rows 每页显示的记录数
     * @return
     */
    ShowPageDataList<TbContent> getContentList(Long categoryId,Integer page,Integer rows);

    /**
     * 内容添加
     * @param tbContent
     * @return
     */
    DataResult addContent(TbContent tbContent);

    /**
     * 通过分类ID获取该分类中的信息
     * @param categoryId 分类id
     * @return
     */
    List<TbContent> getContentByCategoryId(Long categoryId);
}
