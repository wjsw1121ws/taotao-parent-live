package com.wcc.taotao.manager.service.impl;

import com.wcc.taotao.manager.service.TestService;
import com.wcc.taotao.mapper.TbItemMapper;
import com.wcc.taotao.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 测试
 * @ClassName: TestServiceImpl
 * @Auther: changchun_wu
 * @Date: 2019/3/15 0:01
 * @Version: 1.0
 **/

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TbItemMapper mapper;
    @Override
    public String test(Long itemId) {
        TbItem tbItem = mapper.selectByPrimaryKey(itemId);
        return tbItem.toString();
    }
}
