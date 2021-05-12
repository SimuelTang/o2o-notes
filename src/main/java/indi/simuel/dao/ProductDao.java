package indi.simuel.dao;

import indi.simuel.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDao {

    int insertProduct(Product product);

    Product queryProductById(Long productId);

    List<Product> queryProductList(@Param("productCondition") Product productCondition,
                                   @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    int updateProduct(Product product);

    int updateProductCategoryToNull(long productCategoryId);
}
