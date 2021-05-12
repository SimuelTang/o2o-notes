package indi.simuel.service.impl;

import indi.simuel.dao.ProductDao;
import indi.simuel.dao.ProductImgDao;
import indi.simuel.dto.ImageHolder;
import indi.simuel.dto.ProductExecution;
import indi.simuel.entity.Product;
import indi.simuel.entity.ProductImg;
import indi.simuel.enums.ProductStateEnum;
import indi.simuel.exceptions.ProductOperationException;
import indi.simuel.service.ProductService;
import indi.simuel.util.ImageUtil;
import indi.simuel.util.PageCalculatorUtil;
import indi.simuel.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/7
 * @Time 15:35
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImgDao productImgDao;


    @Override
    public ProductExecution addProduct(Product product, ImageHolder holder, List<ImageHolder> holderList) {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //进入循环的条件判断较为苛刻，首先我们可以确定 id 属性是自增的，所以不用判断
            //然后这里我们会使用到外键，所以要保证外键不为空
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(0);
            //如果缩略图不为空，则添加
            if (holder != null) {
                addProductImage(product, holder);
            }
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品失败：" + e.getMessage());
            }
            if (holderList != null && holderList.size() > 0) {
                addProductImgList(product, holderList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS, product);
        }
        //如果为空，则直接返回
        return new ProductExecution(ProductStateEnum.EMPTY);
    }

    @Override
    public Product getProductById(Long productId) {
        return productDao.queryProductById(productId);
    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int row = PageCalculatorUtil.pageIndexToRow(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, row, pageSize);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        return pe;
    }

    @Override
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //缩略图不为空，则清除原缩略图后再添加
            if (thumbnail != null) {
                String imgAddr = productDao.queryProductById(product.getProductId()).getImgAddr();
                if (imgAddr != null) {
                    ImageUtil.deleteImage(imgAddr);
                }
                addProductImage(product, thumbnail);
            }
            //商品详情图不为空，则清除原有的再添加
            if (productImgList != null && productImgList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgList);
            }
            //尝试更新商品信息
            try {
                // 更新商品信息
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("更新商品信息失败:" + e.toString());
            }
        }
        return new ProductExecution(ProductStateEnum.EMPTY);
    }

    private void deleteProductImgList(Long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImageList(productId);
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteImage(productImg.getImgAddr());
        }
        productImgDao.deleteProductImgByProductId(productId);
    }

    private void addProductImage(Product product, ImageHolder holder) {
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(holder, dest);
        product.setImgAddr(thumbnailAddr);
    }

    private void addProductImgList(Product product, List<ImageHolder> holderList) {
        // 从相应店铺底下获取图片信息
        String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();
        // 遍历图片一次去处理，并添加进productImg实体类里
        for (ImageHolder productImgHolder : holderList) {
            String imgAddr = ImageUtil.generateNormalImg(productImgHolder, dest);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add(productImg);
        }
        // 如果确实是有图片需要添加的，就执行批量添加操作
        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建商品详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建商品详情图片失败:" + e.toString());
            }
        }
    }
}
