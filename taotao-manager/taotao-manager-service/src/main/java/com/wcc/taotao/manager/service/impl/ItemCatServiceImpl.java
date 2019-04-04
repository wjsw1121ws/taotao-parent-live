package com.wcc.taotao.manager.service.impl;

import com.wcc.taotao.common.pojo.TreeData;
import com.wcc.taotao.manager.service.ItemCatService;
import com.wcc.taotao.mapper.TbItemCatMapper;
import com.wcc.taotao.pojo.TbItemCat;
import com.wcc.taotao.pojo.TbItemCatExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品分类service实现
 * @ClassName: ItemCatService
 * @Auther: changchun_wu
 * @Date: 2019/3/15 22:50
 * @Version: 1.0
 **/

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 获取商品分类列表
     * @param parentId
     * @return
     */
    @Override
    public List<TreeData> getItemCatList(Long parentId) {
        //1.提供服务
        //2.注入mapper
        //3.调用mapper进行查询
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        //4.将查询的数据封装到TreeData
        List<TreeData> list = new ArrayList<>();
        for (TbItemCat itemCat : itemCats) {
            TreeData treeData = new TreeData();
            treeData.setId(itemCat.getId());
            treeData.setText(itemCat.getName());
            treeData.setState(itemCat.getIsParent()==true?"closed":"open");
            list.add(treeData);
        }
        //5.返回结果集
        return list;
    }
}
