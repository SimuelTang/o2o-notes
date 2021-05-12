package indi.simuel.dto;

import indi.simuel.entity.ProductCategory;
import indi.simuel.enums.ProductCategoryStateEnum;

import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/6
 * @Time 9:41
 */
public class ProductCategoryExecution {
    private int state;
    private String stateInfo;
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution() {
    }

    // 失败时使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum pcs) {
        this.state = pcs.getState();
        this.stateInfo = pcs.getStateInfo();
    }

    // 操作成功时使用的构造器
    public ProductCategoryExecution(ProductCategoryStateEnum pcs, List<ProductCategory> productCategories) {
        this.state = pcs.getState();
        this.stateInfo = pcs.getStateInfo();
        this.productCategoryList = productCategories;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public List<ProductCategory> getProductCategoryList() {
        return productCategoryList;
    }

    public void setProductCategoryList(List<ProductCategory> productCategoryList) {
        this.productCategoryList = productCategoryList;
    }
}
