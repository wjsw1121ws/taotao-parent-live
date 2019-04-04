package com.wcc.taotao.content.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.ShowPageDataList;
import com.wcc.taotao.content.service.ContentService;
import com.wcc.taotao.mapper.TbContentMapper;
import com.wcc.taotao.pojo.TbContent;
import com.wcc.taotao.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description: 内容管理service实现
 * @ClassName: ContentServiceImpl
 * @Auther: changchun_wu
 * @Date: 2019/3/17 2:41
 * @Version: 1.0
 **/
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper contentMapper;
    /**
     * 获取分类列表
     * @param categoryId 数所分类的id
     * @param page 当前页
     * @param rows 每页显示的记录数
     * @return
     */
    @Override
    public ShowPageDataList<TbContent> getContentList(Long categoryId, Integer page, Integer rows) {
        //1.提供服务
        //2.注入mapper
        //3.设置分页参数
        if (page==null) page=1;
        if (rows==null) rows=20;
        PageHelper.startPage(page,rows);
        //4.调用mapper方法查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        //5.创建结果集
        ShowPageDataList<TbContent> result = new ShowPageDataList<>();
        //6.创建pageInfo对象
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        //7.将分页信息放入结果集中
        result.setTotal(pageInfo.getTotal());
        result.setRows(pageInfo.getList());
        //6.
        return result;
    }

    /**
     * 内容添加
     * @param tbContent
     * @return
     */
    @Override
    public DataResult addContent(TbContent tbContent) {
        //1.提供服务
        //2.注入mapper
        //3.补全其他属性
        tbContent.setCreated(new Date());
        tbContent.setUpdated(tbContent.getCreated());
        //4.调用mapper中的方法插入数据
        contentMapper.insert(tbContent);
        //5.返回
        return DataResult.ok();
    }

    /**
     * 通过分类id获取该分类的内容
     * @param categoryId 分类id
     * @return
     */
    @Override
    public List<TbContent> getContentByCategoryId(Long categoryId) {
        //1.提供服务
        //2.注入mapper
        //3.调用mapper中的方法执行查询
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contentList = contentMapper.selectByExample(example);
        //4.返回结果
        return contentList;
    }
}
