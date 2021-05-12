package indi.simuel.web.frontend;

import indi.simuel.dao.HeadLineDao;
import indi.simuel.dao.ShopCategoryDao;
import indi.simuel.entity.HeadLine;
import indi.simuel.entity.ShopCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author simuel_tang
 * @Date 2021/3/10
 * @Time 11:00
 */

@Controller
@RequestMapping("/frontend")
public class MainPageController {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Autowired
    private HeadLineDao headLineDao;

    /**
     * 展示首页
     *
     * @return
     */
    @RequestMapping(value = "/listmainpageinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listMainPageInfo() {
        Map<String, Object> modelMap = new HashMap<>();
        try {
            //获取一级店铺列表
            List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(null);
            modelMap.put("shopCategoryList", shopCategoryList);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }

        try {
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus(1);
            List<HeadLine> headLines = headLineDao.queryHeadlines(headLineCondition);
            modelMap.put("success", true);
            modelMap.put("headLineList", headLines);
        } catch (Exception e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        return modelMap;
    }
}
