package indi.simuel.web.frontend;

import indi.simuel.dto.ProductExecution;
import indi.simuel.entity.Product;
import indi.simuel.entity.ProductCategory;
import indi.simuel.entity.Shop;
import indi.simuel.service.ProductCategoryService;
import indi.simuel.service.ProductService;
import indi.simuel.service.ShopService;
import indi.simuel.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author simuel_tang
 * @Date 2021/3/13
 * @Time 10:06
 */

@Controller
@RequestMapping("/frontend")
public class ShopDetailController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 展示店铺的基本信息，显示在卡片上
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取前台传过来的shopId
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        Shop shop;
        List<ProductCategory> productCategoryList;
        if (shopId != -1) {
            // 获取店铺Id为shopId的店铺信息
            shop = shopService.getShopById(shopId);
            // 获取店铺下面的商品类别列表
            productCategoryList = productCategoryService.getProductCategoryList(shopId);
            modelMap.put("shop", shop);
            modelMap.put("productCategoryList", productCategoryList);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty shopId");
        }
        return modelMap;
    }

    /**
     * 找到该店铺下的所有商品
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProductsByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取页码
        int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        // 获取一页需要显示的条数
        int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        // 获取店铺Id
        long shopId = HttpServletRequestUtil.getLong(request, "shopId");
        // 空值判断
        if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
            // 尝试获取商品类别Id（表示这个店铺有哪些类别）
            long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
            // 尝试获取模糊查找的商品名
            String productName = HttpServletRequestUtil.getString(request, "productName");
            // 组合查询条件
            Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
            // 按照传入的查询条件以及分页信息返回相应商品列表以及总数
            ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
            modelMap.put("productList", pe.getProductList());
            modelMap.put("count", pe.getCount());
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
        }
        return modelMap;
    }

    private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
        Product productCondition = new Product();
        Shop shop = new Shop();
        shop.setShopId(shopId);
        productCondition.setShop(shop);
        if (productCategoryId != -1L) {
            // 查询某个商品类别下面的商品列表
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if (productName != null) {
            // 查询名字里包含productName的店铺列表
            productCondition.setProductName(productName);
        }
        // 只允许选出状态为上架的商品
        productCondition.setEnableStatus(1);
        return productCondition;
    }
}
