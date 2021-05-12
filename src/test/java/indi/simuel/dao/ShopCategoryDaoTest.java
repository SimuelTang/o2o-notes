package indi.simuel.dao;

import indi.simuel.BaseTest;
import indi.simuel.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/2/25
 * @Time 9:23
 */
public class ShopCategoryDaoTest extends BaseTest {
    
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    
    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(2, shopCategoryList.size());
        
        
//        ShopCategory testShopCat = new ShopCategory();
//        ShopCategory parentShopCat = new ShopCategory();
//        parentShopCat.setShopCategoryId(1L);
//        testShopCat.setParent(parentShopCat);
//        shopCategoryList = shopCategoryDao.queryShopCategory(testShopCat);
//        assertEquals(1, shopCategoryList.size());
//        System.out.println("店铺类别名：" + shopCategoryList.get(0).getShopCategoryName());
    }
}
