package indi.simuel.service.impl;

import indi.simuel.dao.ShopDao;
import indi.simuel.dto.ImageHolder;
import indi.simuel.dto.ShopExecution;
import indi.simuel.entity.Shop;
import indi.simuel.enums.ShopStateEnum;
import indi.simuel.exceptions.ShopOperationException;
import indi.simuel.service.ShopService;
import indi.simuel.util.ImageUtil;
import indi.simuel.util.PageCalculatorUtil;
import indi.simuel.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/2/21
 * @Time 13:52
 */

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;

    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        // 页码转换为数据库中的行码
        int rowIdx = PageCalculatorUtil.pageIndexToRow(pageIndex, pageSize);
        // 根据查询条件，通过 dao 调用方法返回店铺列表
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIdx, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        // 将结果保存
        ShopExecution se = new ShopExecution();
        if (shopList != null) {
            se.setShopList(shopList);
            se.setCount(count);
        } else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
            se.setStateInfo(ShopStateEnum.INNER_ERROR.getStateInfo());
        }
        return se;
    }

    @Override
    public Shop getShopById(Long shopId) {
        return shopDao.queryByShopId(shopId);
    }


    /**
     * 修改店铺信息
     *
     * @param shop
     * @param imageHolder
     * @return 店铺操作的结果
     */
    @Override
    public ShopExecution modifyShop(Shop shop, ImageHolder imageHolder) {
        //先判断店铺信息是否为空，如果为空，则直接返回
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //判断图片是否需要进行处理
            if (imageHolder != null && imageHolder.getImage() != null
                    && !"".equals(imageHolder.getImageName())) {
                Shop tmpShop = shopDao.queryByShopId(shop.getShopId());
                if (tmpShop.getShopImg() != null) {
                    //删除原有的照片
                    ImageUtil.deleteImage(tmpShop.getShopImg());
                }
                //添加更改后的照片
                addShopImage(shop, imageHolder);
            }
            //更新店铺信息
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.updateShop(shop);
            if (effectedNum <= 0) {
                return new ShopExecution(ShopStateEnum.INNER_ERROR);
            } else {
                shop = shopDao.queryByShopId(shop.getShopId());
                return new ShopExecution(ShopStateEnum.SUCCESS, shop);
            }
        } catch (Exception e) {
            throw new ShopOperationException("modifyShop Error: " + e.getMessage());
        }
    }

    /**
     * 为新添加的店铺添加图片
     *
     * @param shop        被添加的店铺
     * @param imageHolder 该店铺对应的图片位置
     */
    private void addShopImage(Shop shop, ImageHolder imageHolder) {
        //获取我们已经准备好的图片的相对路径
        String targetPath = PathUtil.getShopImagePath(shop.getShopId());
        String thumbnailPath = ImageUtil.generateThumbnail(imageHolder, targetPath);
        shop.setShopImg(thumbnailPath);
    }

    /**
     * 用于添加店铺
     *
     * @param shop        待添加的店铺
     * @param imageHolder 该店铺对应的图片（如奶茶店对应的奶茶等）
     * @return 方法执行完后的相关信息
     */
    @Override
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        //考虑到店铺的添加操作可能会失败，所以放在 try 语句块中
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺插入失败");
            }
            if (imageHolder.getImage() != null) {
                addShopImage(shop, imageHolder);
                effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    throw new ShopOperationException("店铺图片更新失败");
                }
            }
        } catch (ShopOperationException e) {
            throw new ShopOperationException("addShop error: " + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }
}
