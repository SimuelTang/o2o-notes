package indi.simuel.dao;

import indi.simuel.BaseTest;
import indi.simuel.entity.Area;
import indi.simuel.entity.PersonInfo;
import indi.simuel.entity.Shop;
import indi.simuel.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;


/**
 * @Author simuel_tang
 * @Date 2021/2/19
 * @Time 13:01
 */
public class ShopDaoTest extends BaseTest {
    @Autowired
    private ShopDao shopDao;


    /**
     * 测试符合条件的店铺的总数：
     * 如果店铺类别不为空，则统计拥有店铺类别ID的店铺总数
     * 如果店铺有父引用，则统计符合父引用条件的店铺数量
     */
    @Test
    public void testQueryShopListAndCount() {
        Shop shopCondition = new Shop();
        ShopCategory currCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(4L);
        currCategory.setParent(parentCategory);
        shopCondition.setShopCategory(currCategory);
        List<Shop> shops = shopDao.queryShopList(shopCondition, 0, 6);
        for (Shop shop : shops) {
            System.out.println(shop);
        }
    }

    @Test
    public void testQueryShopById() {
        Shop shop = shopDao.queryByShopId(3L);
        System.out.println(shop);
    }

    @Test
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo user = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        user.setUserId(13L);
        area.setAreaId(5);
        shopCategory.setShopCategoryId(39L);
        shop.setOwner(user);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("test");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(0);
        shop.setAdvice("审核中");
        int flag = shopDao.insertShop(shop);
        assertEquals(1, flag);
    }

    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        PersonInfo user = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        user.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setShopId(3L);
        shop.setOwner(user);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺1");
        shop.setShopDesc("test1");
        shop.setShopAddr("test1");
        shop.setPhone("test1");
        shop.setShopImg("test1");
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("通过");
        int flag = shopDao.updateShop(shop);
        assertEquals(1, flag);
    }

}
