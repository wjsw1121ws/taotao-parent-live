package com.wcc.taotao.content.service;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.TreeData;

import java.util.List;

/**
 * @Description: 内容分类interface
 * @ClassName: ContentCategoryService
 * @Auther: changchun_wu
 * @Date: 2019/3/16 22:47
 * @Version: 1.0
 **/
public interface ContentCategoryService {
    /**
     * 获取内容分类列表树形结构
     * @param parentId
     * @return
     */
    List<TreeData> getContentCategoryList(Long parentId);

    /**
     * 新增内容分类节点
     * @param parentId
     * @param name
     * @return
     */
    DataResult addContentCategory(Long parentId,String name);
}
