package indi.simuel.dao;

import indi.simuel.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * 添加店铺
     *
     * @param shop 待添加的店铺
     * @return 被影响的行数
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     *
     * @param shop 待修改的店铺
     * @return 被影响的行数
     */
    int updateShop(Shop shop);

    /**
     * 通过店铺 ID 查询店铺信息
     *
     * @param shopId 待查询的店铺 ID
     * @return 待查询的店铺（已包含所有信息）
     */
    Shop queryByShopId(Long shopId);

    /**
     * 分页查询店铺
     *
     * @param shopCondition 待查询的店铺的要求
     * @param rowIndex      查询开始的页号
     * @param pageSize      每页显示的数据
     * @return 商铺列表
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 返回queryShopList总数
     *
     * @param shopCondition 待查询的店铺的要求
     * @return 查询到的店铺总数
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);
}
