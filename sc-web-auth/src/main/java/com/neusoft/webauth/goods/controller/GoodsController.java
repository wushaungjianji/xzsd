package com.neusoft.webauth.goods.controller;


import com.neusoft.core.restful.AppResponse;
import com.neusoft.security.client.utils.SecurityUtils;
import com.neusoft.util.AuthUtils;
import com.neusoft.webauth.goods.entity.GoodsInfo;
import com.neusoft.webauth.goods.service.GoodsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Description增删改查DEMO
 * @Author dingning
 * @Date 2020-03-21
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Resource
    private GoodsService goodsService;

    /**
     *  新增商品
     *
     * @param
     * @return AppResponse
     * @author dingning
     * @Date 2020-03-29
     */
    @PostMapping("/saveGoods")
    public AppResponse saveUser(GoodsInfo goodsInfo) {
        try {
            //获取用户id
            String goodsId = SecurityUtils.getCurrentUserId();
            goodsInfo.setCreateBy(goodsId);
            AppResponse appResponse = goodsService.saveGoods(goodsInfo);
            return appResponse;
        } catch (Exception e) {
            logger.error("商品新增失败", e);
            System.out.println(e.toString());
            throw e;
        }
    }


    /**
     * 商品列表(分页)
     *
     * @param
     * @return AppResponse
     * @author dingning
     * @Date 2020-03-29
     */
    @RequestMapping(value = "listGoods")
    public AppResponse listGoods(GoodsInfo goodsInfo) {
        try {
            return goodsService.listGoods(goodsInfo);
        } catch (Exception e) {
            logger.error("查询商品列表异常", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 删除商品
     *
     * @param
     * @return AppResponse
     * @author dingning
     * @Date 2020-03-29
     */
    @PostMapping("deleteGoods")
    public AppResponse deleteGoods(String goodsCode) {
        try {
            //获取用户id
            String goodsId = SecurityUtils.getCurrentUserId();

            return goodsService.deleteGoods(goodsCode);
        } catch (Exception e) {
            logger.error("商品删除错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     *  修改商品
     *
     * @param
     * @return AppResponse
     * @author dingning
     * @Date 2020-03-29
     */
    @PostMapping("updateGoods")
    public AppResponse updateGoods(GoodsInfo goodsInfo) {
        try {
            //获取id
            String userId = SecurityUtils.getCurrentUserId();
            goodsInfo.setCreateBy(userId);
            goodsInfo.setLastModifiedBy(userId);
          return goodsService.updateGoods(goodsInfo);

        } catch (Exception e) {
            logger.error("修改商品信息错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }

    /**
     * 查询商品详情
     *
     * @param
     * @return AppResponse
     * @author dingning
     * @Date 2020-03-29
     */
    @RequestMapping(value = "getGoodsByGoodsCode")
    public AppResponse getGoodsByGoodsCode(String goodsCode) {
        try {
            return goodsService.getGoodsByGoodsId(goodsCode);
        } catch (Exception e) {
            logger.error("商品查询错误", e);
            System.out.println(e.toString());
            throw e;
        }
    }
}
