package com.neusoft.webauth.goods.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neusoft.core.restful.AppResponse;
import com.neusoft.util.StringUtil;
import com.neusoft.webauth.goods.dao.GoodsDao;
import com.neusoft.webauth.goods.entity.GoodsInfo;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @DescriptionDemo 实现类
 * @Author
 * @Date 2020-03-29
 */
@Service
public class GoodsService {


    @Resource
    private GoodsDao goodsDao;

    /**
     * 新增用户
     *
     * @param
     * @returndingning
     * @Date 2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse saveGoods(GoodsInfo goodsInfo) {
        // 校验账号是否存在
        //int countUserAcct = goodsDao.countGoodsAcct(goodsInfo);
//        if (0 != countUserAcct) {
//            return AppResponse.success("用户账号已存在，请重新输入！");
//        }
        goodsInfo.setGoodsCode( StringUtil.getCommonCode(2));
        goodsInfo.setIsDeleted(0);
    // 新增用户
    int count = goodsDao.saveGoods(goodsInfo);
        if (0 == count) {
        return AppResponse.success("新增失败，请重试！");
    }
        return AppResponse.success("新增成功！");
}

    /**
     *  查询商品列表（分页）
     *
     * @param
     * @return
     * @Author
     * @Date 2020-03-29
     */
    public AppResponse listGoods(GoodsInfo goodsInfo) {
        PageHelper.startPage(goodsInfo.getPageNum(), goodsInfo.getPageSize());
        List<GoodsInfo> goodsInfoList = goodsDao.listGoodsByPage(goodsInfo);
        // 包装Page对象
        PageInfo<GoodsInfo> pageData = new PageInfo<GoodsInfo>(goodsInfoList);
        return AppResponse.success("查询成功！", pageData);
    }

    /**
     *  删除商品
     *
     * @param
     * @param
     * @return
     * @Author
     * @Date 2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse deleteGoods(String GoodsCode) {
        List<String> listCode = Arrays.asList(GoodsCode.split(","));
        // 删除用户
        int count = goodsDao.deleteGoods(listCode);
        if (0 == count) {
            return AppResponse.bizError("删除失败，请重试！");
        }
        return AppResponse.success("删除成功！");
    }

    /**
     *  修改商品
     *
     * @param
     * @return
     * @Author
     * @Date 2020-03-29
     */
    @Transactional(rollbackFor = Exception.class)
    public AppResponse updateGoods(GoodsInfo goodsInfo) {
        AppResponse appResponse = AppResponse.success("修改成功");
        // 校验账号是否存在
        int countGoodsAcct = goodsDao.countGoodsAcct(goodsInfo);
        if (0 != countGoodsAcct) {
            return AppResponse.bizError("用户账号已存在，请重新输入！");
        }
        // 修改用户信息
        int count = goodsDao.updateGoods(goodsInfo);
        if (0 == count) {
            appResponse = AppResponse.versionError("数据有变化，请刷新！");
            return appResponse;
        }
        return appResponse;
    }


    /**
     * demo 查询商品详情
     *
     * @param
     * @return
     * @Author
     * @Date 2020-03-29
     */
    public AppResponse getGoodsByGoodsId(String goodsId) {
        GoodsInfo goodsInfo = goodsDao.getGoodsByGoodsCode(goodsId);
        return AppResponse.success("查询成功！",goodsInfo);
    }
}


