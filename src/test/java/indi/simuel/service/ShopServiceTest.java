package indi.simuel.service;

import indi.simuel.BaseTest;
import indi.simuel.dto.ImageHolder;
import indi.simuel.dto.ShopExecution;
import indi.simuel.entity.Area;
import indi.simuel.entity.PersonInfo;
import indi.simuel.entity.Shop;
import indi.simuel.entity.ShopCategory;
import indi.simuel.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Author simuel_tang
 * @Date 2021/2/21
 * @Time 13:52
 */
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1L);
        shopCondition.setShopCategory(shopCategory);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 4);
        System.out.println("该页商铺总数：" + se.getShopList().size());
        System.out.println("满足该条件的商铺总数：" + se.getCount());
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(3L);
        shop.setShopName("测试被修改的店铺");
        shop.setLastEditTime(new Date());
        ImageHolder imageHolder = new ImageHolder();
        ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
    }

    @Test
    public void testAddShop() throws FileNotFoundException {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺5");
        shop.setShopDesc("test5");
        shop.setShopAddr("test5");
        shop.setPhone("test5");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");
        File shopImg = new File("E:\\Desktop\\图片\\Favorites\\Saber.jpg");
        InputStream is = new FileInputStream(shopImg);
        ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
        ShopExecution se = shopService.addShop(shop, imageHolder);
        assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
    }


}
