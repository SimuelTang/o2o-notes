package indi.simuel.service;

import indi.simuel.dto.ProductCategoryExecution;
import indi.simuel.entity.Product;
import indi.simuel.entity.ProductCategory;
import indi.simuel.exceptions.ProductCategoryOperationException;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryService {
    List<ProductCategory> queryProductCategories(Long shopId);

    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories)
            throws ProductCategoryOperationException;

    ProductCategoryExecution deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                                                   @Param("shopId") long shopId) throws ProductCategoryOperationException;
    
    List<ProductCategory> getProductCategoryList(Long shopId);
}
