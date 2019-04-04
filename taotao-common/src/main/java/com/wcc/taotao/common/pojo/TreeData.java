package com.wcc.taotao.common.pojo;

import java.io.Serializable;

/**
 * @Description: 树形结构数据
 * @ClassName: TreeData
 * @Auther: changchun_wu
 * @Date: 2019/3/15 22:44
 * @Version: 1.0
 **/
public class TreeData implements Serializable {
    private Long id;        //绑定节点的标识值
    private String text;    //显示的节点文本
    private String state;   //节点状态，'open' 或 'closed'

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
