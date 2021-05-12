package indi.simuel.dao;

import indi.simuel.BaseTest;
import indi.simuel.dto.ProductCategoryExecution;
import indi.simuel.entity.ProductCategory;
import indi.simuel.entity.Shop;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/3
 * @Time 21:13
 */
public class ProdectCategoryDaoTest extends BaseTest {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void testQueryProductCategoryList() {
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(10L);
        assert(0 != productCategories.size());
    }
      
    @Test
    public void testDeleteProductCategory() {
        ProductCategory pc = new ProductCategory();
        pc.setShopId(4L);
        pc.setProductCategoryId(9L);
        int effectedNum = productCategoryDao.deleteProductCategory(9L, 4L);
        assertEquals(0, effectedNum);
    }

    @Test
    public void testBatchInsertProductCategory() {
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryName("商品类别4");
        pc.setPriority(3);
        pc.setCreateTime(new Date());
        pc.setShopId(4L);
        ProductCategory pc2 = new ProductCategory();
        pc2.setProductCategoryName("商品类别5");
        pc2.setPriority(7);
        pc2.setCreateTime(new Date());
        pc2.setShopId(5L);
        List<ProductCategory> productCategories = Arrays.asList(pc, pc2);
        int effectedNum = productCategoryDao.batchInsertProductCategory(productCategories);
        assertEquals(2, effectedNum);
    }

    @Test
    public void testQueryProductCategoryByShopId() {
        Shop shop = new Shop();
        shop.setShopId(3L);
        List<ProductCategory> productCategories = productCategoryDao.queryProductCategoryList(shop.getShopId());
        for (ProductCategory productCategory : productCategories) {
            System.out.println(productCategory);
        }
    }
}
