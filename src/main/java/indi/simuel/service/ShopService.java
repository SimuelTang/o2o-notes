package indi.simuel.service;

import indi.simuel.dto.ImageHolder;
import indi.simuel.dto.ShopExecution;
import indi.simuel.entity.Shop;

public interface ShopService {

    /**
     * @param shopCondition 查询条件
     * @param pageIndex     页号
     * @param pageSize      页大小
     * @return 返回指定页的商铺总数和满足条件的商铺总数
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 根据商铺ID返回商铺
     *
     * @param shopId id号
     * @return 商铺
     */
    Shop getShopById(Long shopId);

    /**
     * 根据传入的参数更改商铺信息
     *
     * @param shop        待修改的商铺
     * @param imageHolder 可能会存在的图片修改
     * @return 修改后的商铺信息
     */
    ShopExecution modifyShop(Shop shop, ImageHolder imageHolder);

    /**
     * 添加店铺
     *
     * @param shop        待添加的商铺
     * @param imageHolder 商铺的图片名字和文件流
     * @return 添加后的店铺信息
     */
    ShopExecution addShop(Shop shop, ImageHolder imageHolder);

}
