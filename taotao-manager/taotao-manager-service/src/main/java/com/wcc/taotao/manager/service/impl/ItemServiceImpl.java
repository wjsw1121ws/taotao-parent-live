package com.wcc.taotao.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcc.taotao.common.pojo.DataResult;
import com.wcc.taotao.common.pojo.ShowPageDataList;
import com.wcc.taotao.common.utils.IDUtils;
import com.wcc.taotao.manager.service.ItemService;
import com.wcc.taotao.mapper.TbItemDescMapper;
import com.wcc.taotao.mapper.TbItemMapper;
import com.wcc.taotao.pojo.TbItem;
import com.wcc.taotao.pojo.TbItemDesc;
import com.wcc.taotao.pojo.TbItemExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.List;

/**
 * @Description: 商品管理service实现
 * @ClassName: ItemServiceImpl
 * @Auther: changchun_wu
 * @Date: 2019/3/15 21:29
 * @Version: 1.0
 **/

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource(name = "topicDestination")
    private Destination destination;

    /**
     * 分页显示商品列表
     * @param page 当前页
     * @param rows 每页显示的数据
     * @return
     */
    @Override
    public ShowPageDataList<TbItem> showItem(Integer page, Integer rows) {
        //1.服务提供
        //2.注入mapper
        //3.设置分页参数
        //数据合理性判断
        if (page==null) page=1;
        if (rows==null) rows=30;
        PageHelper.startPage(page,rows);
        //4.执行查询
        TbItemExample example = new TbItemExample();
        List<TbItem> itemList = itemMapper.selectByExample(example);
        ShowPageDataList<TbItem> result = new ShowPageDataList<>();
        //5.创建PageInfo对象,并设置参数
        PageInfo<TbItem> pageInfo = new PageInfo<>(itemList);
        result.setRows(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 商品保存实现
     * @param item 要保存的商品信息
     * @param desc 要保存的商品描述信息
     * @return
     */
    @Override
    public DataResult addItem(final TbItem item, String desc) {
        //1.提供服务
        //2.注入mapper
        //3.补全商品的其他属性
        //设置商品的id
        final long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        //4.将商品信息插入到商品表
        itemMapper.insert(item);
        //5.补全商品描述信息
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getUpdated());
        //6.将商品描述信息插入到商品描述表
        int insert = itemDescMapper.insert(itemDesc);
        //添加ActiveMQ的逻辑
        if (insert>0){
            jmsTemplate.send(destination, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage textMessage = session.createTextMessage(itemId+"");
                    return textMessage;
                }
            });
        }
        //7.返回DataResult
        return DataResult.ok();
    }

    @Override
    public TbItem getItemById(Long itemId) {
        //1.注入dubbo服务
        //2.注入mapper
        //3.调用mapper中的方法查询
        TbItem item = itemMapper.selectByPrimaryKey(itemId);
        //4.返回结果
        return item;
    }

    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        //1.注入dubbo服务
        //2.注入mapper
        //3.调用mapper中的方法查询
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
        //4.返回结果
        return itemDesc;
    }
}
