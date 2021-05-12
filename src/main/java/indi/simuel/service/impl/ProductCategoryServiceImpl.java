package indi.simuel.service.impl;

import indi.simuel.dao.ProductCategoryDao;
import indi.simuel.dao.ProductDao;
import indi.simuel.dto.ProductCategoryExecution;
import indi.simuel.entity.Product;
import indi.simuel.entity.ProductCategory;
import indi.simuel.enums.ProductCategoryStateEnum;
import indi.simuel.exceptions.ProductCategoryOperationException;
import indi.simuel.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/3
 * @Time 21:27
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductCategoryDao productCategoryDao;

    /**
     * 通过商铺 id 查找其对应的商品类别列表
     *
     * @param shopId 商铺id
     * @return 对应的商品列表
     */
    @Override
    public List<ProductCategory> queryProductCategories(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

    /**
     * 批量插入商品类别
     *
     * @param productCategories 待插入的商品类别
     * @return 操作后的状态
     * @throws ProductCategoryOperationException
     */
    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories)
            throws ProductCategoryOperationException {
        if (productCategories != null && productCategories.size() > 0) {
            try {
                int effectedNum = productCategoryDao.batchInsertProductCategory(productCategories);
                if (effectedNum <= 0) {
                    throw new ProductCategoryOperationException("商铺类别添加失败");
                }
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            } catch (Exception e) {
                throw new ProductCategoryOperationException("batchAddProductCategory error:" + e.getMessage());
            }
        }
        return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId)
            throws ProductCategoryOperationException {

        // 解除与这个商品类别绑定的商品
        try {
            int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商品类别更新失败");
            }
        } catch (Exception e) {
            throw new ProductCategoryOperationException("deleteProductCategoy error: " + e.getMessage());
        }

        // 删除这个商品类别
        try {
            int effectedNum = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (effectedNum <= 0) {
                throw new ProductCategoryOperationException("商品类别删除失败");
            } else {
                return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
            }
        } catch (ProductCategoryOperationException e) {
            throw new ProductCategoryOperationException("deleteProductCategory error:" + e.getMessage());
        }
    }


    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

}
