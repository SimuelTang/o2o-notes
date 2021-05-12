package indi.simuel.service;

import indi.simuel.dto.ImageHolder;
import indi.simuel.dto.ProductExecution;
import indi.simuel.entity.Product;
import indi.simuel.exceptions.ProductOperationException;

import java.util.List;

public interface ProductService {
    ProductExecution addProduct(Product product, ImageHolder holder, List<ImageHolder> holderList)
            throws ProductOperationException;

    Product getProductById(Long productId);

    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList);
}
