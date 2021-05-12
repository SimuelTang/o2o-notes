package indi.simuel.service;

import indi.simuel.BaseTest;
import indi.simuel.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author simuel_tang
 * @Date 2021/3/12
 * @Time 15:21
 */
public class ShopCategoryServiceTest extends BaseTest {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Test
    public void testQueryShopCategory() {
        ShopCategory shopCondition = new ShopCategory();
        ShopCategory parentCate = new ShopCategory();
        parentCate.setShopCategoryId(4L);
        shopCondition.setParent(parentCate);
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(shopCondition);
        assertEquals(3, shopCategoryList.size());
    }
}
