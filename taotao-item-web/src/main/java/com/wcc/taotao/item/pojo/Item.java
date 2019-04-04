package com.wcc.taotao.item.pojo;

import com.wcc.taotao.pojo.TbItem;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @Description:
 * @ClassName: Item
 * @Author: changchun_wu
 * @Date: 2019/3/31 1:04
 * @Version: 1.0
 **/
public class Item extends TbItem implements Serializable {

    public Item(TbItem tbItem){
        BeanUtils.copyProperties(tbItem,this);
    }

    public String[] getImages(){
        String[] images = super.getImage().split(",");
        return images;
    }
}
