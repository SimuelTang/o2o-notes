package indi.simuel.dao;

import indi.simuel.dto.ImageHolder;
import indi.simuel.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    int batchInsertProductImg(List<ProductImg> productImgList);

    List<ProductImg> queryProductImageList(Long productId);

    void deleteProductImgByProductId(Long productId);
}
