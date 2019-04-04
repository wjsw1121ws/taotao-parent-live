package com.wcc.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: 显示商品列表数据
 * @ClassName: ShowItemList
 * @Auther: changchun_wu
 * @Date: 2019/3/15 21:19
 * @Version: 1.0
 **/
public class ShowPageDataList<T> implements Serializable {
    private Long total;     //total总页数
    private List<T> rows;   //data页面显示的数据

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
