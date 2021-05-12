package indi.simuel.service;

import indi.simuel.BaseTest;
import indi.simuel.dto.ImageHolder;
import indi.simuel.dto.ProductExecution;
import indi.simuel.entity.Product;
import indi.simuel.entity.ProductCategory;
import indi.simuel.entity.Shop;
import indi.simuel.enums.ProductStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @Author simuel_tang
 * @Date 2021/3/7
 * @Time 20:35
 */
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() throws FileNotFoundException {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(9L);
        ProductCategory pc = new ProductCategory();
        pc.setProductCategoryId(8L);
        product.setShop(shop);
        product.setProductCategory(pc);
        product.setProductName("测试商品2");
        product.setProductDesc("测试商品2");
        product.setPriority(20);
        product.setCreateTime(new Date());
        product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
        // 创建缩略图文件流
        File thumbnailFile = new File("E:\\Desktop\\图片\\Favorites\\Saber.jpg");
        InputStream is = new FileInputStream(thumbnailFile);
        ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(), is);
        // 创建两个商品详情图文件流并将他们添加到详情图列表中
        File productImg1 = new File("E:\\Desktop\\图片\\Favorites\\Saber.jpg");
        InputStream is1 = new FileInputStream(productImg1);
        File productImg2 = new File("E:\\Desktop\\图片\\Favorites\\Arthur.jpg");
        InputStream is2 = new FileInputStream(productImg2);
        List<ImageHolder> productImgList = new ArrayList<>();
        productImgList.add(new ImageHolder(productImg1.getName(), is1));
        productImgList.add(new ImageHolder(productImg2.getName(), is2));
        // 添加商品并验证
        ProductExecution pe = productService.addProduct(product, thumbnail, productImgList);
        assertEquals(ProductStateEnum.SUCCESS.getState(), pe.getState());
    }
}
