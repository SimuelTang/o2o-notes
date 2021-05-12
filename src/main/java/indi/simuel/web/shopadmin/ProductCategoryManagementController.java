package indi.simuel.web.shopadmin;

import indi.simuel.dto.ProductCategoryExecution;
import indi.simuel.entity.ProductCategory;
import indi.simuel.entity.Shop;
import indi.simuel.enums.ProductCategoryStateEnum;
import indi.simuel.enums.ProductStateEnum;
import indi.simuel.exceptions.ProductCategoryOperationException;
import indi.simuel.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author simuel_tang
 * @Date 2021/3/3
 * @Time 20:41
 */

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @RequestMapping(value = "addproductcategories", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProductCategoryList(@RequestBody List<ProductCategory> productCategories
            , HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取当前店铺
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 为前台传过来的商品类别设置店铺id
        for (ProductCategory p : productCategories) {
            p.setShopId(currentShop.getShopId());
        }
        // 进行插入操作
        if (productCategories.size() > 0) {
            try {
                ProductCategoryExecution pce = productCategoryService.batchAddProductCategory(productCategories);
                if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pce.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请添加至少一个商品类别");
        }
        return modelMap;
    }


    @RequestMapping(value = "getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> manageProductCategory(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        if (currentShop != null && currentShop.getShopId() > 0) {
            List<ProductCategory> productCategories =
                    productCategoryService.queryProductCategories(currentShop.getShopId());
            modelMap.put("success", true);
            modelMap.put("dataList", productCategories);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", ProductStateEnum.INNER_ERROR);
        }
        return modelMap;
    }

    @RequestMapping(value = "removeproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> removeProductCategory(long productCategoryId, HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        if (productCategoryId > 0) {
            try {
                Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
                ProductCategoryExecution pce = productCategoryService.deleteProductCategory(productCategoryId, currentShop.getShopId());
                if (pce.getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pce.getStateInfo());
                }
            } catch (ProductCategoryOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg:", "请至少选择一个商品类别进行删除");
        }
        return modelMap;
    }
}
