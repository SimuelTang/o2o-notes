package indi.simuel.dao;

import indi.simuel.dto.ProductCategoryExecution;
import indi.simuel.entity.Product;
import indi.simuel.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    
    List<ProductCategory> queryProductCategoryList(Long shopId);

    int batchInsertProductCategory(List<ProductCategory> productCategories);

    int deleteProductCategory(@Param("productCategoryId") long productCategoryId,
                              @Param("shopId") long shopId);

}
