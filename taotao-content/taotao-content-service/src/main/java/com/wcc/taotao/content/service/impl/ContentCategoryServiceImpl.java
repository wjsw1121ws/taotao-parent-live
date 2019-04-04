package com.wcc.taotao.content.service.impl;

import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.TreeData;
import com.wcc.taotao.content.service.ContentCategoryService;
import com.wcc.taotao.mapper.TbContentCategoryMapper;
import com.wcc.taotao.pojo.TbContentCategory;
import com.wcc.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description: 内容分类service实现
 * @ClassName: ContentCategoryServiceImpl
 * @Auther: changchun_wu
 * @Date: 2019/3/16 22:50
 * @Version: 1.0
 **/

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;
    /**
     * 获取内容分类列表树形结构
     * @param parentId
     * @return
     */
    @Override
    public List<TreeData> getContentCategoryList(Long parentId) {
        //1.提供服务
        //2.注入mapper
        //3.获取内容分类信息
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> contentCategories = contentCategoryMapper.selectByExample(example);
        //4.创建结果集List<TreeData>对象
        List<TreeData> list = new ArrayList<>();
        //5.遍历内容分类信息,并将分类中的属性放入到TreeData中
        for (TbContentCategory contentCategory : contentCategories) {
            TreeData treeData = new TreeData();
            treeData.setId(contentCategory.getId());
            treeData.setText(contentCategory.getName());
            treeData.setState(contentCategory.getIsParent()==true?"closed":"open");
            list.add(treeData);
        }
        //6.返回结果集
        return list;
    }

    /**
     * 新增内容分类
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public DataResult addContentCategory(Long parentId, String name) {
        //1.提供dobbo服务
        //2.注入mapper
        //3.创建ContentCategory对象并补全属性
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setParentId(parentId);
        contentCategory.setName(name);
        //状态 可选值:1(正常),2(删除)
        contentCategory.setStatus(1);
        //排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setIsParent(false);
        contentCategory.setUpdated(contentCategory.getCreated());
        //4.调用mapper中的方法添加节点
        contentCategoryMapper.insert(contentCategory);
        //5.判断要跟新的节点的父节点是否是叶子节点,如果是,更新其为父节点
        TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(parentId);
        if (category.getIsParent()==false){
            //表示要添加的节点的父节点为根节点,更新其为父节点
            category.setIsParent(true);
        }
        //6.返回
        return DataResult.ok();
    }
}
