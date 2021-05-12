package indi.simuel.web.frontend;

import indi.simuel.entity.Product;
import indi.simuel.service.ProductService;
import indi.simuel.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author simuel_tang
 * @Date 2021/3/13
 * @Time 13:19
 */

@Controller
@RequestMapping("/frontend")
public class ProductDetailController {
    @Autowired
    private ProductService productService;

    /**
     * 根据商品Id获取商品详情
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/listproductdetailpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listProductDetailPageInfo(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 获取前台传递过来的productId
        long productId = HttpServletRequestUtil.getLong(request, "productId");
        Product product;
        // 空值判断
        if (productId != -1) {
            // 根据productId获取商品信息，包含商品详情图列表
            product = productService.getProductById(productId);
            modelMap.put("product", product);
            modelMap.put("success", true);
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "empty productId");
        }
        return modelMap;
    }
}
