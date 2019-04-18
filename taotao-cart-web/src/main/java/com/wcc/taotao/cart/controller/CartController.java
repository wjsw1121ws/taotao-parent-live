package com.wcc.taotao.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: 购物车Controller层
 * @ClassName: CartController
 * @Author: changchun_wu
 * @Date: 2019/4/19 0:30
 * @Version: 1.0
 **/

@Controller
public class CartController {

    /**
     * 添加商品到购物车
     * @param itemId
     * @return
     */
    @RequestMapping(value = "/cart/add/{itemId}")
    public String addItemToCart(@PathVariable Long itemId){
        return "cartSuccess";
    }

    @RequestMapping(value = "/cart/cart")
    public String checkoutCart(){
        return "cart";
    }
}
