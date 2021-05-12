package indi.simuel.dao;

import indi.simuel.BaseTest;
import indi.simuel.entity.Product;
import indi.simuel.entity.Shop;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/7
 * @Time 14:47
 */
public class ProductDaoTest extends BaseTest {

    @Autowired
    private ProductDao productDao;
 
    @Test
    public void testUpdateProductCategoryToNull() {
        int num = productDao.updateProductCategoryToNull(2L);
        assertEquals(1, num);
    }
    
    
    @Test
    public void testQueryProductList() {
        Product productCondition = new Product();
        // 分页查询，预期返回三条结果
        List<Product> productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
        // 使用商品名称模糊查询，预期返回两条结果
        productCondition.setProductName("商品2");
        // 从第一个查到的数据开始算
        productList = productDao.queryProductList(productCondition, 0, 3);
        assertEquals(3, productList.size());
    }

    @Test
    public void testGetProductById() {
        Product product = productDao.queryProductById(8L);
        assert (product != null);
        System.out.println(product.getShop().getShopId());
    }

    @Test
    public void testInsertProduct() {
        Product product = new Product();
        product.setProductName("test product name");
        product.setEnableStatus(0);
        Shop shop = new Shop();
        shop.setShopId(9L);
        product.setShop(shop);
        int effectedNum = productDao.insertProduct(product);
        assertEquals(1, effectedNum);
    }

}
