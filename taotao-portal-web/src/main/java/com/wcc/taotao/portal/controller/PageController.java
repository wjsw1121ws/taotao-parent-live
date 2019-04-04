package com.wcc.taotao.portal.controller;

import com.wcc.taotao.common.pojo.Advertising1;
import com.wcc.taotao.common.utils.JsonUtils;
import com.wcc.taotao.content.service.ContentService;
import com.wcc.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 显示首页
 * @ClassName: PageController
 * @Auther: changchun_wu
 * @Date: 2019/3/17 3:39
 * @Version: 1.0
 **/

@Controller
public class PageController {

    @Autowired
    private ContentService contentService;

    @Value("${AD_CATEGORY_ID}")
    private Long AD_CATEGORY_ID;

    @Value("${AD_IMG_HEIGHT}")
    private String AD_IMG_HEIGHT;

    @Value("${AD_IMG_WIDTH}")
    private String AD_IMG_WIDTH;

    @Value("${AD_IMG_WIDTH_B}")
    private String AD_IMG_WIDTH_B;

    /**
     * 展示首页并将大广告位显示出来
     * @return
     */
    @RequestMapping(value = "/index")
    public String showPage(Model model){
        //1.消费服务
        //2.注入服务
        //3.调用服务方法
        List<TbContent> contentList = contentService.getContentByCategoryId(AD_CATEGORY_ID);
        //4.遍历查询结果,并将内容中的信息放入到Advertising1中
        List<Advertising1> list = new ArrayList<>();
        for (TbContent content : contentList) {
            Advertising1 ad1 = new Advertising1();
            ad1.setAlt("");
            ad1.setHeight(AD_IMG_HEIGHT);
            ad1.setHeightB(AD_IMG_HEIGHT);
            ad1.setHref("");
            ad1.setSrc(content.getPic());
            ad1.setSrcB(content.getPic2());
            ad1.setWidth(AD_IMG_WIDTH);
            ad1.setWidthB(AD_IMG_WIDTH_B);
            list.add(ad1);
        }
        //5.将数据放到页面中
        model.addAttribute("ad1",JsonUtils.objectToJson(list));
        //6.返回
        return "index";
    }
}
