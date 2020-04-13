package com.neusoft.webauth.goods.dao;

import com.neusoft.webauth.goods.entity.GoodsInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName DemoDao
 * @Description Demo
 * @Author dingning
 * @Date 2020-03-21
 */
@Mapper
public interface GoodsDao {
    /**
     * 统计商品账号数量
     * @param
     * @return
     */
    int countGoodsAcct(GoodsInfo goodsInfo);

    /**
     * 新增商品
     * @param
     * @return
     */
    int saveGoods(GoodsInfo goodsInfo);

    /**
     * 获取所有商品信息
     * @param
     * @return 所有商品信息
     */
    List<GoodsInfo> listGoodsByPage(GoodsInfo goodsInfo);

    /**
     * 删除商品信息
     * @param listCode 选中的商品编号集合
     * @param
     * @return
     */
    int deleteGoods(List<String> listCode);

    /**
     * 修改商品信息
     * @param
     * @return 修改结果
     */
    int updateGoods(GoodsInfo goodsInfo);

    /**
     * 查询商品信息
     * @param
     * @return 修改结果
     */
    GoodsInfo getGoodsByGoodsCode(@Param("goodsCode") String goodsCode);
}
