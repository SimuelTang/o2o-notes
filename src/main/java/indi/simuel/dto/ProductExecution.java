package indi.simuel.dto;

import indi.simuel.entity.Product;
import indi.simuel.enums.ProductStateEnum;

import java.util.List;

/**
 * @Author simuel_tang
 * @Date 2021/3/7
 * @Time 15:14
 */
public class ProductExecution {
    private int state;
    private String stateInfo;
    private int count;
    private Product product;
    private List<Product> productList;

    public ProductExecution() {
    }

    public ProductExecution(ProductStateEnum pse) {
        this.state = pse.getState();
        this.stateInfo = pse.getStateInfo();
    }

    public ProductExecution(ProductStateEnum pse, Product product) {
        this.state = pse.getState();
        this.stateInfo = pse.getStateInfo();
        this.product = product;
    }

    public ProductExecution(ProductStateEnum pse, List<Product> productList) {
        this.state = pse.getState();
        this.stateInfo = pse.getStateInfo();
        this.productList = productList;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
