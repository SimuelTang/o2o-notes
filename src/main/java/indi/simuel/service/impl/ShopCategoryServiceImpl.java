package indi.simuel.service.impl;

import indi.simuel.dao.ShopCategoryDao;
import indi.simuel.entity.ShopCategory;
import indi.simuel.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/2/25
 * @Time 10:12
 */

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    
    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }
    
}
